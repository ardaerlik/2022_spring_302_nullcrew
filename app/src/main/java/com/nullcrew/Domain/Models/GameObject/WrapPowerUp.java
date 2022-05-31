package com.nullcrew.Domain.Models.GameObject;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class WrapPowerUp extends PowerUp{
	private final float POWERUP_TIME = 120f;
	
	public WrapPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}
	
	public WrapPowerUp(Document document) {
		super(document);
	}

	@Override
	public void use() {
		this.getGameController().getPaddle().onWrapPowerUp=true;
		this.getGameController().getGameView().getTopPanel().getWrap_label().setVisible(true);
	}
	
	public float getBoostTime() {
		return POWERUP_TIME;
	}
	
	@Override
	public void fall() {
		// TODO Auto-generated method stub
		this.setY(this.getY()+this.getVelocity());
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}
	
}
