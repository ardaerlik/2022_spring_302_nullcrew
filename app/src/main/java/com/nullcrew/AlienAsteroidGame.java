package com.nullcrew;

import com.nullcrew.UI.Views.GameView;
import com.nullcrew.Utilities.DBManager;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();

	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}

		return instance;
	}

	public static void main(String[] args) {
		System.out.println("Hello gradle");
		DBManager dbms = DBManager.getInstance();
		dbms.connectDB();
		dbms.closeDB();
		GameView.main(null);
	}

	private AlienAsteroidGame() {
	}

}
