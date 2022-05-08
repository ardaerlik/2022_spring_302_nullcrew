package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public interface DataStrategy {
	public abstract void saveTheGame(Game game);
	
	public abstract void loadTheGames();
	
	public abstract void registerUser(String email, String password, String forgotKey);
	
	public abstract void loginUser(String email, String password);
	
	public abstract void resetPassword(String email, String newPassword, String forgotKey);
	
	public abstract void setUser(User user);
	
	public abstract User getUser();
	
	public abstract void subscribeAuthObserver(AuthObserver observer);
	
	public abstract void notifyAuthObservers(String response);
}
