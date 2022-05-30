package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class ProtectingAlien extends Alien {
	
	private static final double SPEED = 7.2;
	
	public ProtectingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, SPEED, Color.PINK, AlienType.Protecting);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		double posX = (double) this.getX() - gameController.getBalls().get(0).getX();
		double posY = (double) this.getY() - gameController.getBalls().get(0).getY();
		double angle = Math.atan2(posY - 0, posX - (double) 1) * (180 / Math.PI);
		
		if (45d <= angle && angle <= 135d) {
			gameController.setAlien(null);
		}
	}

	@Override
	public void act(GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	
	
}
