package com.nullcrew.Controllers;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	
	public GameController(GameView gameView) {
		this.gameView = gameView;
	}

	public void paddleMoved(MoveDirection direction) {
		if(GamePanel.gameMode==GameMode.PAUSED) {
			return;
		}
		switch (direction) {
		case RIGHT: {		
			if (paddle.getX() + gameView.getGameController().getPaddle().getWidth() < gameView.getFrame().getWidth()) {
				paddle.setX(paddle.getX() + paddle.velocity);
			}
			break;
		}
		case LEFT: {
			if (paddle.getX() > 0) {
				paddle.setX(paddle.getX() - paddle.velocity);
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
	
    public void paddleHitBall(){
        if(new Rectangle(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight()).
        		intersects(new Rectangle(paddle.getX(),paddle.getY(),paddle.getWidth(),paddle.getHeight()))){
        	ball.setVelocityY((-ball.getVelocityY()));
           
        }
    }
	
    public Asteroid ballHitAsteroid() {
    	if(getAsteroidList()==null || getAsteroidList().size()==0) {
    		return null;
    	}
    	

    	for(Asteroid asteroid: getAsteroidList()) {
    		if(asteroid==null) {
    			continue;
    		}
            if(new Rectangle(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight()).
            		intersects(new Rectangle(asteroid.getX(),asteroid.getY(),asteroid.getWidth(),asteroid.getHeight()))){
            		
    				asteroid.hit(gameView);
            		
            		return asteroid;
            }
         
    	}
    	return null;
    }
    
    public void reflectFromAsteroid(Asteroid collided_asteroid) {

		if(collided_asteroid==null) {
			return;
		}

		double posX = (double)collided_asteroid.getX()-ball.getX();
		double posY=(double)collided_asteroid.getY()-ball.getY();
		double angle = Math.atan2( posY - 0, posX - (double)1 ) * ( 180 / Math.PI );
		if(45d<=angle&&angle<=135d) {
			ball.setVelocityY(-ball.getVelocityY());
		}
		if((135d<=angle&&angle<=180d)||(0>=angle&&angle>=-45d)) {
			ball.setVelocityX(-ball.getVelocityX());
		}
		if(-45d>=angle&&angle>=-135d) {
			ball.setVelocityY(-ball.getVelocityY());
		}
		if((0d<=angle&&angle<=45d)||(-135d>=angle&&angle<=-180d)) {
			ball.setVelocityX(-ball.getVelocityX());
		}
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
