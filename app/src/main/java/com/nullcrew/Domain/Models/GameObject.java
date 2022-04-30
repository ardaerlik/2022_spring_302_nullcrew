package com.nullcrew.Domain.Models;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private int initial_width;
	private int initial_height;
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initial_width=width;
		initial_height=height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public int getInitialWidth(){
		return initial_width;
	}
	public int getInitialHeight(){
		return initial_height;
	}
}
