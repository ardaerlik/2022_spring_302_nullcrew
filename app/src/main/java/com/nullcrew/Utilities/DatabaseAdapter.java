package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public class DatabaseAdapter implements Database {
	private DBManager dbManager;

	public DatabaseAdapter(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	@Override
	public void saveTheGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadTheGames() {
		// TODO Auto-generated method stub
		
	}

	public void registerUser(String email, String password, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	public void resetPassword(String email, String newPassword, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public void subscribeAuthObserver(AuthObserver observer) {
		// TODO Auto-generated method stub
		
	}

	public void notifyAuthObservers(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		// TODO Auto-generated method stub
		
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

}
