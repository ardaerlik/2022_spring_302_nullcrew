package com.nullcrew;

import com.nullcrew.UI.Views.AppView;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.UI.Views.LoginView;
import com.nullcrew.Utilities.DBManager;
import com.nullcrew.Utilities.Database;
import com.nullcrew.Utilities.DatabaseAdapter;
import com.nullcrew.Utilities.FileManager;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	private DatabaseAdapter databaseAdapter;
	private Database fileManager;

		
	/**
	* The value is assigned for the instance value in AlienAsteroidGame,
	* it is returned after creation.
	* 
	* @return instance is the value it is stable.
	*/
	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}

		return instance;
	}
	
	
	/**
	* Returns the initial property that will start the application by taking the values.
	* 
	* @param  args that support String. 
	*/
	public static void main(String[] args) {
		getInstance().startApp();
	}
	
	
	/**
	* Private AlienAsteroidGame
	*/
	private AlienAsteroidGame() {
	}
	
	/**
	* It helps to take the initial step to start the application.
	*/
	public void startApp() {
		databaseAdapter = new DatabaseAdapter(DBManager.getInstance());
		fileManager = FileManager.getInstance();
		databaseAdapter.connect();
		changeView(null, new GameView());
	}
	
	/**
	* It provides the necessary run to exit the application.
	*/
	public void exitApp() {
		databaseAdapter.close();
	}
	
	
	/**
	* Allows the View property to change.
	*
	* @param to is destination for AppView.
	* @param from is location for AppView.
	*/
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
