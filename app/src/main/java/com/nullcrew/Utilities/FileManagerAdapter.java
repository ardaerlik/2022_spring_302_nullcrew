package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public final class FileManagerAdapter implements DataStrategy {
	private static FileManagerAdapter instance = new FileManagerAdapter();
	private FileManager fileManager;
	
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
	public void loadTheGame(String gameId) {
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
	
}
