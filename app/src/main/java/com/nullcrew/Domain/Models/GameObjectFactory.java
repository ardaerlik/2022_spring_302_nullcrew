package com.nullcrew.Domain.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.nullcrew.UI.Views.GamePanel;

public class GameObjectFactory {
	public static final int ASTEROID_WIDTH = 20;
	public static final int ASTEROID_HEIGHT = 20;
	public static final int ALIEN_WIDTH = 40;
	public static final int ALIEN_HEIGHT = 40;
	public static final int PADDLE_X = 100;
	public static final int PADDLE_Y = 1005;
	public static final int BALL_X = 155;
	public static final int BALL_Y = 975;

	public static Paddle createPaddle() {
		return new Paddle(PADDLE_X, PADDLE_Y, 120, 10);
	}

	public static Ball createBall() {
		return new Ball(BALL_X, BALL_Y, 17, 17);
	}
	
	public static Alien createAlien() {
		
		int random = new Random().nextInt(3);
		
		switch (random) {
			case 0:
				return new RepairingAlien(0, 0, ALIEN_WIDTH, ALIEN_HEIGHT);
			case 1:
				return new CooperativeAlien(0, 0, ALIEN_WIDTH, ALIEN_HEIGHT);
			case 2:
				return new ProtectingAlien(0, 0, ALIEN_WIDTH, ALIEN_HEIGHT);
			case 3:
				return new TimeWastingAlien(0, 0, ALIEN_WIDTH, ALIEN_HEIGHT);
		}
		
		return null;
	}


	public static List<Asteroid> createAsteroids(int[] numOfAsteroidTypes, int[] locSpaces, int[] margins, int[] maxRowsColumns) {
		//REQUIRES: numOfAsteroidTypes != null && numOfAsteroidTypes.length == 4 && locSpaces != null && locSpaces.length == 2 && margins != null && margins.length == 4 && maxRowsColumns != null && maxRowsColumns.length == 2
		//MODIFIES: nothing is modified
		//EFFECTS: creates different types of Asteroids and appends them to the list of Asteroid objects, then returns the list
		List<Asteroid> asteroids = new ArrayList<>();

		asteroids.addAll( createSimple(numOfAsteroidTypes[0]));
		asteroids.addAll( createFirm(numOfAsteroidTypes[1]));
		asteroids.addAll( createExplosive(numOfAsteroidTypes[2]));
		asteroids.addAll( createGift(numOfAsteroidTypes[3]));

		Collections.shuffle(asteroids);
		placeAsteroids(asteroids, numOfAsteroidTypes, locSpaces, margins, maxRowsColumns);
		return asteroids;
	}

	public static List<Asteroid> createSimple(int numSimple){
		List<Asteroid> asteroids = new ArrayList<>();
		for (int i = 0; i < numSimple; i++) {
			SimpleAsteroid simpleAsteroid = new SimpleAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
			if (GamePanel.list_objects != null) {
				GamePanel.list_objects.add(simpleAsteroid);
			}
			asteroids.add(simpleAsteroid);
		}
		return asteroids;
	}

	public static List<Asteroid> createFirm(int numFirm){
		List<Asteroid> asteroids = new ArrayList<>();
		for (int i = 0; i < numFirm; i++) {
			Random rand = new Random();
			int lives = rand.nextInt(3) * 5;
			FirmAsteroid firmAsteroid = new FirmAsteroid(0, 0, ASTEROID_WIDTH + lives, ASTEROID_HEIGHT + lives, 0);
			if (GamePanel.list_objects != null) {
				GamePanel.list_objects.add(firmAsteroid);
			}
			asteroids.add(firmAsteroid);
		}
		return asteroids;
	}

	public static List<Asteroid> createExplosive(int numExplosive){
		List<Asteroid> asteroids = new ArrayList<>();
		for (int i = 0; i < numExplosive; i++) {
			ExplosiveAsteroid explosiveAsteroid = new ExplosiveAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
			if (GamePanel.list_objects != null) {
				GamePanel.list_objects.add(explosiveAsteroid);
			}
			asteroids.add(explosiveAsteroid);
		}
		return asteroids;
	}

	public static List<Asteroid> createGift(int numGift){
		List<Asteroid> asteroids = new ArrayList<>();
		for (int i = 0; i < numGift; i++) {
			GiftAsteroid giftAsteroid = new GiftAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
			if (GamePanel.list_objects != null) {
				GamePanel.list_objects.add(giftAsteroid);
			}
			asteroids.add(giftAsteroid);
		}
		return asteroids;
	}

	private static void placeAsteroids(List<Asteroid> asteroids, int[] numOfAsteroidTypes, int[] locSpaces,
			int[] margins, int[] maxRowsColumns) {
		int total = 0;
		for (int num : numOfAsteroidTypes) {
			total = total + num;
		}
		int leftMargin = margins[0];
		int topMargin = margins[1];
		int rightMargin = margins[2];
		int bottomMargin = margins[3];
		int xSpace = locSpaces[0];
		int ySpace = locSpaces[1];
		int maxRows = maxRowsColumns[0];
		int maxColumns = maxRowsColumns[1];

		int maxX = (xSpace-ASTEROID_WIDTH)/5;
		int minX = -(xSpace-ASTEROID_WIDTH)/5;
		int maxY = (ySpace-ASTEROID_HEIGHT)/5;
		int minY = -(ySpace-ASTEROID_HEIGHT)/5;
		int column = 0;
		int row = 0;
		for (Asteroid a : asteroids) {
			int randomX = new Random().nextInt(maxX - minX) + minX;
			a.setX(leftMargin + column * xSpace + randomX) ;
			column++;
			int randomY = new Random().nextInt(maxY - minY) + minY;
			a.setY(topMargin + row * ySpace + randomY);
			if (column == maxColumns) {
				column = 0;
				row++;
				if (row == maxRows) {
					break;
				}
			}
		}
	}

}
