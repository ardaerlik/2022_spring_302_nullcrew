package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class RepairingAlien extends Alien{
	float alienStartTime = 0;
	Boolean startedTime = false;
	int index = 5;
	

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
		if (gameController.getDestroyedAsteroid() == 0) return;	
		
		gameController.addNewSimpleAsteroid();
	}

	
	
}
