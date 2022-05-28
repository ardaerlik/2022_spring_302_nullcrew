package com.nullcrew;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;
import com.nullcrew.Utilities.FileManager;

public class SaveLoadTest {
	FileManager fileManager;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void init() throws Exception {
		fileManager = FileManager.getInstance();
		fileManager.setUser(new User());
	}
	
	@Test
	void test1() {
		Game game = new Game(new ObjectId(), null, null, null);
		game.setLives(10);
		game.setScore(323);
		fileManager.saveTheGame(game);
		System.out.println(fileManager.getUser().getSavedGameIds().get(0));
		assertEquals(fileManager.getUser().getSavedGameIds().get(0), game.getGameId());
	}

}
