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
	}

}
