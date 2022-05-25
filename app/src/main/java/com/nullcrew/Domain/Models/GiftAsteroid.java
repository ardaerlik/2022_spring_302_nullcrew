package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.List;

import com.nullcrew.Domain.Controllers.GameController;

public class GiftAsteroid extends Asteroid {
	private int lives;

	public GiftAsteroid(double x, double y, int width, int height, double speed) {
		super(x, y, width, height, speed, Color.BLUE, AsteroidType.Gift);
		lives = 1;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {
		 
		gameController.appearAsteroid();

		List<Asteroid> list = gameController.getAsteroidList();

		list.remove(this);
		gameController.setAsteroids(list);
	}

	@Override
	public Asteroid clone() {
		return new GiftAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
	}
}
