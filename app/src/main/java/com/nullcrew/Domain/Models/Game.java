package com.nullcrew.Domain.Models;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;


public class Game  {
	private static Game currentGame = null;
	private ObjectId gameId;
	private ArrayList<Asteroid> list_of_asteroids;
	private ArrayList<Document> list_of_asteroid_documents;
	private int score;
	private int lives;
	private ArrayList<PowerUp> list_of_powerups;
	private ArrayList<Document> list_of_powerup_documents;
	private ArrayList<Alien> list_of_aliens;
	private ArrayList<Document> list_of_alien_documents;
	private DataType location;
	
	public enum DataType {
		DB, FILE
	}
	
	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void setCurrentGame(Game game) {
		currentGame = game;
	}
	
	public Game() {
	}
	
	public Game(ObjectId gameId,
			ArrayList<Asteroid> list_of_asteroids, ArrayList<Alien> list_of_aliens, ArrayList<PowerUp> list_of_powerups) {
		this.gameId = gameId;
		this.list_of_asteroids = list_of_asteroids;
		this.list_of_aliens = list_of_aliens;
		this.list_of_powerups = list_of_powerups;
		
	}
	
	@SuppressWarnings("unchecked")
	public Game(Document document) {
		this.gameId = (ObjectId) document.get("_id");
		this.list_of_asteroids = (ArrayList<Asteroid>) document.get("asteroids");
		this.list_of_aliens = (ArrayList<Alien>) document.get("aliens");
		this.list_of_powerups = (ArrayList<PowerUp>) document.get("powerups");
		this.score = document.getInteger("score", 0);
		this.lives = document.getInteger("lives", 0);
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
	
	public void buildDocuments() {
		buildAsteroidDocuments();
		buildAlienDocuments();
		buildPowerUpDocuments();
	}
	
	public Document getDocument() {
		Document document = new Document()
				.append("asteroids", list_of_asteroid_documents)
				.append("score", score)
				.append("lives", lives)
				.append("powerups", list_of_powerup_documents)
				.append("aliens", list_of_alien_documents);
		
		return document;
	}

	public ObjectId getGameId() {
		return gameId;
	}

	public void setGameId(ObjectId gameId) {
		this.gameId = gameId;
	}

	public ArrayList<Asteroid> getList_of_asteroids() {
		return list_of_asteroids;
	}

	public void setList_of_asteroids(ArrayList<Asteroid> list_of_asteroids) {
		this.list_of_asteroids = list_of_asteroids;
	}

	public ArrayList<Document> getList_of_asteroid_documents() {
		return list_of_asteroid_documents;
	}

	public void setList_of_asteroid_documents(ArrayList<Document> list_of_asteroid_documents) {
		this.list_of_asteroid_documents = list_of_asteroid_documents;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public ArrayList<PowerUp> getList_of_powerups() {
		return list_of_powerups;
	}

	public void setList_of_powerups(ArrayList<PowerUp> list_of_powerups) {
		this.list_of_powerups = list_of_powerups;
	}

	public ArrayList<Document> getList_of_powerup_documents() {
		return list_of_powerup_documents;
	}

	public void setList_of_powerup_documents(ArrayList<Document> list_of_powerup_documents) {
		this.list_of_powerup_documents = list_of_powerup_documents;
	}

	public ArrayList<Alien> getList_of_aliens() {
		return list_of_aliens;
	}

	public void setList_of_aliens(ArrayList<Alien> list_of_aliens) {
		this.list_of_aliens = list_of_aliens;
	}

	public ArrayList<Document> getList_of_alien_documents() {
		return list_of_alien_documents;
	}

	public void setList_of_alien_documents(ArrayList<Document> list_of_alien_documents) {
		this.list_of_alien_documents = list_of_alien_documents;
	}
	
	public DataType getLocation() {
		return location;
	}

	public void setLocation(DataType location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", list_of_asteroids=" + list_of_asteroids + ", score=" + score + ", lives="
				+ lives + ", list_of_powerups=" + list_of_powerups + ", list_of_aliens=" + list_of_aliens + "]";
	}
	
}
