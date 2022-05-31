package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class CooperativeAlien extends Alien implements AlienStrategy {
	
	public CooperativeAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.MAGENTA, AlienType.Cooperative);
	}
	
	public CooperativeAlien(Document document) {
		super(document);
	}

	@Override
	public void hit(GameController gameController) {
		gameController.setAlien(null);
	}

	@Override
	public void act(GameController gameController) {
		gameController.destroyAsteroidRow();
		gameController.setAlien(null);
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}
	
}
