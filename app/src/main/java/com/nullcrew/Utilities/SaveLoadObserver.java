package com.nullcrew.Utilities;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.Game;

public interface SaveLoadObserver {
	public void allGamesLoaded(ArrayList<Game> games, String response);
	
	public void allGamesSaved(String response);
	
	public void gameLoaded(Game game, String response);
	
	public void gameSaved(String response);
	
	public void gameNotSaved(String response);
	
	public void gameNotLoaded(String response);
}
