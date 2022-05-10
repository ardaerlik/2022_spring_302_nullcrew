package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class Asteroid extends GameObject {
	private double speed;
	private Color color;
	private AsteroidType type;
	private String asteroidType;

	public Asteroid(double x, double y, int width, int height, double speed, Color color, AsteroidType type) {
		super(x, y, width, height);
		this.speed = speed;
		this.color = color;
		this.type = type;
		this.asteroidType = type.toString();
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

	@Override
	public abstract Asteroid clone();
	
	public Document getDocument() {
		Document document = new Document()
				.append("speed", speed)
				.append("type", asteroidType);
		
		return document;
	}
}
