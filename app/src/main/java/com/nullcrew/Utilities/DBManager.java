package com.nullcrew.Utilities;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
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

	public User getUser() {
		return user;
	}

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
	public void registerUser(String email, String password, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

}
