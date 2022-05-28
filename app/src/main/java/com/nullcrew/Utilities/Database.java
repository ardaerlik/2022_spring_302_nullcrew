package com.nullcrew.Utilities;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public interface Database {
	public abstract void saveTheGame(Game game);
	
	public abstract ArrayList<Game> loadTheGames();
	
	public abstract void subscribeSaveLoadObserver(SaveLoadObserver observer);
	
	public abstract void notifySaveLoadObserver(String response);
}
