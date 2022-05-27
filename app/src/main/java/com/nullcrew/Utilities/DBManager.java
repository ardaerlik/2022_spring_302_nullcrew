package com.nullcrew.Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.Constants.DatabaseResponses;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public final class DBManager implements DataStrategy {
	private static DBManager instance = new DBManager();
	private MongoClient client;
	private MongoDatabase database;
	private User user;
	private AuthObserver authObserver;
	private SaveLoadObserver saveLoadObserver;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public DBManager() {
	}
	
	public void connectDB() {
		ConnectionString connectionString = new ConnectionString(Constants.DatabaseResponses.DATABASE_CREDENTIALS);
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		client = MongoClients.create(settings);
		database = client.getDatabase(Constants.DatabaseResponses.DATABASE_NAME);
	}
	
	public void closeDB() {
		client.close();
	}

	@Override
	public void saveTheGame(Game game) {	
		if (game.getGameId() == null) {
			InsertOneResult result = database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION)
					.insertOne(game.getDocument());
			
			if (result.getInsertedId() != null) {
				game.setGameId(result.getInsertedId().asObjectId().getValue());
				user.getSavedGameIds().add(result.getInsertedId().asObjectId().getValue());
				notifySaveLoadObserver(DatabaseResponses.NEW_GAME_SAVED);
			} else {
				notifySaveLoadObserver(DatabaseResponses.DATABASE_ERROR);
			}
		} else {
			Document query = new Document().append("_id", game.getGameId());
			
			Bson updates = Updates.combine(Updates.set("asteroids", game.getList_of_asteroid_documents()),
					Updates.set("score", game.getScore()),
					Updates.set("lives", game.getLives()),
					Updates.set("powerups", game.getList_of_powerup_documents()),
					Updates.set("aliens", game.getList_of_alien_documents()));
			UpdateOptions options = new UpdateOptions().upsert(true);
			
			try {
				database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION)
						.updateOne(query, updates, options);
				notifySaveLoadObserver(DatabaseResponses.GAME_UPDATED);
			} catch (MongoException e) {
				notifySaveLoadObserver(DatabaseResponses.DATABASE_ERROR);
			}
		}
	}

	@Override
	public void loadTheGames() {
		user.getAccount().setSavedGames(getGamesWithObjectIds(user.getSavedGameIds()));
		if (user.getAccount().getSavedGames() != null) {
			notifySaveLoadObserver(DatabaseResponses.GAMES_LOADED);
		} else {
			notifySaveLoadObserver(DatabaseResponses.DATABASE_ERROR);
		}
	}

	@Override
	public synchronized void registerUser(String email, String password, String forgotKey) {
		if (!userExists(email)) {
			Document document = new Document()
					.append("email", email)
					.append("password", password)
					.append("forgotKey", forgotKey)
					.append("savedGameIds", new ArrayList<ObjectId>());
			
			InsertOneResult result = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
					.insertOne(document);
			
			if (result.getInsertedId() != null) {
				notifyAuthObservers(DatabaseResponses.REGISTER_ACCEPTED);
			} else {
				notifyAuthObservers(DatabaseResponses.DATABASE_ERROR);
			}
		} else {
			notifyAuthObservers(DatabaseResponses.EMAIL_HAS_ACCOUNT);
		}
	}

	@Override
	public synchronized void loginUser(String email, String password) {
		if (userExists(email)) {
			ObjectId checkedCredentials = checkCredentials(email, password);
			
			if (checkedCredentials != null) {
				user = getUserWithObjectId(checkedCredentials);
				notifyAuthObservers(DatabaseResponses.LOGIN_ACCEPTED);
			} else {
				notifyAuthObservers(DatabaseResponses.WRONG_PASSWORD);
			}
		} else {
			notifyAuthObservers(DatabaseResponses.WRONG_EMAIL);
		}
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
		if (userExists(email)) {
			ObjectId checkedForgotKey = checkForgotKey(email, forgotKey);
			
			if (checkedForgotKey != null) {
				boolean success = updatePassword(checkedForgotKey, newPassword);

				if (success) {
					// TODO: Notify reset password observers and login
				} else {
					// TODO: Notify reset password observers
				}
			} else {
				// TODO: Notify reset password observers
			}
		} else {
			// TODO: Notify reset password observers
		}
	}
	
	private synchronized boolean userExists(String email) {
		FindIterable<Document> users = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).find(new Document());
		
		Iterator<Document> iterator = users.iterator();
		while (iterator.hasNext()) {
			if (((Document) iterator.next()).get("email").equals(email)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks the credentials of the given email with password on the database
	 * 
	 * @requires
	 * <li>DBManager instance is created</li>
	 * <li>MongoClient is opened</li>
	 * <li>MongoDatabase is created</li>
	 * @effects 
	 * <li>if email or password is null throws NullPointerException</li>
	 * <li>		else if email is not matched with REGEX throws IllegalArgumentException</li>
	 * <li>		else if email is not found returns null</li>
	 * <li>		else if password is not correct returns null</li>
	 * <li>		else returns userId of the email</li>
	 * @modifies 
	 * <li>database instance</li>
	 * @param email 		String value of email address of the user
	 * @param password 		String value of password of the user
	 * @return ObjectId of the given email address or null
	 */
	public synchronized ObjectId checkCredentials(String email, String password) {
		if (email == null || password == null) {
			throw new NullPointerException("Email and password can not be null");
		}
		
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		if (!pattern.matcher(email).matches()) {
			throw new IllegalArgumentException("Email argument is not valid");
		}
		
		FindIterable<Document> users = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).find(new Document());
		
		Iterator<Document> iterator = users.iterator();
		while (iterator.hasNext()) {
			Document document = (Document) iterator.next();
			
			if (document.get("email").equals(email)) {
				if (document.get("password").equals(password)) {
					return (ObjectId) document.get("_id");
				} else {
					return null;
				}
			}
		}
		
		return null;
	}
	
	private synchronized User getUserWithObjectId(ObjectId objectId) {		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", objectId);
		
		Document document = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
				.find(query)
				.first();
		User user = new User(document);
		
		return user;
	}
	
	private synchronized ArrayList<Game> getGamesWithObjectIds(ArrayList<ObjectId> objectIds) {
		ArrayList<Game> games = new ArrayList<Game>();
		
		for (ObjectId gameId: objectIds) {
			BasicDBObject query = new BasicDBObject();
			query.put("_id", gameId);
			
			Document document = database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION)
					.find(query)
					.first();
			games.add(new Game(document));
		}
		
		return games;
	}
	
	private synchronized ObjectId checkForgotKey(String email, String forgotKey) {
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		Document document = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
				.find(query)
				.first();
		
		if (document.get("forgotKey").equals(forgotKey)) {
			return (ObjectId) document.get("_id");
		} else {
			return null;
		}
	}
	
	private synchronized boolean updatePassword(ObjectId objectId, String newPassword) {
		Document query = new Document().append("_id", objectId);
		
		Bson updates = Updates.combine(Updates.set("password", newPassword));
		UpdateOptions options = new UpdateOptions().upsert(true);
		
		try {
			database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
					.updateOne(query, updates, options);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}
	
	@Override
	public void subscribeAuthObserver(AuthObserver observer) {
		this.authObserver = observer;
	}
	
	@Override
	public void notifyAuthObservers(String response) {
		if (response.equals(DatabaseResponses.LOGIN_ACCEPTED)) {
			authObserver.loginAccepted(user, response);
			return;
		}
		
		if (response.equals(DatabaseResponses.WRONG_EMAIL)) {
			authObserver.loginRejected(response);
			return;
		}
		
		if (response.equals(DatabaseResponses.WRONG_PASSWORD)) {
			authObserver.loginRejected(response);
			return;
		}
		// TODO: Observers
	}
	
	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		this.saveLoadObserver = observer;
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		// TODO Auto-generated method stub
		
	}
	
	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}
	
	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

}
