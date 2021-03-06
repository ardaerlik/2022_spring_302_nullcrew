package com.nullcrew.Domain.Models.Game;

import java.util.ArrayList;
import javax.swing.Timer;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.nullcrew.Domain.Models.GameObject.Alien;
import com.nullcrew.Domain.Models.GameObject.Asteroid;
import com.nullcrew.Domain.Models.GameObject.Ball;
import com.nullcrew.Domain.Models.GameObject.LaserBall;
import com.nullcrew.Domain.Models.GameObject.Paddle;
import com.nullcrew.Domain.Models.GameObject.PowerUp;


public class Game  {
	private static final int NUM_LIVES = 3;
	private static Game currentGame = null;
	private ObjectId gameId;
	private int score;
	private int lives;
	private int timeRemaining;
	private Paddle paddle;
	private ArrayList<Asteroid> list_of_asteroids;
	private ArrayList<PowerUp> list_of_powerups;
	private ArrayList<Alien> list_of_aliens;
	private ArrayList<Ball> list_of_balls;
	private ArrayList<LaserBall> list_of_laser_balls;
	private ArrayList<Document> list_of_powerup_documents;
	private ArrayList<Document> list_of_alien_documents;
	private ArrayList<Document> list_of_asteroid_documents;
	private ArrayList<Document> list_of_ball_documents;
	private ArrayList<Document> list_of_laserball_documents;
	private Document paddle_document;
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
		this.lives = NUM_LIVES;
		
		list_of_asteroid_documents = new ArrayList<Document>();
		list_of_powerup_documents = new ArrayList<Document>();
		list_of_alien_documents = new ArrayList<Document>();
		list_of_ball_documents = new ArrayList<Document>();
		list_of_laserball_documents = new ArrayList<Document>();
		paddle_document = new Document();
		
		list_of_asteroids = new ArrayList<Asteroid>();
		list_of_powerups = new ArrayList<PowerUp>();
		list_of_aliens = new ArrayList<Alien>();
		list_of_balls = new ArrayList<Ball>();
		list_of_laser_balls = new ArrayList<LaserBall>();
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
		list_of_asteroids = new ArrayList<Asteroid>();
		list_of_powerups = new ArrayList<PowerUp>();
		list_of_aliens = new ArrayList<Alien>();
		list_of_balls = new ArrayList<Ball>();
		list_of_laser_balls = new ArrayList<LaserBall>();
		
		this.gameId = (ObjectId) document.get("_id");
		this.score = document.getInteger("score", 0);
		this.lives = document.getInteger("lives", 0);
		this.timeRemaining = document.getInteger("timeRemaining", 200);
		
		ArrayList<Document> asteroidDocuments = (ArrayList<Document>) document.get("asteroids");
		ArrayList<Document> alienDocuments = (ArrayList<Document>) document.get("aliens");
		ArrayList<Document> powerupDocuments = (ArrayList<Document>) document.get("powerups");
		ArrayList<Document> ballDocuments = (ArrayList<Document>) document.get("balls");
		ArrayList<Document> laserballDocuments = (ArrayList<Document>) document.get("laserballs");
		Document paddleDocument = (Document) document.get("paddle");
		
