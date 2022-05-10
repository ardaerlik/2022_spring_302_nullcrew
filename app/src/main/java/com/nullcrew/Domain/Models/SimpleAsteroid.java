package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.List;

import com.nullcrew.UI.Views.GameView;

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
	public void hit(GameView gameView) {
		List<Asteroid> list = gameView.getGameController().getAsteroidList();

		list.remove(this);
		gameView.getGameController().setAsteroids(list);
	}

	@Override
	public Asteroid clone() {
		return new SimpleAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
	}

}
