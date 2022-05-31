package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public class DatabaseAdapter implements Database {
	private DBManager dbManager;

	public DatabaseAdapter(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public void close() {
		dbManager.closeDB();
	}

	public void connect() {
		dbManager.connectDB();
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	@Override
	public void loadTheGames() {
		dbManager.loadTheGames();
	}

	public void loginUser(String email, String password) {
		dbManager.loginUser(email, password);
	}

	public void notifyAuthObservers(String response) {
		dbManager.notifyAuthObservers(response);
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		dbManager.notifySaveLoadObserver(response);
	}

	public void registerUser(String email, String password, String forgotKey) {
		dbManager.registerUser(email, password, forgotKey);
	}

	public void resetPassword(String email, String newPassword, String forgotKey) {
		dbManager.resetPassword(email, newPassword, forgotKey);
	}

	@Override
	public void saveTheGame(Game game) {
		dbManager.saveTheGame(game);
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public void subscribeAuthObserver(AuthObserver observer) {
		dbManager.subscribeAuthObserver(observer);

	}

	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		dbManager.subscribeSaveLoadObserver(observer);
	}

}
