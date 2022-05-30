package com.nullcrew.Domain.Controllers;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.Alien;
import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.AsteroidType;
import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.ExplosiveAsteroid;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.Game.DataType;
import com.nullcrew.Domain.Models.GameMode;
import com.nullcrew.Domain.Models.GameObject;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.GiftAsteroid;
import com.nullcrew.Domain.Models.LaserBall;
import com.nullcrew.Domain.Models.MagnetPowerUp;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.MoveDirection;
import com.nullcrew.Domain.Models.Paddle;
import com.nullcrew.Domain.Models.PowerUp;
import com.nullcrew.Domain.Models.TallerPowerUp;
import com.nullcrew.UI.Views.GamePanel;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.Utilities.SaveLoadObserver;

public class GameController extends AppController implements SaveLoadObserver {
	public static final int MAX_NUM_ASTEROIDS = 165;
	public static final int MIN_NUM_ASTEROIDS = 75;
	public static final int MIN_NUM_EXPLOSIVE = 5;
	public static final int MIN_NUM_FIRM = 10;
	public static final int MIN_NUM_GIFT = 10;
	private List<GameObject> list_objects;
	private List<Asteroid> asteroidList;
	private List<PowerUp> powerups;
	private List<Ball> balls;
	private List<LaserBall> laser_balls;
	private Paddle paddle;
	private Alien alien;
	private PowerUp powerUp;
	private float estimated_tallerTime=0f;
	private float estimated_wrapTime=0f;
	private long wrap_start_time=0l;
	private long taller_start_time=0l;
	private boolean wrap_started_timing=false;
	private boolean taller_started_timing=false;
	private Game game;
	
	public GameController(GameView gameView, AlienAsteroidGame app) {
		super(gameView, app);
		app.getDatabaseAdapter()
		.subscribeSaveLoadObserver(this);
		app.getFileManager()
		.subscribeSaveLoadObserver(this);
		List<GameObject> list = new ArrayList<GameObject>();
		this.setList_objects(list);
		game = Game.getCurrentGame();
		asteroidList = new ArrayList<>();
		powerups=  new ArrayList<>();
	}

	/**
	* boolean function for the adding asteroids.
	* 
	* @param toBeAdded is for a thing that will be added.
	* @param newX is new X value.
	* @param newY is new Y value.
	* 
	* @return boolean, there is some different calculations about the
	* getting true or false returns.
	*/
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

	/**
	* appearAsteroid is indicate the asteroids that will be indicated.
	*/
	public void appearAsteroid() {
		((GameView) view).getGamePanel().createAlien();
		this.getAlien().act(this);
	}
	
	/**
	* destroyAsteroidRow will count the rows that are destroyed.
	*/
	public void destroyAsteroidRow() {
		if (getAsteroidList() == null || getAsteroidList().size() == 0) {
			return;
		}
		int r = new Random().nextInt(asteroidList.size()/15);
		
		for (int i = 0; i < 15; i++) {
			if (asteroidList.size() < 15*r) break;
			asteroidList.remove(15*r);
		}
	}
	
	/**
	* The hitting reaction between ball and asteroids.
	*/
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
							((GiftAsteroid) asteroid).powerup.canFall=true;
							powerups.add(((GiftAsteroid) asteroid).powerup);
							((GiftAsteroid) asteroid).powerup.setX(asteroid.getX());
							((GiftAsteroid) asteroid).powerup.setY(asteroid.getY());	
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
	
	/**
	* The function shows that hitting reaction between 
	* ball and aliens.
	* 
	* @param ball is a object from the game.
	*/
	public Alien ballHitAlien(Ball ball) {
		if (ball.getObjShape().getShape().intersects(alien.getObjShape().getRect())) {
			reflectFromAlien(alien,ball);
			alien.hit(this);
			return alien;
		}
		return null;
	}

	/**
	* ball collisions.
	*/
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
	
	/**
	* Function that shows the movements of power ups 
	* and specifies openings in their formulas.
	*/
	public void powerUpMovement() {
		for(PowerUp powerup:powerups) {
			if(powerup.canFall) {
				powerup.fall();
			}
			System.out.println(powerup.getObjShape().getRect());
			if(paddle.getObjShape().getShape().intersects(powerup.getObjShape().getRect())) {
				System.out.println("Collided with paddle");
				if(powerup.canbeUsed==true) {
					return;
				}
				powerup.canFall=false;
				powerup.canbeUsed=true;
				powerup.setHeight(0);
				powerup.setWidth(0);
				if(powerup instanceof MagnetPowerUp) {
					((GameView) view).getTopPanel().getMagnet_button().setVisible(true);
					break;
				}
				else if(powerup instanceof TallerPowerUp) {
					((GameView) view).getTopPanel().getTaller_button().setVisible(true);
					break;
				}
				else {
					powerup.use();
				}
			}
		}
	}
	
