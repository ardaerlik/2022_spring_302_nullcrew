package com.nullcrew.Models;

public class GameObjectFactory {
	
	public static Paddle createPaddle() {
		return new Paddle(100, 470, 120, 10);
	}
	
	public static Ball createBall() {
		return new Ball(10, 10, 10, 10);
	}

}
