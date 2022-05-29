package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class WrapPowerUp extends PowerUp{
	private final float POWERUP_TIME=120f;
	public WrapPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
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
}
