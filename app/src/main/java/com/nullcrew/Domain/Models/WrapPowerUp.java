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
	}
	public float getBoostTime() {
		return POWERUP_TIME;
	}
}
