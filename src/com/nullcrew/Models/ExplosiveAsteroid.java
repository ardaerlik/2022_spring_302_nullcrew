package com.nullcrew.Models;

import java.awt.*;

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
    public void hit() {

    }
}
