package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class RepairingAlien extends Alien{
	
	

	public RepairingAlien(double x, double y, int width, int height) {
		super(x, y, width, height, 0, Color.DARK_GRAY, AlienType.Repairing);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		gameController.setAlien(null);
	}

	
	
}
