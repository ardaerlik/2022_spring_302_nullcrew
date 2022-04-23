package com.nullcrew.Domain.Models;

public class Paddle extends GameObject {
	
	private int rotationDegree;
	
	private final int FINAL_VELOCITY = 20;
	
	public int velocity = FINAL_VELOCITY;
	
	public Paddle(int x, int y, int width, int height) {
		super(x, y, width, height);
		rotationDegree = 0;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;
		
	}


}
