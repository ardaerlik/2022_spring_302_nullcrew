package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class Alien extends GameObject implements AlienStrategy {
	private double speed;
	private Color color;
	private AlienType type;
	private String alienType;

	public Alien(GameController gameController, double x, double y, int width, int height, double speed, Color color, AlienType type) {
		super(gameController, x, y, width, height);
		this.speed = speed;
		this.color = color;
		this.type = type;
		this.alienType = type.toString();	
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Color getColor() {
		return color;
	}

	public AlienType getType() {
		return type;
	}

	public Document getDocument() {
		Document document = new Document()
				.append("speed", speed)
				.append("type", alienType);
		
		return document;
	}
	
	

}
