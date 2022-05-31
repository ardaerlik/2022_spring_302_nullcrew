package com.nullcrew.Domain.Models.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.UI.Views.GameView.GamePanel;

public class GangofBallPowerUp extends PowerUp{

	public GangofBallPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}
	
	public GangofBallPowerUp(Document document) {
		super(document);
	}

	@Override
	public void use() {
		this.getGameController().getGameView().getTopPanel().getGangballs_label().setVisible(true);
		for(int a=0;a<10;a++) {

			Ball ball= GameObjectFactory.createBall();
			ball.setX(this.getGameController().getBalls().get(0).x);
			ball.setY(this.getGameController().getBalls().get(0).y);
			ball.setVelocityX(
					this.getGameController().getBalls().get(0).getVelocityY()*Math.cos(Math.toRadians(new Random().nextDouble()*360f))*1.5f
					);
			ball.setVelocityY(
					this.getGameController().getBalls().get(0).getVelocityX()*Math.sin(Math.toRadians(new Random().nextDouble()*360f))*1.5f
					);
			this.getGameController().getList_objects().add(ball);
			ArrayList<Ball> temp_list= getGameController().getBalls();
			temp_list.add(ball);
			this.getGameController().setBalls(temp_list);
			System.out.println("Created balls");
		}
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
