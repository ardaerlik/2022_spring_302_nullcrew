package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class Paddle extends GameObject {
	private int rotationDegree;
	private final int FINAL_VELOCITY = 20;
	private final int PADDLE_WIDTH;
	private final int PADDLE_HEIGHT;
	
	public int velocity = FINAL_VELOCITY;

	public boolean onWrapPowerUp;
	public boolean onTallerPowerUp;
	public boolean onMagnet;
	public Paddle(GameController gameController,int x, int y, int width, int height) {
		super(gameController,x, y, width, height);
		rotationDegree = 0;
		this.PADDLE_HEIGHT = height;
		this.PADDLE_WIDTH=width;
		onWrapPowerUp=false;
		onTallerPowerUp=false;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;

	}
}
