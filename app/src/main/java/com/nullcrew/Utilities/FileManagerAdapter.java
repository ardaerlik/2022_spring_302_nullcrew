package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public final class FileManagerAdapter implements DataStrategy {
	private static FileManagerAdapter instance = new FileManagerAdapter();
	private FileManager fileManager;
	private User user;
	
	public static FileManagerAdapter getInstance() {
		if (instance == null) {
			instance = new FileManagerAdapter();
		}
		
		return instance;
	}

	public FileManagerAdapter() {
		this.fileManager = FileManager.getInstance();
	}
	
	public FileManagerAdapter(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	@Override
	public void saveTheGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadTheGames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUser(String email, String password, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void subscribeAuthObserver(AuthObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
	
}
