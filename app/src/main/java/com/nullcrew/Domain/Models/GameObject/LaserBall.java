package com.nullcrew.Domain.Models.GameObject;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class LaserBall extends GameObject {
	private final double initialVelocityX = 0;
	private final double initialVelocityY = -7;

	private double velocityX;
	private double velocityY;
	
	public LaserBall(GameController gameController, double x, double y, int width, int height) {
		super(gameController, x, y, width, height);
	}
	
	public LaserBall(Document document) {
		super(document);
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
