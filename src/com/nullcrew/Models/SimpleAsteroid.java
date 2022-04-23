package com.nullcrew.Models;

import java.awt.*;
import java.util.List;

import com.nullcrew.Views.GameView;

public class SimpleAsteroid extends Asteroid {
    private int lives;

    public SimpleAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, "simple", Color.RED);
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
}
