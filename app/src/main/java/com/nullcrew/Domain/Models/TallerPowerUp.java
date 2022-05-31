package com.nullcrew.Domain.Models;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.UI.Views.GameView;

public class TallerPowerUp extends PowerUp{
	private final float POWERUP_TIME=30f;
	
	public TallerPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}

	@Override
	public void fall() {
		this.setY(this.getY()+this.getVelocity());

	}
	
	public float getBoostTime() {
		return POWERUP_TIME;
	}

	@Override
	public void use() {
		this.getGameController().getPaddle().setWidth(
				(int)(this.getGameController().getPaddle().getWidth()*1.5f)
				);
		this.getGameController().getGameView().getTopPanel().getTaller_button().setVisible(false);
		
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}

}
