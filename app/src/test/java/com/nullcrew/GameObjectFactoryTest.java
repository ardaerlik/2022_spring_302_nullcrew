/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.nullcrew;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.Paddle;

class GameObjectFactoryTest {
    @Test void createAsteroidsTest() {
        assertNotNull( GameObjectFactory.createAsteroids(new int[]{25,25,25,25}, new int[]{40,40}, new int[]{20,20,20,20}, new int[]{5,10}),
                "Asteroid list can not be null!");
        assertEquals( 100, GameObjectFactory.createAsteroids(new int[]{25,25,25,25}, new int[]{40,40}, new int[]{20,20,20,20}, new int[]{5,10}).size(),
                "The length of Asteroid list is incorrect!");
    }

    @Test void createSimpleTest() {
        assertNotNull( GameObjectFactory.createSimple(25),
                "SimpleAsteroid list can not be null!");
        assertEquals( 25, GameObjectFactory.createSimple(25).size(),
                "The length of SimpleAsteroid list is incorrect!");
    }

    @Test void createFirmTest() {
        assertNotNull( GameObjectFactory.createFirm(25),
                "FirmAsteroid list can not be null!");
        assertEquals( 25, GameObjectFactory.createFirm(25).size(),
                "The length of FirmAsteroid list is incorrect!");
    }

    @Test void createExplosiveTest() {
        assertNotNull( GameObjectFactory.createExplosive(25),
                "ExplosiveAsteroid list can not be null!");
        assertEquals( 25, GameObjectFactory.createExplosive(25).size(),
                "The length of ExplosiveAsteroid list is incorrect!");
    }

    @Test void createGiftTest() {
        assertNotNull( GameObjectFactory.createGift(25),
                "GiftAsteroid list can not be null!");
        assertEquals( 25, GameObjectFactory.createGift(25).size(),
                "The length of GiftAsteroid list is incorrect!");
    }

    @Test void createBallTest() {
        assertNotNull( GameObjectFactory.createBall(),
                "Ball can not be null!");
        assertTrue( GameObjectFactory.createBall() instanceof Ball,
                "The type of Object must be Ball!");
    }

    @Test void createPaddleTest() {
        assertNotNull( GameObjectFactory.createPaddle(),
                "Paddle can not be null!");
        assertTrue( GameObjectFactory.createPaddle() instanceof Paddle,
                "The type of Object must be Paddle!");
    }
}