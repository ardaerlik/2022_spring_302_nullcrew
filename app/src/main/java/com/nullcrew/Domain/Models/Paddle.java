package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class Paddle extends GameObject {
	private final int FINAL_VELOCITY = 20;
	private final int PADDLE_WIDTH;
	private final int PADDLE_HEIGHT;
	public int velocity = FINAL_VELOCITY;
	private int rotationDegree;
	public boolean onWrapPowerUp;
	public boolean onTallerPowerUp;
	public boolean onMagnet;
	
	public Paddle(GameController gameController,int x, int y, int width, int height) {
		super(gameController,x, y, width, height);
		rotationDegree = 0;
		PADDLE_HEIGHT = height;
		PADDLE_WIDTH=width;
		onWrapPowerUp=false;
		onTallerPowerUp=false;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		document.append("velocity", velocity)
				.append("rotationDegree", rotationDegree)
				.append("onWrapPowerUp", onWrapPowerUp)
				.append("onTallerPowerUp", onTallerPowerUp)
				.append("onMagnet", onMagnet);
		return document;
	}
	
}
