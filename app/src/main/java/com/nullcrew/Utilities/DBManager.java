package com.nullcrew.Utilities;

import java.util.ArrayList;
import java.util.Iterator;

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
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public final class DBManager implements DataStrategy {
	private static DBManager instance = new DBManager();
	private MongoClient client;
	private MongoDatabase database;
	private User user;

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
		// TODO Auto-generated method stub
	}

	@Override
	public void loadTheGames() {
		user.getAccount().setSavedGames(getGamesWithObjectIds(user.getSavedGameIds()));
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
				// TODO: Notify register observers
			} else {
				// TODO: Notify register observers and login process
			}
		} else {
			// TODO: Notify register observers
		}
	}

	@Override
	public synchronized void loginUser(String email, String password) {
		if (userExists(email)) {
			ObjectId checkedCredentials = checkCredentials(email, password);
			
			if (checkedCredentials != null) {
				user = getUserWithObjectId(checkedCredentials);
				// TODO: Notify login observers
			} else {
				// TODO: Notify login observers
			}
		} else {
			// TODO: Notify login observers
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
	
	private synchronized ObjectId checkCredentials(String email, String password) {
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
		return null;
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
