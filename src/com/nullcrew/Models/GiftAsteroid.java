package com.nullcrew.Models;

import java.awt.*;

import com.nullcrew.Views.GameView;

public class GiftAsteroid extends Asteroid {
    private int lives;

    public GiftAsteroid(int x, int y, int width, int height, double speed) {
        super(x, y, width, height, speed, "gift", Color.BLUE);
        lives = 1;
    }

    public int getLives(){
        return lives;
    }

    @Override
    public void hit(GameView gameView) {

    }
}
