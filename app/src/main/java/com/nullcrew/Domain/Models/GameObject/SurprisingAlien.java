package com.nullcrew.Domain.Models.GameObject;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class SurprisingAlien extends Alien {
	private int actCount = 0;

	public SurprisingAlien(GameController gameController, double x, double y, int width, int height) {
		super(gameController, x, y, width, height, 0, Color.lightGray, AlienType.Surprising);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		if (gameController.getDestroyedAsteroid() > gameController.getInitialAsteroidCount()/2 &&
				gameController.getDestroyedAsteroid() < (gameController.getInitialAsteroidCount()*6)/10) {
			double posX = (double) this.getX() - gameController.getBalls().get(0).getX();
			double posY = (double) this.getY() - gameController.getBalls().get(0).getY();
			double angle = Math.atan2(posY - 0, posX - (double) 1) * (180 / Math.PI);
			
			if (45d <= angle && angle <= 135d) {
				gameController.setAlien(null);
			}
		} else {
			gameController.setAlien(null);
		}
	}

	@Override
	public void act(GameController gameController) {
		// TODO Auto-generated method stub
		actCount++;
		if (gameController.getDestroyedAsteroid() < (gameController.getInitialAsteroidCount() * 3)/10) {
			gameController.destroyAsteroidRow();
			gameController.setAlien(null);
		} else if (gameController.getDestroyedAsteroid() > (gameController.getInitialAsteroidCount() * 7)/10) {
			if (actCount == 1) gameController.freezeAsteroids();
			if (actCount%250 == 0) gameController.addNewSimpleAsteroid();
			if (actCount%750 == 0) gameController.unfreezeAsteroids();
			
		} else if (gameController.getDestroyedAsteroid() > gameController.getInitialAsteroidCount()/2 &&
				gameController.getDestroyedAsteroid() < (gameController.getInitialAsteroidCount()*6)/10) {
			this.setY(820);
			this.setSpeed(7.2);
		} else if (gameController.getDestroyedAsteroid() < gameController.getInitialAsteroidCount()/2 &&
				gameController.getDestroyedAsteroid() > (gameController.getInitialAsteroidCount()*4)/10) {
			if (actCount%250 == 0) gameController.addNewSimpleAsteroid();
		} else {
			if (actCount%250 == 0) gameController.setAlien(null);
		}
		
	}



}
