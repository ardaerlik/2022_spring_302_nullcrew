package com.nullcrew.Models;

import java.awt.*;

public class SimpleAsteroid extends Asteroid {
    private int lives;

    public SimpleAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.RED, AsteroidType.Simple);
        lives = 1;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit() {

    }

    @Override
    public Asteroid clone(){
        return new SimpleAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
}
