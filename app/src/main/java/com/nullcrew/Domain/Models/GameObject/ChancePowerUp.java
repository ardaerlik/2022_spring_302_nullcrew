package com.nullcrew.Domain.Models.GameObject;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.Domain.Models.Game.Game;

public class ChancePowerUp extends PowerUp {
	
	public ChancePowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}
	
	public ChancePowerUp(Document document) {
		super(document);
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
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}

}
