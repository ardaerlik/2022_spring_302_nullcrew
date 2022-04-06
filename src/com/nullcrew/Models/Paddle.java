package com.nullcrew.Models;

public class Paddle extends GameObject {
	
	private int rotationDegree;
	
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
