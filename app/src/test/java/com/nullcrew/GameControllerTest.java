package com.nullcrew;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.nullcrew.Domain.Models.*;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.Domain.Controllers.GameController;
public class GameControllerTest {
	@Test void dragSimpleAsteroidTest() {
		GameController controller = new GameController(new GameView(),AlienAsteroidGame.getInstance());
		List<Asteroid>  list= new ArrayList<Asteroid>();
		list.add(new SimpleAsteroid(42,68,20,20,20));
		list.add(new FirmAsteroid(109,800,20,20,20));
		controller.setAsteroids(list);
		assertNotNull(controller.dragAsteroid(42,68),"return asteroid");
	}
	@Test void dragFirmAsteroidTest() {
		GameController controller = new GameController(new GameView(),AlienAsteroidGame.getInstance());
		List<Asteroid>  list= new ArrayList<Asteroid>();
		list.add(new FirmAsteroid(500,604,20,20,20));
		list.add(new GiftAsteroid(920,1020,20,20,20));
		controller.setAsteroids(list);
		assertNotNull(controller.dragAsteroid(500,604),"return asteroid");
	}
	@Test void dragGiftAsteroidTest() {
		GameController controller = new GameController(new GameView(),AlienAsteroidGame.getInstance());
		List<Asteroid>  list= new ArrayList<Asteroid>();
		list.add(new SimpleAsteroid(50,604,20,20,20));
		list.add(new GiftAsteroid(230,102,20,20,20));
		controller.setAsteroids(list);
		assertNotNull(controller.dragAsteroid(230,102),"return asteroid");
	}
	@Test void dragExplosiveAsteroidTest() {
		GameController controller = new GameController(new GameView(),AlienAsteroidGame.getInstance());
		List<Asteroid> list= new ArrayList<Asteroid>();
		list.add(new SimpleAsteroid(500,604,20,20,20));
		list.add(new ExplosiveAsteroid(920,1020,20,20,20));
		controller.setAsteroids(list);
		assertNotNull(controller.dragAsteroid(920,1020),"return asteroid");
	}
	@Test void dragNullTest() {
		GameController controller = new GameController(new GameView(),AlienAsteroidGame.getInstance());
		List<Asteroid>  list= new ArrayList<Asteroid>();
		list.add(new SimpleAsteroid(500,604,20,20,20));
		list.add(new GiftAsteroid(920,1020,20,20,20));
		controller.setAsteroids(list);
		assertNull(controller.dragAsteroid(200,604),"return null");
	}
}

