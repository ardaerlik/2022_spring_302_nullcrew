package com.nullcrew.Models;

import java.awt.*;

public abstract class Asteroid extends GameObject {
	private double speed;
	private Color color;

	public Asteroid(int x, int y, int width, int height, double speed, Color color) {
		super(x, y, width, height);
		this.speed = speed;
		this.color = color;
	}

	public double getSpeed() {
		return speed;
	}

	public abstract void hit();

	public Color getColor() {
		return color;
	}

	@Override
	public abstract Asteroid clone();
}
