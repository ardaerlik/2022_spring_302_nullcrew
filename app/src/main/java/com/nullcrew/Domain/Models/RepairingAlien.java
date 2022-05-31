package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class RepairingAlien extends Alien{
	
	public RepairingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.DARK_GRAY, AlienType.Repairing);
	}
	
	@Override
	public void hit(GameController gameController) {
		gameController.setAlien(null);
	}

	@Override
	public void act(GameController gameController) {
	}

	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}
	
}
