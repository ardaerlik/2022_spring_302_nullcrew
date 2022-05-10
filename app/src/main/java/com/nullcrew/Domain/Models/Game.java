package com.nullcrew.Domain.Models;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;


public class Game  {
	private ObjectId gameId;
	private ArrayList<Asteroid> list_of_asteroids;
	private ArrayList<Document> list_of_asteroid_documents;
	private int score;
	private int lives;
	private ArrayList<PowerUp> list_of_powerups;
	private ArrayList<Document> list_of_powerup_documents;
	private ArrayList<Alien> list_of_aliens;
	private ArrayList<Document> list_of_alien_documents;
	
	public Game(ObjectId gameId,
			ArrayList<Asteroid> list_of_asteroids, ArrayList<Alien> list_of_aliens, ArrayList<PowerUp> list_of_powerups) {
		this.gameId = gameId;
		this.list_of_asteroids = list_of_asteroids;
		this.list_of_aliens = list_of_aliens;
		this.list_of_powerups = list_of_powerups;
		
	}
	
	public void buildAsteroidDocuments() {
		for (int i = 0; i < list_of_asteroids.size(); i++) {
			list_of_asteroid_documents.add(list_of_asteroids.get(i).getDocument());
		}
	}
	
	public void buildAlienDocuments() {
		for (int i = 0; i < list_of_aliens.size(); i++) {
			list_of_alien_documents.add(list_of_aliens.get(i).getDocument());
		}
	}
	
	public void buildPowerUpDocuments() {
		for (int i = 0; i < list_of_powerups.size(); i++) {
			list_of_powerup_documents.add(list_of_powerups.get(i).getDocument());
		}
	}
	
	
	
	public Document getDocument() {
		Document document = new Document()
				.append("_id", gameId)
				.append("asteroids", list_of_asteroid_documents)
				.append("score", score)
				.append("lives", lives)
				.append("powerups", list_of_powerup_documents)
				.append("aliens", list_of_alien_documents);
		
		return document;
	}

	
}
