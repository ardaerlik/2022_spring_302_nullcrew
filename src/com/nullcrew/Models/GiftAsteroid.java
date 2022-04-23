package com.nullcrew.Models;

import java.awt.*;
import java.util.List;

import com.nullcrew.Views.GameView;

public class GiftAsteroid extends Asteroid {
    private int lives;

    public GiftAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.BLUE, AsteroidType.Gift);
        lives = 1;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit(GameView gameView) {
    	
		List<Asteroid> list= gameView.getGameController().getAsteroidList();

    	list.remove(this);
		gameView.getGameController().setAsteroids(list);
    }

    @Override
    public Asteroid clone(){
        return new GiftAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
}
