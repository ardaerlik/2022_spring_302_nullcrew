package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.nullcrew.Domain.Controllers.GameController;

public class FirmAsteroid extends Asteroid {

	public FirmAsteroid(GameController gameController,double x, double y, int width, int height, double speed) {
		super(gameController,x, y, width, height, speed, Color.CYAN, AsteroidType.Firm);
		lives = 3;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {

		ArrayList<Asteroid> list = gameController.getAsteroidList();

		if (this.getHeight() == 30) {
			this.setHeight(25);
			this.setWidth(25);
		} else if (this.getHeight() == 25) {
			this.setHeight(20);
			this.setWidth(20);
		} else {
			list.remove(this);
			gameController.setAsteroids(list);
		}
	}

	@Override
	public Asteroid clone() {
		return new FirmAsteroid(this.getGameController(),this.x, this.y, this.width, this.height, super.getSpeed());
	}
}
