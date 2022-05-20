package com.nullcrew;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.nullcrew.Domain.Models.*;
import com.nullcrew.Domain.Controllers.GameController;
public class GameControllerTest {
	@Test void addSimpleAsteroidTest() {
		assertTrue(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20),25,25),"return true");
		assertFalse(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20), -5, -5),"return false");
		assertNull(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addFirmAsteroidTest() {
		assertTrue(GameController.addAsteroid(new FirmAsteroid(20,20,20,20,20),65,535),"return true");
		assertFalse(GameController.addAsteroid(new FirmAsteroid(20,20,20,20,20), 1700, 2),"return false");
		assertNull(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addGiftAsteroidTest() {
		assertTrue(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20),225,235),"return true");
		assertFalse(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20), 45, 2400),"return false");
		assertNull(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addExplosiveAsteroidTest() {
		assertTrue(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20),95,87),"return true");
		assertFalse(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20), 0, -15),"return false");
		assertNull(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
}
