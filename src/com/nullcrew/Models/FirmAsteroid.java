package com.nullcrew.Models;

import java.awt.*;

public class FirmAsteroid extends Asteroid {
    private int lives;

    public FirmAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, "firm", Color.CYAN);
        lives = 3;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit() {

    }
}
