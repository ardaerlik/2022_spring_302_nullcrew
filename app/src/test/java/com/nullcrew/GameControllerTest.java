package com.nullcrew;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.nullcrew.Domain.Models.*;
import com.nullcrew.Domain.Controllers.GameController;
public class GameControllerTest {
	@Test void addSimpleAsteroidTest() {
		GameController.asteroidList= new ArrayList<>();
		GameController.asteroidList.add(new SimpleAsteroid(500,604,20,20,20));
		GameController.asteroidList.add(new GiftAsteroid(920,1020,20,20,20));
		GameController.asteroidList.add(new ExplosiveAsteroid(230,102,20,20,20));
		assertTrue(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20),25,25),"return true");
		assertFalse(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20), -5, -5),"return false");
		assertFalse(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addFirmAsteroidTest() {
		GameController.asteroidList= new ArrayList<>();
		GameController.asteroidList.add(new SimpleAsteroid(500,604,20,20,20));
		GameController.asteroidList.add(new GiftAsteroid(920,1020,20,20,20));
		GameController.asteroidList.add(new ExplosiveAsteroid(230,102,20,20,20));
		assertTrue(GameController.addAsteroid(new FirmAsteroid(20,20,20,20,20),65,535),"return true");
		assertFalse(GameController.addAsteroid(new FirmAsteroid(20,20,20,20,20), 1700, 2),"return false");
		assertFalse(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addGiftAsteroidTest() {
		GameController.asteroidList= new ArrayList<>();
		GameController.asteroidList.add(new SimpleAsteroid(500,604,20,20,20));
		GameController.asteroidList.add(new GiftAsteroid(920,1020,20,20,20));
		GameController.asteroidList.add(new ExplosiveAsteroid(230,102,20,20,20));
		assertTrue(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20),225,235),"return true");
		assertFalse(GameController.addAsteroid(new GiftAsteroid(20,20,20,20,20), 45, 2400),"return false");
		assertFalse(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addExplosiveAsteroidTest() {
		GameController.asteroidList= new ArrayList<>();
		GameController.asteroidList.add(new SimpleAsteroid(500,604,20,20,20));
		GameController.asteroidList.add(new GiftAsteroid(920,1020,20,20,20));
		GameController.asteroidList.add(new ExplosiveAsteroid(230,102,20,20,20));
		assertTrue(GameController.addAsteroid(new ExplosiveAsteroid(20,20,20,20,20),95,87),"return true");
		assertFalse(GameController.addAsteroid(new ExplosiveAsteroid(20,20,20,20,20), 0, -15),"return false");
		assertFalse(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
	@Test void addAsteroidOnCollisionTest() {
		GameController.asteroidList= new ArrayList<>();
		GameController.asteroidList.add(new SimpleAsteroid(500,604,20,20,20));
		GameController.asteroidList.add(new GiftAsteroid(920,1020,20,20,20));
		GameController.asteroidList.add(new ExplosiveAsteroid(230,102,20,20,20));
		assertTrue(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20),500,725),"return true");
		assertFalse(GameController.addAsteroid(new SimpleAsteroid(20,20,20,20,20), 500,604),"collision with asteroid and return false");
		assertFalse(GameController.addAsteroid(null,25,25),"Asteroid cannot be null and return false");
	}
}
