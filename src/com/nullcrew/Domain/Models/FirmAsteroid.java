package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.util.List;

import com.nullcrew.UI.Views.GameView;

public class FirmAsteroid extends Asteroid {
    private int lives;

    public FirmAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.CYAN, AsteroidType.Firm);
        lives = 3;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit(GameView gameView) {
    	
    	List<Asteroid> list= gameView.getGameController().getAsteroidList();
    	
    	if (this.getHeight() == 30) {
			this.setHeight(25);
			this.setWidth(25);
		} else if (this.getHeight() == 25) {
			this.setHeight(20);
			this.setWidth(20);
		} else {
			list.remove(this);
			gameView.getGameController().setAsteroids(list);
		}
    }

    @Override
    public Asteroid clone(){
        return new FirmAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
}
