package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class Paddle extends GameObject {

	private int rotationDegree;
	private final int FINAL_VELOCITY = 20;
	public int velocity = FINAL_VELOCITY;

	public Paddle(GameController gameController,int x, int y, int width, int height) {
		super(gameController,x, y, width, height);
		rotationDegree = 0;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;

	}

}
