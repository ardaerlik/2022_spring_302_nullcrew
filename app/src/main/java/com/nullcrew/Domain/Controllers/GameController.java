package com.nullcrew.Domain.Controllers;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.Alien;
import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.AsteroidType;
import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.ExplosiveAsteroid;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.GameMode;
import com.nullcrew.Domain.Models.GameObject;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.GiftAsteroid;
import com.nullcrew.Domain.Models.MagnetPowerUp;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.MoveDirection;
import com.nullcrew.Domain.Models.Paddle;
import com.nullcrew.Domain.Models.PowerUp;
import com.nullcrew.Domain.Models.TallerPowerUp;
import com.nullcrew.UI.Views.GamePanel;
import com.nullcrew.UI.Views.GameView;

public class GameController extends AppController {
	public static final int MAX_NUM_ASTEROIDS = 165;
	public static final int MIN_NUM_ASTEROIDS = 75;
	public static final int MIN_NUM_EXPLOSIVE = 5;
	public static final int MIN_NUM_FIRM = 10;
	public static final int MIN_NUM_GIFT = 10;
	private List<Asteroid> asteroidList;
	private List<PowerUp> powerups;
	private List<Ball> balls;
	private Paddle paddle;
	private Alien alien;
	private Game game;
	private PowerUp powerUp;
	private float estimated_boostTime=0f;
	private long boost_start_time=0l;
	public GameController(GameView gameView, AlienAsteroidGame app) {
		super(gameView, app);
		asteroidList = new ArrayList<>();
		powerups=  new ArrayList<>();
	}

	public boolean addAsteroid(Asteroid toBeAdded, double newX, double newY) {
		if(toBeAdded==null||newX<0||newY<0||newX>1536|newY>1116) {
			return false;
		}
		for (Asteroid a : asteroidList) {
			if (newX >= a.getX() && newX <= a.getX() + GameObjectFactory.ASTEROID_WIDTH && newY >= a.getY()
					&& newY <= a.getY() + GameObjectFactory.ASTEROID_HEIGHT) {
				return false;
			}
			if(a==toBeAdded) {
				continue;
			}
			if(new Rectangle2D.Double(newX,newY,toBeAdded.getWidth(),toBeAdded.getHeight()).intersects(
					new Rectangle2D.Double(a.getX(),a.getY(),a.getWidth(),a.getHeight()))) {
				return false;
			}
		} 
		toBeAdded.setX(newX);
		toBeAdded.setY(newY);
		asteroidList.add(toBeAdded);
		((GameView) view).getGamePanel().repaint();
		return true;
	}

