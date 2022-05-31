package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

/**
 * DataStrategy interface gives auth, save and load functionalities
 * 
 * @author Arda Erlik
 * @version 1.0
 * @since 2022-05-01
 */
public interface DataStrategy {
	public abstract User getUser();

	public abstract void loadTheGames();

	public abstract void loginUser(String email, String password);

	public abstract void notifyAuthObservers(String response);

	public abstract void notifySaveLoadObserver(String response);

	public abstract void registerUser(String email, String password, String forgotKey);

	public abstract void resetPassword(String email, String newPassword, String forgotKey);

	public abstract void saveTheGame(Game game);

	public abstract void setUser(User user);

	public abstract void subscribeAuthObserver(AuthObserver observer);

	public abstract void subscribeSaveLoadObserver(SaveLoadObserver observer);
}
