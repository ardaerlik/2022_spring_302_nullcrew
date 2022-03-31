package com.nullcrew;

public final class AlienAsteroidGame {
	
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	
	private AlienAsteroidGame() {}

	public static void main(String[] args) {

	}
	
	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}
		
		return instance;
	}

}
