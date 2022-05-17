package com.nullcrew.Domain.Models;

import java.awt.Color;

import com.nullcrew.Domain.Controllers.GameController;

public class ProtectingAlien extends Alien {
	
	
	

	public ProtectingAlien(double x, double y, int width, int height) {
		super(x, y, width, height, 20, Color.PINK, AlienType.Protecting);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hit(GameController gameController) {
		// TODO Auto-generated method stub
		
	}

	
	
}