	/**
	* Movement of the ball.
	*/
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
	
	/**
	* Movement of the alien.
	*/
	public void alienMoved() {
		if (GamePanel.gameMode == GameMode.PAUSED) {
			return;
		}
		if (0 > alien.getX()) {
			alien.setSpeed(-alien.getSpeed());
		}
		if (alien.getX() + alien.getWidth() >= ((GameView) view).getInitialWidth()) {
			alien.setSpeed(-alien.getSpeed());
		}
		alien.setX(alien.getX() + alien.getSpeed());
	}

	/**
	* Movement of the laser.
	*/
	public void laserMoved() {
		for(LaserBall laser:laser_balls) {
			if(laser==null) {
				return;
			}
			if (GamePanel.gameMode == GameMode.PAUSED) {
				return;
			}
			laser.setX(laser.getX() + laser.getVelocityX());
			laser.setY(laser.getY() + laser.getVelocityY());
			if(laser.getY()<=0) {
				laser.setHeight(0);
				laser.setWidth(0);
			}
		}
	}
	
	/**
	* Asteroids are hit by laser by this function.
	*/
	public void laserHitAsteroid() {
		for(LaserBall laser:laser_balls) {
			if(laser==null) {continue;}
			try {
				for(Asteroid asteroid: asteroidList) {
					if(asteroid==null) {continue;}
					if(laser.getObjShape().getShape().intersects(asteroid.getObjShape().getShape().getBounds2D())) {
						asteroid.setLives(0);
						asteroid.hit(this);
					}
				}
			}
			catch(Exception e) {
				System.out.println("Exception occured!");
			}

		}
	}
	
