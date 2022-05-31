package com.nullcrew.Domain.Models;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.UI.Views.GamePanel;

public class LaserPowerUp extends PowerUp{
	public int total_shots = 5;
	
	public LaserPowerUp(GameController gameController,double x, double y, int width, int height) {
		super(gameController,x, y, width, height);
	}
	
	public LaserPowerUp(Document document) {
		super(document);
		this.total_shots = document.getInteger("total_shots", 5);
	}

	@Override
	public void use() {
		if(total_shots<=0) {
			return;
		}
		this.getGameController().getGameView().getTopPanel().getLaser_label().setVisible(true);
		ArrayList<LaserBall> laser_list= new ArrayList();
		for(int a=0;a<total_shots;a++) {
			LaserBall left_laser= GameObjectFactory.createLaserBall(this.getGameController(),
					this.getGameController().getPaddle().x,
					this.getGameController().getPaddle().y
					, 10, 20);
			LaserBall right_laser= GameObjectFactory.createLaserBall(this.getGameController(),
					this.getGameController().getPaddle().x+this.getGameController().getPaddle().getWidth(),
					this.getGameController().getPaddle().y
					, 10, 20);
			right_laser.setVelocityY(-7+a);
			left_laser.setVelocityY(-7+a);
			this.getGameController().getList_objects().add(left_laser);
			this.getGameController().getList_objects().add(right_laser);
			
			laser_list.add(left_laser);
			laser_list.add(right_laser);
			
		}
		this.getGameController().setLaser_balls(laser_list);
	}

	@Override
	public void fall() {
		// TODO Auto-generated method stub
		this.setY(this.getY()+this.getVelocity());
	}
	
	@Override
	public Document getDocument() {
		Document document = super.getDocument();
		document.append("total_shots", total_shots);
		return document;
	}
	
}
