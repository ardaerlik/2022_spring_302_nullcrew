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
			paddle.setX(paddle.getX() - 5);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}
	
	public void paddleRotated(MoveDirection direction) {
		// TODO: call paddle model and change rotations
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
