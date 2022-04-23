package com.nullcrew.Controllers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Models.*;
import com.nullcrew.Utilities.*;
import com.nullcrew.Views.*;


public class GameController {
	private GameView gameView;
	private Paddle paddle;
	private List<Asteroid> asteroidList;
	private Ball ball;
	public static final int MIN_NUM_ASTEROIDS = 75;
	public static final int MAX_NUM_ASTEROIDS = 105;
	public static final int MIN_NUM_FIRM = 10;
	public static final int MIN_NUM_EXPLOSIVE = 5;
	public static final int MIN_NUM_GIFT = 10;
	
	public GameController(GameView gameView) {
		this.gameView = gameView;
		asteroidList = new ArrayList<>();
	}

	public void paddleMoved(MoveDirection direction) {
		if(GamePanel.gameMode==GameMode.PAUSED) {
			return;
		}
		switch (direction) {
		case RIGHT: {		
			if (paddle.getX() + gameView.getGameController().getPaddle().getWidth() < gameView.getFrame().getWidth()) {
				paddle.setX(paddle.getX() + 5);
			}
			break;
		}
		case LEFT: {
			if (paddle.getX() > 0) {
				paddle.setX(paddle.getX() - 5);
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}
	
	public void paddleRotated(MoveDirection direction) {
		if(GamePanel.gameMode==GameMode.PAUSED) {
			return;
		}
		switch (direction) {
		case UP: {
			if(paddle.getRotationDegree()+5>45) {
				return;
			}
			paddle.setRotationDegree(paddle.getRotationDegree() + 5);
			break;
		}
		case DOWN: {
			if(paddle.getRotationDegree()-5<-45) {
				return;
			}
			paddle.setRotationDegree(paddle.getRotationDegree() - 5);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}

	public void ballMoved() {
		if(GamePanel.gameMode==GameMode.PAUSED) {
			return;
		}
		if(0>=ball.getX()) {
			ball.setVelocityX(-ball.getVelocityX());
		}
		if(ball.getX()+ball.getWidth()>=gameView.getGamePanel().getWidth()) {
			ball.setVelocityX(-ball.getVelocityX());
		}
		if(0>=ball.getY()){
			ball.setVelocityY((-ball.getVelocityY()));
		}
		ball.setX(ball.getX() + ball.getVelocityX());
		ball.setY(ball.getY() + ball.getVelocityY());
		
	}

	private int countAsteroidTypes(AsteroidType type){
		int count=0;
		for(Asteroid a: asteroidList){
			if(a.getType() == type){
				count++;
			}
		}
		return count;
	}

	public Asteroid dragAsteroid(int x, int y){
		Asteroid toBeRemoved=null;
		for(Asteroid a: asteroidList){
			if( x >= a.getX() && x <= a.getX()+GameObjectFactory.ASTEROID_WIDTH &&
					y >= a.getY() && y <= a.getY()+GameObjectFactory.ASTEROID_HEIGHT) {
				toBeRemoved = a;
				break;
			}
		}
		Asteroid backup=null;
		if(toBeRemoved != null) {
			backup = (Asteroid) toBeRemoved.clone();
			asteroidList.remove(toBeRemoved);
			gameView.getGamePanel().repaint();
		}
		return backup;
	}

	public Object[] removeAsteroid(int x, int y){
		MessageType msg = null;
		Asteroid toBeRemoved=null;
		for(Asteroid a: asteroidList){
			if( x >= a.getX() && x <= a.getX()+GameObjectFactory.ASTEROID_WIDTH &&
					y >= a.getY() && y <= a.getY()+GameObjectFactory.ASTEROID_HEIGHT) {
				toBeRemoved = a;
				break;
			}else{
				msg = MessageType.NoAsteroidInThisLocation;
			}
		}
		Asteroid backup=null;
		if(toBeRemoved != null) {
			if( asteroidList.size() <= MIN_NUM_ASTEROIDS){
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorTotal;
			}else if( toBeRemoved.getType() == AsteroidType.Firm && countAsteroidTypes(AsteroidType.Firm) <= MIN_NUM_FIRM){
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorFirm;
			}else if( toBeRemoved.getType() == AsteroidType.Explosive && countAsteroidTypes(AsteroidType.Explosive) <= MIN_NUM_EXPLOSIVE){
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorExplosive;
			}else if( toBeRemoved.getType() == AsteroidType.Gift && countAsteroidTypes(AsteroidType.Gift) <= MIN_NUM_GIFT){
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorGift;
			}else{
				msg = MessageType.Success;
			}
			if(toBeRemoved != null) {
				backup = (Asteroid) toBeRemoved.clone();
				asteroidList.remove(toBeRemoved);
				gameView.getGamePanel().repaint();
			}
		}
		return new Object[]{backup, msg};
	}

	public MessageType checkNumAsteroids(int[] numOfAsteroidTypes){ //simple, firm, explosive, gift
		int total=0;
		for(int n: numOfAsteroidTypes){
			total = total + n;
		}
		if( total < MIN_NUM_ASTEROIDS){
			return MessageType.MinThresholdErrorTotal;
		}else if( total > MAX_NUM_ASTEROIDS){
			return MessageType.MaxThresholdErrorTotal;
		}else if( numOfAsteroidTypes[1] < MIN_NUM_FIRM){
			return MessageType.MinThresholdErrorFirm;
		}else if( numOfAsteroidTypes[2] < MIN_NUM_EXPLOSIVE){
			return MessageType.MinThresholdErrorExplosive;
		}else if( numOfAsteroidTypes[3] < MIN_NUM_GIFT){
			return MessageType.MinThresholdErrorGift;
		}else{
			return MessageType.Success;
		}
	}

	public boolean addAsteroid(Asteroid toBeAdded, int newX, int newY){
		for(Asteroid a: asteroidList){
			if( newX >= a.getX() && newX <= a.getX()+GameObjectFactory.ASTEROID_WIDTH &&
					newY >= a.getY() && newY <= a.getY()+GameObjectFactory.ASTEROID_HEIGHT) {
				return false;
			}
		}
		toBeAdded.setX(newX);
		toBeAdded.setY(newY);
		asteroidList.add(toBeAdded);
		gameView.getGamePanel().repaint();
		return true;
	}
	
	
	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void setBall(Ball ball)
	{
		this.ball = ball;
	}
	
	public List<Asteroid> getAsteroidList(){ return asteroidList;}

	public void setAsteroids(List<Asteroid> asteroidList){ this.asteroidList = asteroidList;}
}
