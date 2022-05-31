package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class Asteroid extends GameObject {
	private double speed;
	private Color color;
	private AsteroidType type;
	private String asteroidType;
	protected int lives;
	private boolean freezed = false;
	
	public Asteroid(GameController gameController,double x, double y, int width, int height, double speed, Color color, AsteroidType type) {
		super(gameController,x, y, width, height);
		this.speed = speed;
		this.color = color;
		this.type = type;
		this.asteroidType = type.toString();
	}
	
	public void setLives(int lives) {
		this.lives=lives;
	}
	
	public double getSpeed() {
		return speed;
	}

	public abstract void hit(GameController gameController);

	public Color getColor() {
		return color;
	}

	public AsteroidType getType() {
		return type;
	}
	
	public Boolean getFreezed() {
		return freezed;
	}
	
	public void setFreezed(Boolean freezed) {
		this.freezed = freezed;
	}
	
	@Override
	public abstract Asteroid clone();
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		document.append("speed", speed)
				.append("type", asteroidType);
		
		return document;
	}
	
}
