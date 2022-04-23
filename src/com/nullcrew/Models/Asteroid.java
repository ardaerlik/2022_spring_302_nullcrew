package com.nullcrew.Models;

import java.awt.*;

import com.nullcrew.Views.GameView;

public abstract class Asteroid extends GameObject {
	private double speed;
	private String type;
	private Color color;

	public Asteroid(int x, int y, int width, int height, double speed, String type, Color color) {
		super(x, y, width, height);
		this.speed = speed;
		this.type = type;
		this.color = color;
	}

	public double getSpeed() {
		return speed;
	}

	public abstract void hit(GameView gameView);

	public String getType(){
		return type;
	}

	public Color getColor() {
		return color;
	}
}