	public Asteroid ballHitAsteroid() {
		if (getAsteroidList() == null || getAsteroidList().size() == 0) {
			return null;
		}

		for (Asteroid asteroid : getAsteroidList()) {
			if (asteroid == null) {
				continue;
			}
			for(Ball ball:balls) {
				
			if(ball.getObjShape().getShape().intersects(asteroid.getObjShape().getRect())) {
				reflectFromObject(asteroid,ball);
				if ((asteroid instanceof ExplosiveAsteroid)) {
					((ExplosiveAsteroid) asteroid).hit_nearby(this);
					asteroid.hit(this);
				}
				else if (asteroid instanceof GiftAsteroid){ 
					if(((GiftAsteroid) asteroid).powerup!=null){
						if(((GiftAsteroid) asteroid).powerup instanceof MagnetPowerUp) {
							powerups.add(((GiftAsteroid) asteroid).powerup);
						}
						else if(((GiftAsteroid) asteroid).powerup instanceof TallerPowerUp) {
							powerups.add(((GiftAsteroid) asteroid).powerup);
						}
						else {
							((GiftAsteroid) asteroid).powerup.use();
							powerUp=((GiftAsteroid) asteroid).powerup;
						}
					}
					asteroid.hit(this);
				}
				else {
					asteroid.hit(this);
				}
				return asteroid;
			}
			}

		}
		return null;
	}
	public void ballHitBall() {
		if(balls.size()<=1) {
			return;
		}
		for(Ball ball: balls) {
			for(Ball collided_ball:balls) {
				if(ball==collided_ball) {
					continue;
				}
				if(ball.getObjShape().getShape().intersects(collided_ball.getObjShape().getShape().getBounds2D())){
					reflectFromObject(ball,collided_ball);
					reflectFromObject(collided_ball,ball);
				}
			}
		}
	}
	public void ballMoved() {

		for(Ball ball:balls) {
			if (GamePanel.gameMode == GameMode.PAUSED) {
				return;
			}
			if (0 >= ball.getX()) {
				ball.setVelocityX(-ball.getVelocityX());
			}
			if (ball.getX() + ball.getWidth() >= ((GameView) view).getInitialWidth()) {
				ball.setVelocityX(-ball.getVelocityX());
			}
			if (0 >= ball.getY()) {
				ball.setVelocityY((-ball.getVelocityY()));
			}
			ball.setX(ball.getX() + ball.getVelocityX());
			ball.setY(ball.getY() + ball.getVelocityY());
		}

	}
	public void updateBoosts() {
		if(paddle.onTallerPowerUp) {
			//int time_passed= System.currentTimeMillis()-
			
		}
	}
	public MessageType checkNumAsteroids(int[] numOfAsteroidTypes) { // simple, firm, explosive, gift
		int total = 0;
		for (int n : numOfAsteroidTypes) {
			total = total + n;
		}
		if (total < MIN_NUM_ASTEROIDS) {
			return MessageType.MinThresholdErrorTotal;
		} else if (total > MAX_NUM_ASTEROIDS) {
			return MessageType.MaxThresholdErrorTotal;
		} else if (numOfAsteroidTypes[1] < MIN_NUM_FIRM) {
			return MessageType.MinThresholdErrorFirm;
		} else if (numOfAsteroidTypes[2] < MIN_NUM_EXPLOSIVE) {
			return MessageType.MinThresholdErrorExplosive;
		} else if (numOfAsteroidTypes[3] < MIN_NUM_GIFT) {
			return MessageType.MinThresholdErrorGift;
		} else {
			return MessageType.Success;
		}
	}

	private int countAsteroidTypes(AsteroidType type) {
		int count = 0;
		for (Asteroid a : asteroidList) {
			if (a.getType() == type) {
				count++;
			}
		}
		return count;
	}

	public Asteroid dragAsteroid(int x, int y) {
		Asteroid toBeRemoved = null;
		for (Asteroid a : asteroidList) {
			if (x >= a.getX() && x <= a.getX() + GameObjectFactory.ASTEROID_WIDTH && y >= a.getY()
					&& y <= a.getY() + GameObjectFactory.ASTEROID_HEIGHT) {
				toBeRemoved = a;
				break;
			}
		}
		Asteroid backup = null;
		if (toBeRemoved != null) {
			backup = (Asteroid) toBeRemoved.clone();
			asteroidList.remove(toBeRemoved);
			((GameView) view).getGamePanel().repaint();
		}
		return backup;
	}

