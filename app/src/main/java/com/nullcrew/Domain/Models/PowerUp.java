package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public abstract class PowerUp extends GameObject {
	public float velocity;
	public boolean canFall;
	public boolean canbeUsed;
	
	public PowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		velocity= gameController.getPaddle().getHeight()/8f;
		canFall=false;
		canbeUsed=false;
	}
	
	public PowerUp(Document document) {
		super(document);
		this.velocity = (float) document.get("velocity");
		this.canFall = document.getBoolean("canFall", false);
		this.canbeUsed = document.getBoolean("canbeUsed", false);
	}
	
	public abstract void fall();
	
	public abstract void use();
	
	public float getVelocity() {
		return velocity;
	}
	
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		document.append("velocity", velocity)
				.append("canFall", canFall)
				.append("canbeUsed", canbeUsed);
		return document;
	}
	
}
