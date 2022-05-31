package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class Ball extends GameObject {

	private final double initialVelocityX = 3;
	private final double initialVelocityY = -3;

	private double velocityX;
	private double velocityY;

	public Ball(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
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
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}

}
