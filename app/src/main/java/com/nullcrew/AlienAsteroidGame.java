package com.nullcrew;

import com.nullcrew.Utilities.DBManager;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	private DBManager dbManager;

	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}

		return instance;
	}

	public static void main(String[] args) {
		getInstance().startApp();
	}
	
	public void startApp() {
		dbManager = DBManager.getInstance();
		dbManager.connectDB();
	}
	
	public void exitApp() {
		dbManager.closeDB();
	}
	
	public void changeView() {
		
	}

	private AlienAsteroidGame() {
	}

}
