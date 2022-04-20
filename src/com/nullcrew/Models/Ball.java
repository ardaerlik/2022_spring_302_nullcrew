package com.nullcrew.Models;

public class Ball extends GameObject {

	private final int initialVelocityX = 5;
    private final int initialVelocityY = -5;
    
    private int velocityX;
    private int velocityY;
    
   
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		velocityX = initialVelocityX; velocityY = initialVelocityY;
	}


	public int getVelocityX() {
		return velocityX;
	}


	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}


	public int getVelocityY() {
		return velocityY;
	}


	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

}
