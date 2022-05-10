package com.nullcrew;

import com.nullcrew.UI.Views.AppView;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.UI.Views.MenuView;
import com.nullcrew.UI.Views.LoginView;
import com.nullcrew.UI.Views.NewGameView;
import com.nullcrew.Utilities.DBManager;
import com.nullcrew.Utilities.DataStrategy;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	private DBManager dbManager;
	private DataStrategy dataStrategy;

	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}

		return instance;
	}

	public static void main(String[] args) {
		getInstance().startApp();
	}
	
	private AlienAsteroidGame() {
	}
	
	public void startApp() {
		dbManager = DBManager.getInstance();
		dataStrategy = dbManager;
		
		dbManager.connectDB();
		changeView(null, new GameView());
	}
	
	public void exitApp() {
		dbManager.closeDB();
	}
	
	public void changeView(AppView from, AppView to) {
		if (from == null) {
			to.startView();
			return;
		}
		
		if (to == null) {
			from.endView();
			exitApp();
			return;
		}
		
		to.startView();
		from.endView();
	}

	public DataStrategy getDataStrategy() {
		return dataStrategy;
	}

	public void setDataStrategy(DataStrategy dataStrategy) {
		this.dataStrategy = dataStrategy;
	}

}
