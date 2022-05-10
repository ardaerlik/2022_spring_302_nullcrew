package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.List;

import com.nullcrew.Domain.Controllers.GameController;

public class SimpleAsteroid extends Asteroid {
	private int lives;

	public SimpleAsteroid(double x, double y, int width, int height, double speed) {
		super(x, y, width, height, speed, Color.RED, AsteroidType.Simple);
		lives = 1;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {
		List<Asteroid> list = gameController.getAsteroidList();

		list.remove(this);
		gameController.setAsteroids(list);
	}

	@Override
	public Asteroid clone() {
		return new SimpleAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
	}

}
