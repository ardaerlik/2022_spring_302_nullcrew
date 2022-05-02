package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;

public interface DataStrategy {
	public abstract void saveTheGame(Game game);
	
	public abstract Game loadTheGame(String gameId);
	
	public abstract boolean registerUser();
}
