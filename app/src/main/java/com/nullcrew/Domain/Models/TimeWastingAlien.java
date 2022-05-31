package com.nullcrew.Domain.Models;

import java.awt.Color;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class TimeWastingAlien extends Alien {

	public TimeWastingAlien(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height, 0, Color.YELLOW, AlienType.TimeWasting);
	}
	
	public TimeWastingAlien(Document document) {
		super(document);
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