		list_of_asteroids = createAsteroidsFromDocument(asteroidDocuments);
		list_of_powerups = createPowerupsFromDocument(powerupDocuments);
		list_of_aliens = createAliensFromDocument(alienDocuments);
		list_of_balls = createBallsFromDocument(ballDocuments);
		list_of_laser_balls = createLaserBallsFromDocument(laserballDocuments);
		paddle = createPaddleFromDocument(paddleDocument);
	}
	
	public ArrayList<Asteroid> createAsteroidsFromDocument(ArrayList<Document> documents) {
		ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
		// TODO: create model instances here
		return asteroids;
	}
	
	public ArrayList<Alien> createAliensFromDocument(ArrayList<Document> documents) {
		ArrayList<Alien> aliens = new ArrayList<Alien>();
		// TODO: create model instances here
		return aliens;
	}
	
	public ArrayList<PowerUp> createPowerupsFromDocument(ArrayList<Document> documents) {
		ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
		// TODO: create model instances here
		return powerups;
	}
	
	public ArrayList<Ball> createBallsFromDocument(ArrayList<Document> documents) {
		ArrayList<Ball> balls = new ArrayList<Ball>();
		// TODO: create model instances here
		return balls;
	}
	
	public ArrayList<LaserBall> createLaserBallsFromDocument(ArrayList<Document> documents) {
		ArrayList<LaserBall> laserBalls = new ArrayList<LaserBall>();
		// TODO: create model instances here
		return laserBalls;
	}
	
	public Paddle createPaddleFromDocument(Document document) {
		Paddle paddle = null;
		// TODO: create model instances here
		return paddle;
	}
	
	public void buildAsteroidDocuments() {
		list_of_asteroid_documents = new ArrayList<Document>();
		
		if (list_of_asteroids == null) {
			return;
		}
		
		for (int i = 0; i < list_of_asteroids.size(); i++) {
			list_of_asteroid_documents.add(list_of_asteroids.get(i).getDocument());
		}
	}
	
	public void buildAlienDocuments() {
		list_of_alien_documents = new ArrayList<Document>();
		
		if (list_of_aliens == null) {
			return;
		}
		
		for (int i = 0; i < list_of_aliens.size(); i++) {
			list_of_alien_documents.add(list_of_aliens.get(i).getDocument());
		}
	}
	
	public void buildPowerUpDocuments() {
		list_of_powerup_documents = new ArrayList<Document>();
		
		if (list_of_powerups == null) {
			return;
		}
		
		for (int i = 0; i < list_of_powerups.size(); i++) {
			list_of_powerup_documents.add(list_of_powerups.get(i).getDocument());
		}
	}
	
	public void buildBallDocuments() {
		list_of_ball_documents = new ArrayList<Document>();
		
		if (list_of_balls == null) {
			return;
		}
		
		for (int i = 0; i < list_of_balls.size(); i++) {
			list_of_ball_documents.add(list_of_balls.get(i).getDocument());
		}
	}
	
	public void buildLaserBallDocuments() {
		list_of_laserball_documents = new ArrayList<Document>();
		
		if (list_of_laser_balls == null) {
			return;
		}
		
		for (int i = 0; i < list_of_laser_balls.size(); i++) {
			list_of_laserball_documents.add(list_of_laser_balls.get(i).getDocument());
		}
	}
	
	public void buildPaddleDocument() {
		paddle_document = new Document();
		
		if (paddle == null) {
			return;
		}
		
		paddle_document = paddle.getDocument();
	}
	
	public void buildDocuments() {
		buildAsteroidDocuments();
		buildAlienDocuments();
		buildPowerUpDocuments();
		buildBallDocuments();
		buildLaserBallDocuments();
		buildPaddleDocument();
	}
	
	public Document getDocument() {
		Document document = new Document()
				.append("asteroids", list_of_asteroid_documents)
				.append("score", score)
				.append("lives", lives)
				.append("powerups", list_of_powerup_documents)
				.append("aliens", list_of_alien_documents)
				.append("balls", list_of_ball_documents)
				.append("laserBalls", list_of_laser_balls)
				.append("paddle", paddle_document)
				.append("timeRemaining", timeRemaining);
		
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

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public ArrayList<Ball> getList_of_balls() {
		return list_of_balls;
	}

	public void setList_of_balls(ArrayList<Ball> list_of_balls) {
		this.list_of_balls = list_of_balls;
	}

	public ArrayList<LaserBall> getList_of_laser_balls() {
		return list_of_laser_balls;
	}

	public void setList_of_laser_balls(ArrayList<LaserBall> list_of_laser_balls) {
		this.list_of_laser_balls = list_of_laser_balls;
	}

	public ArrayList<Document> getList_of_ball_documents() {
		return list_of_ball_documents;
	}

	public void setList_of_ball_documents(ArrayList<Document> list_of_ball_documents) {
		this.list_of_ball_documents = list_of_ball_documents;
	}

	public ArrayList<Document> getList_of_laserball_documents() {
		return list_of_laserball_documents;
	}

	public void setList_of_laserball_documents(ArrayList<Document> list_of_laserball_documents) {
		this.list_of_laserball_documents = list_of_laserball_documents;
	}

	public Document getPaddle_document() {
		return paddle_document;
	}

	public void setPaddle_document(Document paddle_document) {
		this.paddle_document = paddle_document;
	}

	public static int getNumLives() {
		return NUM_LIVES;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", score=" + score + ", lives=" + lives + ", timeRemaining=" + timeRemaining
				+ ", paddle=" + paddle + ", list_of_asteroids=" + list_of_asteroids + ", list_of_powerups="
				+ list_of_powerups + ", list_of_aliens=" + list_of_aliens + ", list_of_balls=" + list_of_balls
				+ ", list_of_laser_balls=" + list_of_laser_balls + "]";
	}
	
}
