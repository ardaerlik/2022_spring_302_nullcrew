package com.nullcrew;

import org.bson.types.ObjectId;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;
import com.nullcrew.UI.Views.AppView;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.UI.Views.MenuView;
import com.nullcrew.UI.Views.LoginView;
import com.nullcrew.UI.Views.NewGameView;
import com.nullcrew.UI.Views.HelpView;
import com.nullcrew.Utilities.DBManager;
import com.nullcrew.Utilities.DataStrategy;
import com.nullcrew.Utilities.FileManager;

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
//		dbManager = DBManager.getInstance();
//		dataStrategy = dbManager;
//
//		dbManager.connectDB();
//		changeView(null, new GameView());
		FileManager fileManager = FileManager.getInstance();
		fileManager.setUser(new User());
//		Game game = new Game(null, null, null, null);
//		game.setLives(10);
//		game.setScore(323);
//		fileManager.saveTheGame(game);
//		System.out.println(fileManager.getUser().getSavedGameIds().get(0));
		Game game = fileManager.loadTheGame(new ObjectId("6292868ba613981bac5b3659"));
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
