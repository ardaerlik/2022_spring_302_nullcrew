package com.nullcrew.Models;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameObjectFactory {
	public static final int ASTEROID_WIDTH = 20;
	public static final int ASTEROID_HEIGHT = 20;
	
	public static Paddle createPaddle() {
		return new Paddle(100, 470, 120, 10);
	}
	
	public static Ball createBall() {
		return new Ball(155, 455, 17, 17);
	}

	public static List<Asteroid> createAsteroids(int[] numOfAsteroidTypes, int[] locSpaces, int[] margins, int[] maxRowsColumns){
		List<Asteroid> asteroids = new ArrayList<>();
		int numSimple = numOfAsteroidTypes[0];
		int numFirm = numOfAsteroidTypes[1];
		int numExplosive = numOfAsteroidTypes[2];
		int numGift = numOfAsteroidTypes[3];

		for(int i=0; i<numSimple; i++){
			SimpleAsteroid simpleAsteroid = new SimpleAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
//			asteroids = controlLocation(asteroids, simpleAsteroid);
			asteroids.add(simpleAsteroid);
		}
		for(int i=0; i<numFirm; i++){
			FirmAsteroid firmAsteroid = new FirmAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
//			asteroids = controlLocation(asteroids, firmAsteroid);
			asteroids.add(firmAsteroid);
		}
		for(int i=0; i<numExplosive; i++){
			ExplosiveAsteroid explosiveAsteroid = new ExplosiveAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
//			asteroids = controlLocation(asteroids, explosiveAsteroid);
			asteroids.add(explosiveAsteroid);
		}
		for(int i=0; i<numGift; i++){
			GiftAsteroid giftAsteroid = new GiftAsteroid(0, 0, ASTEROID_WIDTH, ASTEROID_HEIGHT, 0);
//			asteroids = controlLocation(asteroids, giftAsteroid);
			asteroids.add(giftAsteroid);
		}
		Collections.shuffle(asteroids);
		placeAsteroids(asteroids, numOfAsteroidTypes, locSpaces, margins, maxRowsColumns);
		return asteroids;
	}

	private static void placeAsteroids(List<Asteroid> asteroids, int[] numOfAsteroidTypes, int[] locSpaces, int[] margins, int[] maxRowsColumns){
		int total = 0;
		for(int num: numOfAsteroidTypes){
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

		int column=0;
		int row=0;
		for(Asteroid a: asteroids){
			a.setX(leftMargin + column*xSpace);
			column++;
			a.setY(topMargin + row*ySpace);
			if( column == maxColumns){
				column=0;
				row++;
				if( row == maxRows){
					break;
				}
			}
		}
	}
	
//	private static List<Asteroid> controlLocation(List<Asteroid> asteroids, Asteroid newAsteroid) {
//		boolean isValidLocation = false;
//
//		do {
//			Random r = new Random();
//			int xLoc = r.nextInt(1024 - newAsteroid.getWidth());
//			int yLoc = r.nextInt(470 - newAsteroid.getHeight());
//			newAsteroid.setX(xLoc);
//			newAsteroid.setY(yLoc);
//
//			isValidLocation = true;
//
//			for (int i=0; i<asteroids.size(); i++) {
//				if (intersects(newAsteroid, asteroids.get(i))) {
//					isValidLocation = false;
//					break;
//				}
//			}
//		} while (!isValidLocation);
//
//		asteroids.add(newAsteroid);
//
//		return asteroids;
//	}
	
	private static boolean intersects(Asteroid asteroid1, Asteroid asteroid2) {
		Rectangle rectangle1 = new Rectangle(asteroid1.x, asteroid1.y, asteroid1.width, asteroid1.height);
		Rectangle rectangle2 = new Rectangle(asteroid2.x, asteroid2.y, asteroid2.width, asteroid2.height);
		
		if (rectangle1.intersects(rectangle2)) {
			return true;
		} else {
			return false;
		}
	}
}
