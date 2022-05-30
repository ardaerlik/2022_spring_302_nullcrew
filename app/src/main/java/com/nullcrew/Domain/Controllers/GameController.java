package com.nullcrew.Domain.Controllers;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.Alien;
import com.nullcrew.Domain.Models.AlienType;
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
import com.nullcrew.Domain.Models.SimpleAsteroid;
import com.nullcrew.UI.Views.GamePanel;
import com.nullcrew.UI.Views.GameView;
import com.nullcrew.UI.Views.MenuView;
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
	private int actAlienCount = 0;
	private int destroyedAsteroid;
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
	private boolean gameOver=false;
	public GameController(GameView gameView, AlienAsteroidGame app) {
		super(gameView, app);
		app.getDatabaseAdapter()
		.subscribeSaveLoadObserver(this);
		app.getFileManager()
		.subscribeSaveLoadObserver(this);
		List<GameObject> list = new ArrayList<GameObject>();
		this.setList_objects(list);
		game = new Game();
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

	public void appearAlien() {
		((GameView) view).getGamePanel().createAlien();
		actAlienCount=0;
	}
	
	public void actAlien() {
		if (getAlien() == null) return;
		actAlienCount++;
		
		if(getAlien().getType() == AlienType.Repairing) {
			
			if (actAlienCount%250 == 0) getAlien().act(this);
		} else if(getAlien().getType() == AlienType.Cooperative) {
			getAlien().act(this);
			setAlien(null);
		} else if (getAlien().getType() == AlienType.TimeWasting) {
			if (actAlienCount == 1) {
				getAlien().act(this);
			}
			if (actAlienCount%75 == 0) {
				this.unfreezeAsteroids();
			}
		}
	}

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
	
	public void addNewSimpleAsteroid() {
	
		for (int x = new Random().nextInt(500)+100; x < ((GameView) view).getInitialWidth(); x += 40) {
			for (int y = new Random().nextInt(500)+100; y < 900; y += 40) {
				Asteroid asteroid2 = new SimpleAsteroid(this, x, y, 20, 20, 0); 
				if (addAsteroid(asteroid2, asteroid2.getX(), asteroid2.getY()) == true) return;
			}
		}
	}
	
	public void freezeAsteroids() {
		for (int i = 0; i < 8; i++) {
			Random r = new Random();
			int x = r.nextInt(getAsteroidList().size());
			if(!getAsteroidList().get(x).getFreezed()) getAsteroidList().get(x).setFreezed(true);
			else { i--;}
		}
	}
	
	private void unfreezeAsteroids() {
		for (Asteroid a : getAsteroidList()) {
			if (a.getFreezed()) a.setFreezed(false);
		}	
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
	
	public Alien ballHitAlien(Ball ball) {
		if (ball.getObjShape().getShape().intersects(alien.getObjShape().getRect())) {
			reflectFromAlien(alien,ball);
			alien.hit(this);
			return alien;
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

	public MessageType checkNumAsteroids(int[] numOfAsteroidTypes) {
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
	
	public int getDestroyedAsteroid() {
		return destroyedAsteroid;
	}
	
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

	public void ballFalls() {
		Ball temp_ball=null;
		for(Ball ball:balls) {
			if (getPaddle().getObjShape().getShape().getBounds().getY() < ball.getObjShape().getShape().getBounds().getY()){
				temp_ball=ball;
				break;
			}
		}

		if(temp_ball!=null) {
			balls.remove(temp_ball);
		}
		
		if(balls.size()==0) {
			System.out.println(game);
			((GameView) view).getTopPanel().setLives(game.getLives()-1);
			game.setLives(game.getLives()-1);
			respawnBall();
		}
		if(game.getLives() <= 0){
			this.setGameOver(true);
			((GameView) view).gameOver();
			AlienAsteroidGame.getInstance().changeView(((GameView) view), new MenuView());
		}
	}
	
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
				(getPaddle().getWidth()/2)+getPaddle().getX()
				);
		ball.setY(
				GameObjectFactory.BALL_Y
				);
		ball.setVelocityX(0);
		ball.setVelocityY(0);
	}
	public void respawnBall() {
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
		((GameView)view).getTopPanel().setLives(game.getLives());
		setPaddle(new Paddle(this, GameObjectFactory.PADDLE_X, GameObjectFactory.PADDLE_Y, 120, 10));
	}
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

	public List<PowerUp> getPowerups() {
		return powerups;
	}

	public void setPowerups(List<PowerUp> powerups) {
		this.powerups = powerups;
	}

	public List<LaserBall> getLaser_balls() {
		return laser_balls;
	}

	public void setLaser_balls(List<LaserBall> laser_balls) {
		this.laser_balls = laser_balls;
	}

	public float getEstimated_tallerTime() {
		return estimated_tallerTime;
	}

	public void setEstimated_tallerTime(float estimated_tallerTime) {
		this.estimated_tallerTime = estimated_tallerTime;
	}

	public float getEstimated_wrapTime() {
		return estimated_wrapTime;
	}

	public void setEstimated_wrapTime(float estimated_wrapTime) {
		this.estimated_wrapTime = estimated_wrapTime;
	}

	public long getWrap_start_time() {
		return wrap_start_time;
	}

	public void setWrap_start_time(long wrap_start_time) {
		this.wrap_start_time = wrap_start_time;
	}

	public long getTaller_start_time() {
		return taller_start_time;
	}

	public void setTaller_start_time(long taller_start_time) {
		this.taller_start_time = taller_start_time;
	}

	public boolean isWrap_started_timing() {
		return wrap_started_timing;
	}

	public void setWrap_started_timing(boolean wrap_started_timing) {
		this.wrap_started_timing = wrap_started_timing;
	}

	public boolean isTaller_started_timing() {
		return taller_started_timing;
	}

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

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setDestroyedAsteroid(int destroyedAsteroid) {
		this.destroyedAsteroid = destroyedAsteroid;
	}

}
