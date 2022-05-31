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
import com.nullcrew.Domain.Models.Game.DataType;
import com.nullcrew.Domain.Models.User;

public final class DBManager {
	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}

		return instance;
	}

	private AuthObserver authObserver;
	private MongoClient client;
	private MongoDatabase database;

	private SaveLoadObserver saveLoadObserver;

	public DBManager() {
	}

	/**
	 * Checks the credentials of the given email with password on the database
	 * 
	 * @requires
	 *           <li>DBManager instance is created</li>
	 *           <li>MongoClient is opened</li>
	 *           <li>MongoDatabase is created</li>
	 * @effects
	 *          <li>if email or password is null throws NullPointerException</li>
	 *          <li>else if email is not matched with REGEX throws
	 *          IllegalArgumentException</li>
	 *          <li>else if email is not found returns null</li>
	 *          <li>else if password is not correct returns null</li>
	 *          <li>else returns userId of the email</li>
	 * @modifies
	 *           <li>database instance</li>
	 * @param email    String value of email address of the user
	 * @param password String value of password of the user
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

		FindIterable<Document> users = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
				.find(new Document());

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

	private synchronized ObjectId checkForgotKey(String email, String forgotKey) {
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);

		Document document = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).find(query).first();

		if (document.get("forgotKey").equals(forgotKey)) {
			return (ObjectId) document.get("_id");
		} else {
			return null;
		}
	}

	public void closeDB() {
		client.close();
	}

	public void connectDB() {
		ConnectionString connectionString = new ConnectionString(Constants.DatabaseResponses.DATABASE_CREDENTIALS);
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
		client = MongoClients.create(settings);
		database = client.getDatabase(Constants.DatabaseResponses.DATABASE_NAME);
	}

	public MongoClient getClient() {
		return client;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	private synchronized ArrayList<Game> getGamesWithObjectIds(ArrayList<ObjectId> objectIds) {
		ArrayList<Game> games = new ArrayList<Game>();

		for (ObjectId gameId : objectIds) {
			BasicDBObject query = new BasicDBObject();
			query.put("_id", gameId);
			System.out.println(gameId);
			Document document = database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION).find(query)
					.first();
			Game game = new Game(document);
			game.setLocation(DataType.DB);
			games.add(game);
		}

		return games;
	}

	private synchronized User getUserWithObjectId(ObjectId objectId) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", objectId);

		Document document = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).find(query).first();
		User user = new User(document);

		return user;
	}

	public void loadTheGames() {
		User.getInstance().getAccount().addListOfGames(getGamesWithObjectIds(User.getInstance().getSavedGameIds()));

		if (User.getInstance().getAccount().getSavedGames() != null) {
			notifySaveLoadObserver(DatabaseResponses.GAMES_LOADED);
		} else {
			notifySaveLoadObserver(DatabaseResponses.DATABASE_READ_ERROR);
		}
	}

	public synchronized void loginUser(String email, String password) {
		if (userExists(email)) {
			ObjectId checkedCredentials = checkCredentials(email, password);

			if (checkedCredentials != null) {
				User.setInstance(getUserWithObjectId(checkedCredentials));
				notifyAuthObservers(DatabaseResponses.LOGIN_ACCEPTED);
			} else {
				notifyAuthObservers(DatabaseResponses.WRONG_PASSWORD);
			}
		} else {
			notifyAuthObservers(DatabaseResponses.WRONG_EMAIL);
		}
	}

	public void notifyAuthObservers(String response) {
		if (response.equals(DatabaseResponses.LOGIN_ACCEPTED)) {
			authObserver.loginAccepted(User.getInstance(), response);
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

		if (response.equals(DatabaseResponses.EMAIL_HAS_ACCOUNT)) {
			authObserver.registerRejected(response);
			return;
		}

		if (response.equals(DatabaseResponses.REGISTER_ACCEPTED)) {
			authObserver.registerAccepted(User.getInstance(), response);
			return;
		}
	}

	public void notifySaveLoadObserver(String response) {
		if (response.equals(DatabaseResponses.DATABASE_WRITE_ERROR)) {
			saveLoadObserver.gameNotSaved(response);
			return;
		}

		if (response.equals(DatabaseResponses.DATABASE_READ_ERROR)) {
			saveLoadObserver.gameNotLoaded(response);
			return;
		}

		if (response.equals(DatabaseResponses.GAME_UPDATED)) {
			saveLoadObserver.gameSaved(response);
			return;
		}

		if (response.equals(DatabaseResponses.GAMES_LOADED)) {
			saveLoadObserver.allGamesLoaded(User.getInstance().getAccount().getSavedGames(), response);
			return;
		}

		if (response.equals(DatabaseResponses.NEW_GAME_SAVED)) {
			saveLoadObserver.gameSaved(response);
			return;
		}
	}

	public synchronized void registerUser(String email, String password, String forgotKey) {
		if (!userExists(email)) {
			Document document = new Document().append("email", email).append("password", password)
					.append("forgotKey", forgotKey).append("savedGameIds", new ArrayList<ObjectId>());

			InsertOneResult result = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
					.insertOne(document);

			if (result.getInsertedId() != null) {
				User.setInstance(getUserWithObjectId(result.getInsertedId().asObjectId().getValue()));
				notifyAuthObservers(DatabaseResponses.REGISTER_ACCEPTED);
			} else {
				notifyAuthObservers(DatabaseResponses.DATABASE_WRITE_ERROR);
			}
		} else {
			notifyAuthObservers(DatabaseResponses.EMAIL_HAS_ACCOUNT);
		}
	}

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

	public void saveTheGame(Game game) {
		if (game.getGameId() == null) {
			InsertOneResult result = database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION)
					.insertOne(game.getDocument());
			if (result.getInsertedId() != null) {
				game.setGameId(result.getInsertedId().asObjectId().getValue());
				User.getInstance().getSavedGameIds().add(result.getInsertedId().asObjectId().getValue());
				User.getInstance().getAccount().addGame(game);

				ArrayList<ObjectId> idsOnDB = new ArrayList<ObjectId>();
				for (Game g : User.getInstance().getAccount().getSavedGames()) {
					if (g.getLocation() == DataType.DB) {
						idsOnDB.add(g.getGameId());
					}
				}

				Document query = new Document().append("_id", User.getInstance().getAccount().getAccountId());
				Bson updates = Updates.combine(Updates.set("savedGameIds", idsOnDB));
				UpdateOptions options = new UpdateOptions().upsert(true);

				try {
					database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).updateOne(query, updates,
							options);
					notifySaveLoadObserver(DatabaseResponses.NEW_GAME_SAVED);
				} catch (MongoException e) {
					notifySaveLoadObserver(DatabaseResponses.DATABASE_WRITE_ERROR);
				}
			} else {
				notifySaveLoadObserver(DatabaseResponses.DATABASE_WRITE_ERROR);
			}
		} else {
			Document query = new Document().append("_id", game.getGameId());

			Bson updates = Updates.combine(Updates.set("asteroids", game.getList_of_asteroid_documents()),
					Updates.set("score", game.getScore()), Updates.set("lives", game.getLives()),
					Updates.set("powerups", game.getList_of_powerup_documents()),
					Updates.set("aliens", game.getList_of_alien_documents()));
			UpdateOptions options = new UpdateOptions().upsert(true);

			try {
				database.getCollection(Constants.DatabaseResponses.GAMES_COLLECTION).updateOne(query, updates, options);
				notifySaveLoadObserver(DatabaseResponses.GAME_UPDATED);
			} catch (MongoException e) {
				notifySaveLoadObserver(DatabaseResponses.DATABASE_WRITE_ERROR);
			}
		}
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public void subscribeAuthObserver(AuthObserver observer) {
		this.authObserver = observer;
	}

	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		this.saveLoadObserver = observer;
	}

	private synchronized boolean updatePassword(ObjectId objectId, String newPassword) {
		Document query = new Document().append("_id", objectId);

		Bson updates = Updates.combine(Updates.set("password", newPassword));
		UpdateOptions options = new UpdateOptions().upsert(true);

		try {
			database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).updateOne(query, updates, options);
			return true;
		} catch (MongoException e) {
			return false;
		}
	}

	private synchronized boolean userExists(String email) {
		FindIterable<Document> users = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
				.find(new Document());

		Iterator<Document> iterator = users.iterator();
		while (iterator.hasNext()) {
			if (((Document) iterator.next()).get("email").equals(email)) {
				return true;
			}
		}

		return false;
	}

}
