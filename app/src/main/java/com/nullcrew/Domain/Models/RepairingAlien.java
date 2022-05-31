package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class RepairingAlien extends Alien implements AlienStrategy {
	private int actCount = 0;

	

	public RepairingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.DARK_GRAY, AlienType.Repairing);
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
		if (gameController.getDestroyedAsteroid() == 0) return;	
		
		if (actCount%250 == 0) gameController.addNewSimpleAsteroid();;
		
	}

	
	
}
