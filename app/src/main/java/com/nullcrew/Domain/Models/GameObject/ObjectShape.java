package com.nullcrew.Domain.Models.GameObject;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ObjectShape {
	protected Shape shape;
	protected AffineTransform transform;
	protected Rectangle2D rect;
	public Shape getShape() {return shape;}
	public void setShape(Shape s) {shape=s;}
	public AffineTransform getTransform() {return transform;}
	public void setTransform(AffineTransform t) {transform=t;}
	public Rectangle2D getRect() {return rect;}
	public void setRect(Rectangle2D r) {rect=r;}
}
