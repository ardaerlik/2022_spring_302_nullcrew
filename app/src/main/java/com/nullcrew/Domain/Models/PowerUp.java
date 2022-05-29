package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class PowerUp extends GameObject {
	public float velocity;
	public boolean canFall;
	public boolean canbeUsed;
	public PowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
		velocity= gameController.getPaddle().getHeight()/8f;
		canFall=false;
		canbeUsed=false;
	}
	public abstract void fall();
	public abstract void use();
	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
}
