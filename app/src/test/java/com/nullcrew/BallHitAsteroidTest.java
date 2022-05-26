//package com.nullcrew;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import com.nullcrew.Domain.Controllers.GameController;
//import com.nullcrew.Domain.Models.Asteroid;
//import com.nullcrew.Domain.Models.Ball;
//import com.nullcrew.Domain.Models.FirmAsteroid;
//import com.nullcrew.UI.Views.GameView;
//
//class BallHitAsteroidTest {
//	GameView view = new GameView();
//	GameController controller = new GameController(view, AlienAsteroidGame.getInstance());
//
//	@Test
//	void test1() {//In this test, asteroidList is empty so the method returns null
//
//		Asteroid asteroid = controller.ballHitAsteroid();//asteroidList is empty when initiated
//		Assertions.assertEquals(asteroid, null);
//	}
//
//	@Test
//	void test2() { //In this test, asteroidList is null so the method returns null
//		controller.setAsteroids(null);
//
//		Asteroid asteroid = controller.ballHitAsteroid();
//		Assertions.assertEquals(asteroid, null);
//	}
//
//	@Test
//	void test3() {//In this test, there is a null asteroid in the asteroidList so the method returns the null asteroid
//
//		Asteroid asteroid1 = null;
//		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
//
//		asteroidList.add(asteroid1);
//
//		controller.setAsteroids(asteroidList);
//
//		Asteroid asteroid2 = controller.ballHitAsteroid();
//		Assertions.assertEquals(asteroid2, null);
//	}
//
//	@Test
//	void test4() {//In this test, there is an asteroid in the list and a ball but they do not intersect so the method returns null
//
//		Asteroid asteroid1 = new FirmAsteroid(10,10,20,20,0);
//		Ball ball = new Ball(100,100,17,17);
//
//		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
//		asteroidList.add(asteroid1);
//
//		controller.setAsteroids(asteroidList);
//		controller.setBall(ball);
//
//		Asteroid asteroid2 = controller.ballHitAsteroid();
//		Assertions.assertEquals(asteroid2,null);
//	}
//
//	@Test
//	void test5() {//In this test, there is an asteroid in the list and a ball and they intersect so the method returns the asteroid that
//					//intersects with the ball
//
//		Asteroid asteroid1 = new FirmAsteroid(10,10,20,20,0);
//		Ball ball = new Ball(15,15,17,17);
//
//		List<Asteroid> asteroidList = new ArrayList<Asteroid>();
//		asteroidList.add(asteroid1);
//
//		controller.setAsteroids(asteroidList);
//		controller.setBall(ball);
//
//		Asteroid asteroid2 = controller.ballHitAsteroid();
//		Assertions.assertEquals(asteroid2,asteroid1);
//	}
//}
//
//
//
//
//
//
//
//
