package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class ChancePowerUp extends PowerUp{
	
	public ChancePowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}

	@Override
	public void use() {
		this.getGameController().getGameView().getTopPanel().getChance_label().setVisible(true);

		if(Game.getCurrentGame() == null) {
			return;
		}
		
		this.getGameController().setLives(
				this.getGameController().getLives() + 1);
	}
	@Override
	public void fall() {
		// TODO Auto-generated method stub
		this.setY(this.getY()+this.getVelocity());
	}

}
