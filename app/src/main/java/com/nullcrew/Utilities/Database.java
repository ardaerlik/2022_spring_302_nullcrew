package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

/**
 * Database interface gives the save load game functionalities
 * 
 * @author Arda Erlik
 * @version 1.0
 * @since 2022-05-01
 */
public interface Database {
	public abstract void loadTheGames();

	public abstract void notifySaveLoadObserver(String response);

	public abstract void saveTheGame(Game game);

	public abstract void subscribeSaveLoadObserver(SaveLoadObserver observer);
}
