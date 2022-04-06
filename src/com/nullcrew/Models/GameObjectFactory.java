package com.nullcrew.Models;

public class GameObjectFactory {
	
	public static Paddle createPaddle() {
		return new Paddle(100, 470, 120, 10);
	}

}
