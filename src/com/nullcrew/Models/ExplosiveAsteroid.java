package com.nullcrew.Models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nullcrew.Views.GameView;

public class ExplosiveAsteroid extends Asteroid {
    private int lives;

    public ExplosiveAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.BLACK, AsteroidType.Explosive);
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
        return new ExplosiveAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
	
    public void hit_nearby(GameView gameView) {
    	
    	int minX = this.getX() - 200;
    	int maxX = this.getX() + 200;
    	
    	int minY = this.getY() - 200;
    	int maxY = this.getY() + 200;

    	
    	List<Asteroid> list = gameView.getGameController().getAsteroidList();
    	List<Asteroid> temp_list = list.stream().map(x->(Asteroid) x).collect(Collectors.toList());
    	
		if (list != null && list.size() != 0) {
			for (Asteroid astr :list) {
				if(astr instanceof ExplosiveAsteroid) {
					continue;
				}
				if(new Rectangle(this.x,this.y,100,100).
		        		intersects(new Rectangle(astr.x,astr.y,astr.getWidth(),astr.getHeight()))) {
					 
						temp_list.remove(astr);
				}
			
			}
		
		}
    	
		gameView.getGameController().setAsteroids(temp_list);
    }
    
}
