package com.nullcrew.Domain.Models;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public abstract class ObjectShape {
	protected Shape shape;
	protected AffineTransform transform;
	protected Rectangle2D rect;
	public Shape getShape() {return shape;}
	public AffineTransform getTransform() {return transform;}
	public Rectangle2D getRect() {return rect;}
}
