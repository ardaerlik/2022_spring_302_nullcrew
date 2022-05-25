package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class TimeWastingAlien extends Alien {
	
	
	

	public TimeWastingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.YELLOW, AlienType.TimeWasting);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	
	
}
