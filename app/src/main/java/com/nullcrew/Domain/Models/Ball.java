package com.nullcrew.Domain.Models;

public class Ball extends GameObject {

	private final double initialVelocityX = 3;
	private final double initialVelocityY = -3;

	private double velocityX;
	private double velocityY;

	public Ball(double x, double y, int width, int height) {
		super(x, y, width, height);
		velocityX = initialVelocityX;
		velocityY = initialVelocityY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

}
