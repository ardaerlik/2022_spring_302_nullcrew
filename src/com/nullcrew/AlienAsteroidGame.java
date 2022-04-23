package com.nullcrew;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();

	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}

		return instance;
	}

	public static void main(String[] args) {
	}

	private AlienAsteroidGame() {
	}

}
