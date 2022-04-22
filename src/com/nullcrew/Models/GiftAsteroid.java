package com.nullcrew.Models;

import java.awt.*;

public class GiftAsteroid extends Asteroid {
    private int lives;

    public GiftAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.BLUE);
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
        return new GiftAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
}
