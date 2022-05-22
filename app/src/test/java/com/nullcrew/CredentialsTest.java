/**
 * 
 */
package com.nullcrew;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nullcrew.Utilities.DBManager;

/**
 * This test class test the DBManager's checkCredentials method
 * 
 * @author Arda Erlik
 *
 */
@DisplayName("Check credentials")
class CredentialsTest {
	DBManager dbManager;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void init() throws Exception {
		dbManager = DBManager.getInstance();
		dbManager.connectDB();
	}

	/**
	 * @requires DBManager instance is created, MongoClient is opened, and both arguments are null
	 * @effects throws NullPointerException
	 * @modifies None
	 */
	@Test
	@DisplayName("throws NullPointerException when both arguments are null")
	void test1() {
		assertThrows(NullPointerException.class,
				() -> {
					dbManager.checkCredentials(null, null);
				});
	}
	
	/**
	 * @requires DBManager instance is created, MongoClient is opened, and password is null
	 * @effects throws NullPointerException
	 * @modifies None
	 */
	@Test
	@DisplayName("throws NullPointerException when password is null")
	void test2() {
		assertThrows(NullPointerException.class,
				() -> {
					dbManager.checkCredentials("arda.erlik@gmail.com", null);
				});
	}
	
	/**
	 * @requires DBManager instance is created, MongoClient is opened, and email is null
	 * @effects throws NullPointerException
	 * @modifies None
	 */
	@Test
	@DisplayName("throws NullPointerException when email is null")
	void test3() {
		assertThrows(NullPointerException.class,
				() -> {
					dbManager.checkCredentials(null, "123456");
				});
	}
	
	/**
	 * @requires DBManager instance is created, MongoClient is opened, and email is not valid
	 * @effects throws IllegalArgumentException
	 * @modifies None
	 */
	@Test
	@DisplayName("throws IllegalArgumentException when email is not a valid by REGEX")
	void test4() {
		assertThrows(IllegalArgumentException.class,
				() -> {
					dbManager.checkCredentials("aerlik20@", "123456");
				});
		assertThrows(IllegalArgumentException.class,
				() -> {
					dbManager.checkCredentials("@ku.edu", "123456");
				});
		assertThrows(IllegalArgumentException.class,
				() -> {
					dbManager.checkCredentials("aerlik20", "123456");
				});
	}
	
	@Nested
	@DisplayName("when valid arguments are passed")
	class ValidArguments {
		/**
		 * @requires DBManager instance is created, MongoClient is opened, and email is not found
		 * @effects returns null
		 * @modifies database instance
		 */
		@Test
		@DisplayName("returns null if the email is not found")
		void test5() {
			ObjectId userId = dbManager.checkCredentials("wrong@email.com", "123456");
			assertNull(userId);
		}
		
		/**
		 * @requires DBManager instance is created, MongoClient is opened, and password is wrong
		 * @effects returns null
		 * @modifies database instance
		 */
		@Test
		@DisplayName("returns null if the password is wrong")
		void test6() {
			ObjectId userId = dbManager.checkCredentials("arda.erlik@gmail.com", "wrong");
			assertNull(userId);
		}
		
		/**
		 * @requires DBManager instance is created and MongoClient is opened
		 * @effects returns the ObjectId of the user email
		 * @modifies database instance
		 */
		@Test
		@DisplayName("returns userId if the email is found")
		void test7() {
			ObjectId userId = dbManager.checkCredentials("arda.erlik@gmail.com", "1234567");
			assertNotNull(userId);
			assertEquals(new ObjectId("627779effb50682c309edf47"), userId);
			
			ObjectId userId2 = dbManager.checkCredentials("canxkoz@gmail.com", "123456");
			assertNotNull(userId);
			assertEquals(new ObjectId("6277f44ca4f774638b83ae45"), userId2);
			
			ObjectId userId3 = dbManager.checkCredentials("byaman20@ku.edu.tr", "bugraaaaa");
			assertNotNull(userId);
			assertNotEquals(new ObjectId("627779effb50682c309edf47"), userId3);
			assertEquals(new ObjectId("62875756ebb4fda2f45b5cf3"), userId3);
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dbManager.closeDB();
	}

}
