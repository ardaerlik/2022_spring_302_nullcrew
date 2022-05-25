package com.nullcrew.Domain.Models;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public interface Constants {
	
	interface UIConstants {
		GraphicsDevice DEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	
	interface DatabaseResponses {
		String DATABASE_CREDENTIALS = "mongodb+srv://nullcrew:NullCrew2022@cluster0.wcrf4.mongodb.net/alien_asteroid_game?retryWrites=true&w=majority";
		String DATABASE_NAME = "alien_asteroid_game";
		String USERS_COLLECTION = "users_test";
		String GAMES_COLLECTION = "games_test";
		String LOGIN_ACCEPTED = "Login successfully";
		String WRONG_PASSWORD = "Wrong password";
		String WRONG_EMAIL = "Wrong email address";
		String REGISTER_ACCEPTED = "Register successfully";
		String EMAIL_HAS_ACCOUNT = "Email has taken";
		String DATABASE_ERROR = "Database error has occured";
		String NEW_GAME_SAVED = "New game saved";
		String GAME_UPDATED = "Game updated";
	}

}