	/**
	* updateBoosts updates the boosts for the game.
	*/
	public void updateBoosts() {
		if(paddle.onTallerPowerUp) {
			if(!taller_started_timing) {
				taller_start_time= System.currentTimeMillis();
				taller_started_timing=true;
			}
			float time_passed=System.currentTimeMillis()-taller_start_time;
			time_passed=(time_passed/1000l);
			estimated_tallerTime=time_passed;
			if(estimated_tallerTime>30f) {
				paddle.onTallerPowerUp=false;
				paddle.setWidth(paddle.getInitialWidth());
				taller_started_timing=false;
				estimated_tallerTime=0;
				((GameView) view).getTopPanel().getTaller_button().setVisible(false);
				System.out.println("End taller");
			}
			System.out.println("Taller Total time passed: " +estimated_tallerTime);
		}
		if(paddle.onWrapPowerUp) {
			if(!wrap_started_timing) {
				wrap_start_time= System.currentTimeMillis();
				wrap_started_timing=true;
			}
			float time_passed=System.currentTimeMillis()-wrap_start_time;
			time_passed=(time_passed/1000l);
			estimated_wrapTime=time_passed;
			if(estimated_wrapTime>120f) {
				paddle.onWrapPowerUp=false;
				wrap_started_timing=false;
				estimated_wrapTime=0;
				((GameView) view).getTopPanel().getWrap_label().setVisible(false);
				System.out.println("End wrap");
			}
			System.out.println("Wrap Total time passed: " +estimated_wrapTime);
		}
		if(getPaddle().onMagnet) {
			freezeBallOnPaddle(
					getBalls().get(0)
					);
		}
		if(getLaser_balls()!=null&&
				getLaser_balls().size()!=0) {
			laserMoved();
			laserHitAsteroid();
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

	/**
	* countAsteroidTypes counts the asteroids and their types.
	* 
	* @param type indicates the asteroid types.
	* 
	* @return count is the countable steps.
	*/
	private int countAsteroidTypes(AsteroidType type) {
		int count = 0;
		for (Asteroid a : asteroidList) {
			if (a.getType() == type) {
				count++;
			}
		}
		return count;
	}

	/**
	* dragAsteroid drags the asteroids except add or delete.
	* 
	* @param x is the value of integer x.
	* @param y is the value of integer y.
	* 
	* @return backup is about the removing or getting some values back.
	*/
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

	/**
	* get value of the AsteroidList.
	* 
	* @return asteroidList 
	*/
	public List<Asteroid> getAsteroidList() {
		return asteroidList;
	}

	/**
	* get value of the Balls.
	* 
	* @return balls
	*/
	public List<Ball> getBalls() {
		return balls;
	}

	/**
	* get value of the Paddle.
	* 
	* @return paddle
	*/
	public Paddle getPaddle() {
		return paddle;
	}
	
	/**
	* get value of the Alien.
	* 
	* @return alien
	*/
	public Alien getAlien() {
		return alien;
	}

	/**
	* Paddle and ball hit function.
	*/
	public void paddleHitBall() {
		for(Ball ball:balls) {	
			if (getPaddle().getObjShape().getShape().intersects(ball.getObjShape().getShape().getBounds2D())){
				float angled_value=(float) (ball.getVelocityX()*Math.cos(Math.toRadians(paddle.getRotationDegree()))*
						ball.getVelocityX()*Math.cos(Math.toRadians(paddle.getRotationDegree())));
				float value = (float) (Math.sqrt(18-angled_value));
				ball.setVelocityY(-value);
				ball.setVelocityX(ball.getVelocityX());
			}
		}
	}
	
	/**
	* Active the power ups for the game.
	* 
	* @param key indicates string.
	*/
	public void activatePowerUp(String key) {
		for(int a=0;a<powerups.size();a++) {
			if(key=="TallerPowerUp"&&powerups.get(a) instanceof TallerPowerUp) {
				if(powerups.get(a).canbeUsed) {
					powerUp=powerups.get(a);
					powerups.get(a).use();
					powerups.remove(a);
					return;
				}
			}
			if(key=="MagnetPowerUp"&&powerups.get(a) instanceof MagnetPowerUp) {
				if(powerups.get(a).canbeUsed) {
					powerUp=powerups.get(a);
					powerups.get(a).use();
					powerups.remove(a);
					getPaddle().onMagnet=true;
					return;
				}
			}
		}
	}

	/**
	* Paddle movement act.
	* 
	* @param direction indicates MoveDirection.
	*/
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

	/**
	* Paddle rotatement act.
	* 
	* @param direction indicates MoveDirection.
	*/
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

	/**
	* Reflection act from the objects.
	* 
	* @param collided_asteroid is the name of collision of the asteroids.
	* @param ball is the object that reflects from the objects.
	*/
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

	/**
	* Freezed on the paddle when the ball is so close.
	* 
	* @param ball is the object that freezed on the paddle.
	*/
	public void freezeBallOnPaddle(Ball ball) {
		if(ball==null) {
			return;
		}
		ball.setX(
				(getPaddle().getWidth()/2)+getPaddle().getX()
				);
		ball.setY(
				GameObjectFactory.BALL_Y
				);
		ball.setVelocityX(0);
		ball.setVelocityY(0);
	}
	
	/**
	* restartGame means the restart of the game.
	*/
	public void restartGame() {
		((GameView) view).createAsteroids();
		List<Ball> list= new ArrayList();
		list.add(new Ball(this, GameObjectFactory.BALL_X, GameObjectFactory.BALL_Y, 17, 17));
		setBalls(list);
		setEstimated_tallerTime(0f);
		setEstimated_wrapTime(0f);
		setWrap_start_time(0);
		setTaller_start_time(0);
		setWrap_started_timing(false);
		setTaller_started_timing(false);
		((GameView)view).getTopPanel().getLaser_label().setVisible(false);
		((GameView)view).getTopPanel().getChance_label().setVisible(false);
		((GameView)view).getTopPanel().getGangballs_label().setVisible(false);
		((GameView)view).getTopPanel().getMagnet_button().setVisible(false);
		((GameView)view).getTopPanel().getTaller_button().setVisible(false);
		((GameView)view).getTopPanel().getWrap_label().setVisible(false);
		setPaddle(new Paddle(this, GameObjectFactory.PADDLE_X, GameObjectFactory.PADDLE_Y, 120, 10));
	}
	
	/**
	* reflectFromAlien means the reflection of the aliens
	* 
	* @param collided_alien is the result of collisions.
	* @param ball is a object.
	*/
	public void reflectFromAlien(Alien collided_alien,Ball ball) {
		double posX = (double) collided_alien.getX() - ball.getX();
		double posY = (double) collided_alien.getY() - ball.getY();
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
	
	/**
	* removeAsteroid removes the asteroids except add or drag.
	* 
	* @param x is the value of integer x.
	* @param y is the value of integer y.
	*/
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

	/**
	* setAsteroids sets asteroidList.
	* 
	* @param asteroidList is a list of Asteroids.
	*/
	public void setAsteroids(List<Asteroid> asteroidList) {
		this.asteroidList = asteroidList;
	}

	/**
	* setBalls sets balls.
	* 
	* @param balls is a list of balls.
	*/
	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	/**
	* setPaddle sets paddle.
	* 
	* @param paddle is a parameter.
	*/
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	/**
	* setAlien sets aliens.
	* 
	* @param alien is a parameter.
	*/
	public void setAlien(Alien alien) {
		this.alien = alien;
	}

	/**
	* getPowerups gets power ups.
	* 
	* @return powerups
	*/
	public List<PowerUp> getPowerups() {
		return powerups;
	}

	/**
	* setPowerups sets power ups.
	* 
	* @param powerups is a parameter.
	*/
	public void setPowerups(List<PowerUp> powerups) {
		this.powerups = powerups;
	}

	/**
	* getLaser_balls gets laser balls.
	* 
	* @return laser_balls
	*/
	public List<LaserBall> getLaser_balls() {
		return laser_balls;
	}

	/**
	* setLaser_balls sets laser balls.
	* 
	* @param laser_balls is a parameter.
	*/
	public void setLaser_balls(List<LaserBall> laser_balls) {
		this.laser_balls = laser_balls;
	}

	/**
	* getEstimated_tallerTime gets taller time.
	* 
	* @return estimated_tallerTime
	*/
	public float getEstimated_tallerTime() {
		return estimated_tallerTime;
	}

	/**
	* setEstimated_tallerTime sets taller time.
	* 
	* @param estimated tallerTime is a parameter.
	*/
	public void setEstimated_tallerTime(float estimated_tallerTime) {
		this.estimated_tallerTime = estimated_tallerTime;
	}

	/**
	* getEstimated_wrapTime gets wrap time.
	* 
	* @return estimated_wrapTime
	*/
	public float getEstimated_wrapTime() {
		return estimated_wrapTime;
	}

	/**
	* setEstimated_wrapTime sets wrap time.
	* 
	* @param estimated_wrapTime is a parameter.
	*/
	public void setEstimated_wrapTime(float estimated_wrapTime) {
		this.estimated_wrapTime = estimated_wrapTime;
	}

	/**
	* getWrap_start_time gets start time.
	* 
	* @return wrap_start_time
	*/
	public long getWrap_start_time() {
		return wrap_start_time;
	}

	/**
	* setWrap_start_time sets start time.
	* 
	* @param wrap_start_time is a parameter.
	*/
	public void setWrap_start_time(long wrap_start_time) {
		this.wrap_start_time = wrap_start_time;
	}

	/**
	* getTaller_start_time gets start time.
	* 
	* @return taller_start_time
	*/
	public long getTaller_start_time() {
		return taller_start_time;
	}

	/**
	* getTaller_start_time gets start time.
	* 
	* @param taller_start_time is a parameter.
	*/
	public void setTaller_start_time(long taller_start_time) {
		this.taller_start_time = taller_start_time;
	}

	/**
	* isWrap_started_timing indicates started time.
	* 
	* @return wrap_started_timing
	*/
	public boolean isWrap_started_timing() {
		return wrap_started_timing;
	}

	/**
	* isWrap_started_timing indicates started time.
	* 
	* @param wrap_started_timing is a parameter.
	*/
	public void setWrap_started_timing(boolean wrap_started_timing) {
		this.wrap_started_timing = wrap_started_timing;
	}

	/**
	* isTaller_started_timing indicates started time.
	* 
	* @return taller_started_timing
	*/
	public boolean isTaller_started_timing() {
		return taller_started_timing;
	}

	/**
	* setTaller_started_timing sets started time.
	* 
	* @param taller_started_timing is a parameter.
	*/
	public void setTaller_started_timing(boolean taller_started_timing) {
		this.taller_started_timing = taller_started_timing;
	}


	@Override
	public void allGamesLoaded(ArrayList<Game> games, String response) {
	}

	@Override
	public void gameSaved(String response) {
		// TODO Update UI with game saved response
		
	}

	@Override
	public void gameNotSaved(String response) {
		// TODO Update UI with game not saved response
		System.err.println(response);
	}

	@Override
	public void gameNotLoaded(String response) {
	}
	
	public void gameSaveButtonClicked(DataType location) {
		switch (location) {
		case DB:
			getApp().getDatabaseAdapter()
			.saveTheGame(Game.getCurrentGame());
			break;
		case FILE:
			getApp().getFileManager()
			.saveTheGame(Game.getCurrentGame());
			break;
		}
	}

	public Game getGame() {
		return game;
	}
	public GameView getGameView() {
		return ((GameView) view);
	}
	public void setGame(Game game) {
		this.game = game;
	}

	public List<GameObject> getList_objects() {
		return list_objects;
	}

	public void setList_objects(List<GameObject> list_objects) {
		this.list_objects = list_objects;
	}

}
