package com.nullcrew.Domain.Models;

import java.util.ArrayList;
import java.util.List;

import com.nullcrew.Domain.Controllers.GameController;

public class MagnetPowerUp extends PowerUp{

	public MagnetPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		ArrayList<Ball> list= new ArrayList<Ball>();
		Ball ball= GameObjectFactory.createBall();
		this.getGameController().freezeBallOnPaddle(ball);
		list.add(ball);
		this.getGameController().setBalls(list);
		this.getGameController().getGameView().getTopPanel().getMagnet_button().setVisible(false);
	}

	@Override
	public void fall() {
		// TODO Auto-generated method stub
		this.setY(this.getY()+this.getVelocity());
	}

}
