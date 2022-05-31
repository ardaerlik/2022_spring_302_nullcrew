package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public interface Database {
	public abstract void loadTheGames();

	public abstract void notifySaveLoadObserver(String response);

	public abstract void saveTheGame(Game game);

	public abstract void subscribeSaveLoadObserver(SaveLoadObserver observer);
}
