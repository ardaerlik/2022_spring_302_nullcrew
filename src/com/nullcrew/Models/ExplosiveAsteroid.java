package com.nullcrew.Models;

import java.awt.*;
import java.util.List;

import com.nullcrew.Views.GameView;

public class ExplosiveAsteroid extends Asteroid {
    private int lives;

    public ExplosiveAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, "explosive", Color.BLACK);
        lives = 1;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit(GameView gameView) {
    	/*
    	int minX = this.getX() - 200;
    	int maxX = this.getX() + 200;
    	
    	int minY = this.getY() - 200;
    	int maxY = this.getY() + 200;
    	
    	
		List<Asteroid> list = gameView.getGameController().getAsteroidList();
		
		if (list != null && list.size() != 0) {
			for (Asteroid astr : gameView.getGameController().getAsteroidList()) {
				if (astr.getX() > minX && astr.getX() < maxX && astr.getY() > minY && astr.getY() < maxY) {
					list.remove(astr);
					
				}
			}
			gameView.getGameController().setAsteroids(list);
		}
		*/
		
    	
    }
}
