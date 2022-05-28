package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public interface Database {
	public abstract void saveTheGame(Game game);
	
	public abstract void loadTheGames();
	
	public abstract void subscribeSaveLoadObserver(SaveLoadObserver observer);
	
	public abstract void notifySaveLoadObserver(String response);
}
