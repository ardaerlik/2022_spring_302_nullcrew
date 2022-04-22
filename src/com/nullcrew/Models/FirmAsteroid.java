package com.nullcrew.Models;

import java.awt.*;

public class FirmAsteroid extends Asteroid {
    private int lives;

    public FirmAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, Color.CYAN);
        lives = 3;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit() {

    }

    @Override
    public Asteroid clone(){
        return new FirmAsteroid(this.x, this.y, this.width, this.height, super.getSpeed());
    }
}
