package com.nullcrew.Domain.Models;

import java.util.Random;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.UI.Views.GamePanel;

public class GangofBallPowerUp extends PowerUp{

	public GangofBallPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		for(int a=0;a<10;a++) {

			Ball ball= GameObjectFactory.createBall();
			ball.setX(this.getGameController().getBall().x);
			ball.setY(this.getGameController().getBall().y);
			ball.setVelocityX(
					ball.getVelocityY()*Math.cos(Math.toRadians(new Random().nextDouble()*360f))
					);
			ball.setVelocityY(
					ball.getVelocityX()*Math.sin(Math.toRadians(new Random().nextDouble()*360f))
					);
			GamePanel.list_objects.add(ball);
		}
		// TODO Auto-generated method stub

	}
}
