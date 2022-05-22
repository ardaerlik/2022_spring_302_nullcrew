package com.nullcrew;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nullcrew.Domain.Models.Account;
import com.nullcrew.Utilities.DBManager;

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
		
	}
	
	@DisplayName("addGame succesfully adds new game into account")
	@Test
	void test5() {
		
	}

}
