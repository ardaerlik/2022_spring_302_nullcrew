package com.nullcrew.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectFactory {
	
	public static Paddle createPaddle() {
		return new Paddle(100, 470, 120, 10);
	}
	
	public static Ball createBall() {
		return new Ball(10, 10, 10, 10);
	}

	public static List<Asteroid> createAsteroids(int[] numOfAsteroidTypes){
		List<Asteroid> asteroids = new ArrayList<>();
		int numSimple = numOfAsteroidTypes[0];
		int numFirm = numOfAsteroidTypes[1];
		int numExplosive = numOfAsteroidTypes[2];
		int numGift = numOfAsteroidTypes[3];
		for(int i=0; i<numSimple; i++){
			//115 - 400
			Random r = new Random();
			int x = r.nextInt(1024);
			int y = r.nextInt(400-115) + 115;
			asteroids.add( new SimpleAsteroid(x, y, 40, 40, 0));
		}
		for(int i=0; i<numFirm; i++){
			//115 - 400
			Random r = new Random();
			int x = r.nextInt(1024);
			int y = r.nextInt(400-115) + 115;
			asteroids.add( new FirmAsteroid(x, y, 40, 40, 0));
		}
		for(int i=0; i<numExplosive; i++){
			//115 - 400
			Random r = new Random();
			int x = r.nextInt(1024);
			int y = r.nextInt(400-115) + 115;
			asteroids.add( new ExplosiveAsteroid(x, y, 40, 40, 0));
		}
		for(int i=0; i<numGift; i++){
			//115 - 400
			Random r = new Random();
			int x = r.nextInt(1024);
			int y = r.nextInt(400-115) + 115;
			asteroids.add( new GiftAsteroid(x, y, 40, 40, 0));
		}
		return asteroids;
	}
}
