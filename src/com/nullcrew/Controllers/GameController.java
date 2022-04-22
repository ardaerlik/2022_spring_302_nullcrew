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
	
	
	public void paddleHitBall(){
        if(new Rectangle(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight()).
                intersects(new Rectangle(paddle.getX(),paddle.getY(),paddle.getWidth()+1,paddle.getHeight()+1))){
            ball.setVelocityY((-ball.getVelocityY()));

        }
    }

    public Asteroid ballHitAsteroid() {
        if(getAsteroidList()==null) {
            return null;
        }
        if(getAsteroidList().size()==0) {
            return null;
        }

        for(Asteroid asteroid: getAsteroidList()) {
            if(asteroid==null) {
                continue;
            }
            if(new Rectangle(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight()).
                    intersects(new Rectangle(asteroid.getX(),asteroid.getY(),asteroid.getWidth()+1,asteroid.getHeight()+1))){


                    return asteroid;

            }

        }
        return null;
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
