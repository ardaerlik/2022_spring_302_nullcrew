package com.nullcrew;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nullcrew.Domain.Models.Game.Game;
import com.nullcrew.Domain.Models.Player.Account;

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
		assertTrue(account.repOk());
		
		Game game1 = new Game(null,null,null,null);
		game1.setScore(-10);
		
		account.addGame(game1);
		account.calculateTotalScore();
		
		Game game2 = new Game(null,null,null,null);
		game2.setScore(-5);
		
		account.addGame(game2);
		
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					account.calculateTotalScore();
				});
	}
	
	@DisplayName("calculateScore throws ArithmeticException when totalScore > Integer.MAX_VALUE")
	@Test
	void test2() {
		assertTrue(account.repOk());
		Game game1 = new Game(null,null,null,null);

		int score = Integer.MAX_VALUE;
		game1.setScore(score);

		account.addGame(game1);

		account.calculateTotalScore();

		Game game2 = new Game(null,null,null,null);
		game2.setScore(50);

		account.getSavedGames().add(game2);

		assertThrows(ArithmeticException.class,
				() -> {
					account.calculateTotalScore();
				});

	}
	
	@DisplayName("calculateScore throws NullPointerException when savedGames == null")
	@Test
	void test3() {
		assertTrue(account.repOk());
		
		account.setSavedGames(null);
		
		assertThrows(NullPointerException.class,
				() -> {
					account.calculateTotalScore();
				});
		
	}
	
	@DisplayName("addGame throws NullPointerException when game == null")
    @Test
    void test4() {
		account.repOk();
        assertThrows(NullPointerException.class, () -> {
            account.addGame(null);
        });
    }

    @DisplayName("addGame succesfully adds new game into account")
    @Test
    void test5() {
    	account.repOk();
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
