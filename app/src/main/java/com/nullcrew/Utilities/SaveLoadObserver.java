package com.nullcrew.Utilities;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.Game;

/**
 * SaveLoadObserver interface manages the connection between classes implements
 * DataStrategy and controller classes.
 * <p>
 * Gives save and load
 * 
 *  signals.
 * 
 * @author Arda Erlik
 * @version 1.0
 * @since 2022-05-01
 */
public interface SaveLoadObserver {
	public void allGamesLoaded(ArrayList<Game> games, String response);

	public void gameNotLoaded(String response);

	public void gameNotSaved(String response);

	public void gameSaved(String response);
}
