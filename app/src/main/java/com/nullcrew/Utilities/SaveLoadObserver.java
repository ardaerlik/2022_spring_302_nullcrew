package com.nullcrew.Utilities;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.Game;

public interface SaveLoadObserver {
	public void allGamesLoaded(ArrayList<Game> games, Constants.DatabaseResponses response);
	
	public void allGamesSaved(Constants.DatabaseResponses response);
	
	public void gameLoaded(Game game, Constants.DatabaseResponses response);
	
	public void gameSaved(Constants.DatabaseResponses response);
}
