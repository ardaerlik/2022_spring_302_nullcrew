package com.nullcrew.Domain.Models.GameObject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class GiftAsteroid extends Asteroid {
	public PowerUp powerup;
	
	public GiftAsteroid(GameController gameController,double x, double y, int width, int height, double speed, PowerUp powerup) {
		super(gameController,x, y, width, height, speed, Color.BLUE, AsteroidType.Gift);
		this.powerup=powerup;
		lives = 1;
	}
	
	public GiftAsteroid(Document document) {
		super(document);
		this.powerup = null;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) { 

		ArrayList<Asteroid> list = gameController.getAsteroidList();
		System.out.println(powerup);
		list.remove(this);
		gameController.setAsteroids(list);
		gameController.setDestroyedAsteroid(gameController.getDestroyedAsteroid() + 1);
		gameController.appearAlien();
	}

	@Override
	public Asteroid clone() {
		return new GiftAsteroid(this.getGameController(),this.x, this.y, this.width, this.height, super.getSpeed(),this.powerup);
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		if (powerup == null) {
			document.append("powerup", null);
		} else {
			document.append("powerup", powerup.getDocument());
		}
		return document;
	}
	
}
