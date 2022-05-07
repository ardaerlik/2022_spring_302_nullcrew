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

	@Override
	public void saveTheGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Game loadTheGame(String gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registerUser() {
		// TODO Auto-generated method stub
		return false;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
	
}
