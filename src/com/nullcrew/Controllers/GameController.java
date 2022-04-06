package com.nullcrew.Controllers;

import com.nullcrew.Models.*;
import com.nullcrew.Utilities.*;
import com.nullcrew.Views.*;

public class GameController {
	private GameView gameView;
	private Paddle paddle;
	
	public GameController(GameView gameView) {
		this.gameView = gameView;
	}

	public void paddleMoved(MoveDirection direction) {
		switch (direction) {
		case RIGHT: {
			paddle.setX(paddle.getX() + 5);
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
		switch (direction) {
		case UP: {
			paddle.setRotationDegree(paddle.getRotationDegree() + 5);
			break;
		}
		case DOWN: {
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
}
