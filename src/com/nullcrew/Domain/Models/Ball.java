package com.nullcrew.Domain.Models;

public class Ball extends GameObject {

	private final int initialVelocityX = 3;
	private final int initialVelocityY = -3;

	private int velocityX;
	private int velocityY;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		velocityX = initialVelocityX;
		velocityY = initialVelocityY;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

}