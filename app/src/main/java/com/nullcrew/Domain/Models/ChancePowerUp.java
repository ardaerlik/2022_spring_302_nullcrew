package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public class ChancePowerUp extends PowerUp{
	
	public ChancePowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() {
		System.out.println("Chance activated");
		// TODO Auto-generated method stub
		if(this.getGameController().getGame()==null) {
			return;
		}
		this.getGameController().getGame().setLives(
				this.getGameController().getGame().getLives()+1
				);
	}


}
