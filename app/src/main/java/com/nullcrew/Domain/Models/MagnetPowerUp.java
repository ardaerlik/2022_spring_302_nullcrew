package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class MagnetPowerUp extends PowerUp{

	public MagnetPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		this.getGameController().getBall().setX(
				this.getGameController().getPaddle().x
				);
		this.getGameController().getBall().setY(
				this.getGameController().getPaddle().y
				);
	}

}