	public List<Asteroid> getAsteroidList() {
		return asteroidList;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public Paddle getPaddle() {
		return paddle;
	}
	
	public Alien getAlien() {
		return alien;
	}

	public void paddleHitBall() {
		for(Ball ball:balls) {	
			if (getPaddle().getObjShape().getShape().intersects(ball.getObjShape().getShape().getBounds2D())){
				
				ball.setVelocityY((-ball.getVelocityY()));
			}
		}
	}
	public void activatePowerUp(String key) {
		for(int a=0;a<powerups.size();a++) {
			if(key=="TallerPowerUp"&&powerups.get(a) instanceof TallerPowerUp) {
				powerUp=powerups.get(a);
				powerups.get(a).use();
				powerups.remove(a);
				estimated_boostTime=30f;
				boost_start_time=System.currentTimeMillis();
				
			}
			if(key=="MagnetPowerUp"&&powerups.get(a) instanceof MagnetPowerUp) {
				powerUp=powerups.get(a);
				powerups.get(a).use();
				powerups.remove(a);
				getPaddle().onMagnet=true;
			}
		}
	}
	public void paddleMoved(MoveDirection direction) {
		if (GamePanel.gameMode == GameMode.PAUSED) {
			return;
		}
		switch (direction) {
		case RIGHT: {
			if (paddle.getX() + getPaddle().getWidth() < ((GameView) view).getInitialWidth()) {
				paddle.setX(paddle.getX() + paddle.velocity);
			}
			else {
				if(paddle.onWrapPowerUp) {
					paddle.setX(0);
				}
			}
			break;
		}
		case LEFT: {
			if (paddle.getX() > 0) {
				paddle.setX(paddle.getX() - paddle.velocity);
			}
			else {
				if(paddle.onWrapPowerUp) {
					paddle.setX(((GameView) view).getInitialWidth()-paddle.getWidth());
				}
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}

	public void paddleRotated(MoveDirection direction) {
		if (GamePanel.gameMode == GameMode.PAUSED) {
			return;
		}
		switch (direction) {
		case UP: {
			if (paddle.getRotationDegree() + 5 > 45) {
				return;
			}
			paddle.setRotationDegree(paddle.getRotationDegree() + 5);
			break;
		}
		case DOWN: {
			if (paddle.getRotationDegree() - 5 < -45) {
				return;
			}
			paddle.setRotationDegree(paddle.getRotationDegree() - 5);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}

	public void reflectFromObject(GameObject collided_asteroid,Ball ball) {
		
		if (collided_asteroid == null) {
			return;
		}
			
		double posX = (double) collided_asteroid.getX() - ball.getX();
		double posY = (double) collided_asteroid.getY() - ball.getY();
		double angle = Math.atan2(posY - 0, posX - (double) 1) * (180 / Math.PI);
		if (45d <= angle && angle <= 135d) {
			ball.setVelocityY(-ball.getVelocityY());
		}
		if ((135d <= angle && angle <= 180d) || (0 >= angle && angle >= -45d)) {
			ball.setVelocityX(-ball.getVelocityX());
		}
		if (-45d >= angle && angle >= -135d) {
			ball.setVelocityY(-ball.getVelocityY());
		}
		if ((0d <= angle && angle <= 45d) || (-135d >= angle && angle <= -180d)) {
			ball.setVelocityX(-ball.getVelocityX());
		}
	}
	public void freezeBallOnPaddle(Ball ball) {
		if(ball==null) {
			return;
		}
		ball.setX(
				(GameObjectFactory.BALL_X-GameObjectFactory.PADDLE_X)+getPaddle().getX()
				);
		ball.setY(
				GameObjectFactory.BALL_Y
				);
		ball.setVelocityX(0);
		ball.setVelocityY(0);
	}

	public Object[] removeAsteroid(int x, int y) {
		MessageType msg = null;
		Asteroid toBeRemoved = null;
		for (Asteroid a : asteroidList) {
			if (x >= a.getX() && x <= a.getX() + GameObjectFactory.ASTEROID_WIDTH && y >= a.getY()
					&& y <= a.getY() + GameObjectFactory.ASTEROID_HEIGHT) {
				toBeRemoved = a;
				break;
			} else {
				msg = MessageType.NoAsteroidInThisLocation;
			}
		}
		Asteroid backup = null;
		if (toBeRemoved != null) {
			if (asteroidList.size() <= MIN_NUM_ASTEROIDS) {
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorTotal;
			} else if (toBeRemoved.getType() == AsteroidType.Firm
					&& countAsteroidTypes(AsteroidType.Firm) <= MIN_NUM_FIRM) {
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorFirm;
			} else if (toBeRemoved.getType() == AsteroidType.Explosive
					&& countAsteroidTypes(AsteroidType.Explosive) <= MIN_NUM_EXPLOSIVE) {
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorExplosive;
			} else if (toBeRemoved.getType() == AsteroidType.Gift
					&& countAsteroidTypes(AsteroidType.Gift) <= MIN_NUM_GIFT) {
				toBeRemoved = null;
				msg = MessageType.MinThresholdErrorGift;
			} else {
				msg = MessageType.Success;
			}
			if (toBeRemoved != null) {
				backup = (Asteroid) toBeRemoved.clone();
				asteroidList.remove(toBeRemoved);
				((GameView) view).getGamePanel().repaint();
			}
		}
		return new Object[] { backup, msg };
	}

	public void setAsteroids(List<Asteroid> asteroidList) {
		this.asteroidList = asteroidList;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	public void setAlien(Alien alien) {
		this.alien = alien;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<PowerUp> getPowerups() {
		return powerups;
	}

	public void setPowerups(List<PowerUp> powerups) {
		this.powerups = powerups;
	}

}
