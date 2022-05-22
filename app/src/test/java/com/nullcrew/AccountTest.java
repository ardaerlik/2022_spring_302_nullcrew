package com.nullcrew;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nullcrew.Domain.Models.Account;
import com.nullcrew.Domain.Models.Game;

public class AccountTest {
	Account account;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void init() throws Exception {
		account = new Account();
	}
	
	@DisplayName("calculateScore throws IllegalArgumentException when totalScore < 0")
	@Test
	void test1() {
		
	}
	
	@DisplayName("calculateScore throws ArithmeticException when totalScore > Integer.MAX_VALUE")
	@Test
	void test2() {
		
	}
	
	@DisplayName("calculateScore throws NullPointerException when savedGames == null")
	@Test
	void test3() {
		
	}
	
	@DisplayName("addGame throws NullPointerException when game == null")
	@Test
	void test4() {
		assertThrows(NullPointerException.class, () -> {
			account.addGame(null);
		});
	}
	
	@DisplayName("addGame succesfully adds new game into account")
	@Test
	void test5() {
		Game game = new Game(null, null, null, null);
		account.addGame(game);
		assertNotNull(game);
		assertEquals(game, account.getSavedGames().get(account.getSavedGames().size() - 1));
		
		Game game2 = new Game(null, null, null, null);
		account.addGame(game2);
		assertNotNull(game2);
		assertEquals(game2, account.getSavedGames().get(account.getSavedGames().size() - 1));
		
		Game game3 = new Game(null, null, null, null);
		account.addGame(game3);
		assertNotNull(game3);
		assertEquals(game3, account.getSavedGames().get(account.getSavedGames().size() - 1));
	}

}
