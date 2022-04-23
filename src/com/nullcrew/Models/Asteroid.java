package com.nullcrew.Models;

import java.awt.*;

public abstract class Asteroid extends GameObject {
	private double speed;
	private Color color;
	private AsteroidType type;

	public Asteroid(int x, int y, int width, int height, double speed, Color color, AsteroidType type) {
		super(x, y, width, height);
		this.speed = speed;
		this.color = color;
		this.type = type;
	}

	public double getSpeed() {
		return speed;
	}

	public Color getColor() {
		return color;
	}

	public AsteroidType getType() { return type; }

	public abstract void hit();

	@Override
	public abstract Asteroid clone();
}
