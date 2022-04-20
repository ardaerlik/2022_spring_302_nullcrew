package com.nullcrew.Controllers;

import java.awt.Graphics2D;
import java.util.List;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Models.*;
import com.nullcrew.Utilities.*;
import com.nullcrew.Views.*;

public class GameController {
	private GameView gameView;
	private Paddle paddle;
	private List<Asteroid> asteroidList;
	
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

	public List<Asteroid> getAsteroidList(){ return asteroidList;}

	public void setAsteroids(List<Asteroid> asteroidList){ this.asteroidList = asteroidList;}
}
