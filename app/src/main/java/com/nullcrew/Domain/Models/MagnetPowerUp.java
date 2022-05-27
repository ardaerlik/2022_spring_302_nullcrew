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
				(GameObjectFactory.BALL_X-GameObjectFactory.PADDLE_X)+this.getGameController().getPaddle().x
				);
		this.getGameController().getBall().setY(
				GameObjectFactory.BALL_Y
				);
		this.getGameController().getBall().setVelocityX(0);
		this.getGameController().getBall().setVelocityY(0);
	}

}
