package com.nullcrew.Utilities;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

public final class DBManager {
	private static DBManager instance = new DBManager();
	private static MongoClient mongoClient;

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public DBManager() {
	}
	
	public void connectDB() {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://nullcrew:<NullCrew2022>@cluster0.wcrf4.mongodb.net/alien_asteroid_games?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
		        .applyConnectionString(connectionString)
		        .serverApi(ServerApi.builder()
		            .version(ServerApiVersion.V1)
		            .build())
		        .build();
		mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("alien_asteroid_game");
	}
	
	public void closeDB() {
		mongoClient.close();
	}

}
