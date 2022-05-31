package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class TimeWastingAlien extends Alien implements AlienStrategy {
	private int actCount = 0;
	
	

	public TimeWastingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.YELLOW, AlienType.TimeWasting);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		gameController.setAlien(null);
	}

	@Override
	public void act(GameController gameController) {
		// TODO Auto-generated method stub
		actCount++;
		if (actCount == 1) {
			gameController.freezeAsteroids();
		}
		if (actCount%750 == 0) {
			gameController.unfreezeAsteroids();
		}
	}

	
	
}
