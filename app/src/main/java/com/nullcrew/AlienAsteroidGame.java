package com.nullcrew;

import com.nullcrew.UI.Views.AppView;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.UI.Views.LoginView;
import com.nullcrew.UI.Views.MenuView;
import com.nullcrew.Utilities.DBManager;
import com.nullcrew.Utilities.Database;
import com.nullcrew.Utilities.DatabaseAdapter;
import com.nullcrew.Utilities.FileManager;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	private DatabaseAdapter databaseAdapter;
	private Database fileManager;

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
		databaseAdapter = new DatabaseAdapter(DBManager.getInstance());
		fileManager = FileManager.getInstance();
		databaseAdapter.connect();
//		changeView(null, new LoginView());
		changeView(null, new GameView());
//		changeView(null, new MenuView());
	}
	
	public void exitApp() {
		databaseAdapter.close();
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

	public DatabaseAdapter getDatabaseAdapter() {
		return databaseAdapter;
	}

	public void setDatabaseAdapter(DatabaseAdapter databaseAdapter) {
		this.databaseAdapter = databaseAdapter;
	}

	public Database getFileManager() {
		return fileManager;
	}

	public void setFileManager(Database fileManager) {
		this.fileManager = fileManager;
	}

}
