package com.nullcrew.Utilities;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public final class DBManager {
	private static DBManager instance = new DBManager();
	private MongoClient client;
	private MongoDatabase database;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public DBManager() {
	}
	
	public void connectDB() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://nullcrew:NullCrew2022@cluster0.wcrf4.mongodb.net/alien_asteroid_game?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		client = MongoClients.create(settings);
		database = client.getDatabase("alien_asteroid_game");
	}
	
	public void closeDB() {
		client.close();
	}
	
	public void registerUser(String email, String password) {
		Document tmp = new Document("aaa", "aa.")
				.append("email", email)
				.append("password", password);
		
		database.getCollection("users_test").insertOne(tmp);
		closeDB();
	}

}
