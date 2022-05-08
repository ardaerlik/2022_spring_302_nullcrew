package com.nullcrew.Utilities;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void saveTheGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadTheGame(String gameId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void registerUser(String email, String password, String forgotKey) {
		if (userExists(email)) {
			// TODO: Notify register observers
		} else {
			Document document = new Document()
					.append("email", email)
					.append("password", password)
					.append("forgotkey", forgotKey)
					.append("savedGamesIds", new ArrayList<ObjectId>());
			
			InsertOneResult result = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION)
					.insertOne(document);
			
			if (result.getInsertedId() != null) {
				// TODO: Notify register observers
			} else {
				// TODO: Notify register observers and login process
			}
		}
	}

	@Override
	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
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
	
	private boolean userExists(String email) {
		FindIterable<Document> users = database.getCollection(Constants.DatabaseResponses.USERS_COLLECTION).find(new Document());
		
		Iterator<Document> iterator = users.iterator();
		while (iterator.hasNext()) {
			if (((Document) iterator.next()).get("email").equals(email)) {
				return false;
			}
		}
		
		return true;
	}

}
