package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.UI.Views.GameView;

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

	public abstract void hit(GameView gameView);

	public Color getColor() {
		return color;
	}

	public AsteroidType getType() { return type; }

	@Override
	public abstract Asteroid clone();
}
