package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class PowerUp extends GameObject {

	public PowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public abstract void use();

	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}

}
