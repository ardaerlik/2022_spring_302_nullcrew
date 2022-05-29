package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.UI.Views.GameView;

public class TallerPowerUp extends PowerUp{
	private final float POWERUP_TIME=30f;
	public TallerPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fall() {
		// TODO Auto-generated method stub
		this.setY(this.getY()+this.getVelocity());

	}
	public float getBoostTime() {
		return POWERUP_TIME;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		this.getGameController().getPaddle().setWidth(
				(int)(this.getGameController().getPaddle().getWidth()*1.5f)
				);
		this.getGameController().getGameView().getTopPanel().getTaller_button().setVisible(false);
		
	}
	

}
