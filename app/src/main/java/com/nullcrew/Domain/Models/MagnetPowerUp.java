package com.nullcrew.Domain.Models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;

public class MagnetPowerUp extends PowerUp{

	public MagnetPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}
	
	public MagnetPowerUp(Document document) {
		super(document);
	}

	@Override
	public void use() {
		ArrayList<Ball> list= new ArrayList<Ball>();
		Ball ball= GameObjectFactory.createBall();
		this.getGameController().freezeBallOnPaddle(ball);
		list.add(ball);
		this.getGameController().setBalls(list);
		this.getGameController().getGameView().getTopPanel().getMagnet_button().setVisible(false);
	}

	@Override
	public void fall() {
		this.setY(this.getY()+this.getVelocity());
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		return document;
	}

}
