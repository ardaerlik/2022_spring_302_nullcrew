package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public class DatabaseAdapter implements Database {
	private DBManager dbManager = DBManager.getInstance();

	public DatabaseAdapter(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public void connect() {
		dbManager.connectDB();
	}
	
	public void close() {
		dbManager.closeDB();
	}

	@Override
	public void saveTheGame(Game game) {
		dbManager.saveTheGame(game);
	}

	@Override
	public void loadTheGames() {
		dbManager.loadTheGames();
	}

	public void registerUser(String email, String password, String forgotKey) {
		dbManager.registerUser(email, password, forgotKey);
	}

	public void loginUser(String email, String password) {
		dbManager.loginUser(email, password);
	}

	public void resetPassword(String email, String newPassword, String forgotKey) {
		dbManager.resetPassword(email, newPassword, forgotKey);
	}

	public void subscribeAuthObserver(AuthObserver observer) {
		dbManager.subscribeAuthObserver(observer);
		
	}

	public void notifyAuthObservers(String response) {
		dbManager.notifyAuthObservers(response);
	}

	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		dbManager.subscribeSaveLoadObserver(observer);
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		dbManager.notifySaveLoadObserver(response);
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

}
