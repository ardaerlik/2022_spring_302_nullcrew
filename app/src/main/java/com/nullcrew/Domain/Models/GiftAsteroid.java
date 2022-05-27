package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.List;

import com.nullcrew.Domain.Controllers.GameController;

public class GiftAsteroid extends Asteroid {
	private int lives;
	public PowerUp powerup;
	public GiftAsteroid(GameController gameController,double x, double y, int width, int height, double speed,PowerUp powerup) {
		super(gameController,x, y, width, height, speed, Color.BLUE, AsteroidType.Gift);
		this.powerup=powerup;
		lives = 1;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {

		List<Asteroid> list = gameController.getAsteroidList();
		System.out.println(powerup);
		list.remove(this);
		gameController.setAsteroids(list);
	}

	@Override
	public Asteroid clone() {
		return new GiftAsteroid(this.getGameController(),this.x, this.y, this.width, this.height, super.getSpeed(),this.powerup);
	}
}
