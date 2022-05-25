package com.nullcrew.Domain.Models;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import com.nullcrew.Domain.Controllers.GameController;
public abstract class GameObject{
	protected ObjectShape object_shape;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private int initial_width;
	private int initial_height;
	private GameController gameController;

	public GameObject(GameController gameController, double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gameController = gameController;
		initial_width=width;
		initial_height=height;
		
		object_shape = new ObjectShape();
		object_shape.setRect(new Rectangle2D.Double(x,y,width,height));
		object_shape.setTransform(new AffineTransform());
		object_shape.setShape(object_shape.getTransform().createTransformedShape(object_shape.getRect()));
	}

	public void changeShape() {
		object_shape.setRect(new Rectangle2D.Double(x,y,width,height));
		object_shape.setShape(object_shape.getTransform().createTransformedShape(object_shape.getRect()));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		changeShape();
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		changeShape();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		changeShape();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		changeShape();
	}
	
	public ObjectShape getObjShape() {
		return object_shape;
	}
	
	public int getInitialWidth(){
		return initial_width;
	}
	
	public int getInitialHeight(){
		return initial_height;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
	
}
