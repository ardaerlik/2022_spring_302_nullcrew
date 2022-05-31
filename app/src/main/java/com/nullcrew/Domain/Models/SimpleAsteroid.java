package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class SimpleAsteroid extends Asteroid {

	public SimpleAsteroid(GameController gameController,double x, double y, int width, int height, double speed) {
		super(gameController,x, y, width, height, speed, Color.RED, AsteroidType.Simple);
		lives = 1;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {
		ArrayList<Asteroid> list = gameController.getAsteroidList();

		list.remove(this);
		gameController.setAsteroids(list);
	}

	@Override
	public Asteroid clone() {
		return new SimpleAsteroid(this.getGameController(),this.x, this.y, this.width, this.height, super.getSpeed());
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}

}
