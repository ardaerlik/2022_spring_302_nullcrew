package com.nullcrew.Utilities;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.Game;

public interface SaveLoadObserver {
	public void allGamesLoaded(ArrayList<Game> games, String response);

	public void gameNotLoaded(String response);

	public void gameNotSaved(String response);

	public void gameSaved(String response);
}
