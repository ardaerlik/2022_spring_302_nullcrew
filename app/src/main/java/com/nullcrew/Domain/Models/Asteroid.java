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
	
	public Asteroid(Document document) {
		super(document);
		this.speed = document.getDouble("speed");
		this.asteroidType = document.getString("asteroidType");
		this.lives = document.getInteger("lives", 0);
		this.freezed = document.getBoolean("freezed", false);
		this.color = Color.BLACK;
		
		switch (asteroidType) {
		case "Explosive":
			this.type = AsteroidType.Explosive;
			break;
		case "Firm":
			this.type = AsteroidType.Firm;
			break;
		case "Gift":
			this.type = AsteroidType.Gift;
			break;
		case "Simple":
			this.type = AsteroidType.Simple;
			break;
		}
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
				.append("asteroidType", asteroidType)
				.append("lives", lives)
				.append("freezed", freezed);
		
		return document;
	}
	
}
