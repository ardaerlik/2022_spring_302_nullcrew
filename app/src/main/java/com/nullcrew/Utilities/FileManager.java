package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public final class FileManager implements DataStrategy {
	private static FileManager instance = new FileManager();
	
	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		
		return instance;
	}

	public FileManager() {
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

}
