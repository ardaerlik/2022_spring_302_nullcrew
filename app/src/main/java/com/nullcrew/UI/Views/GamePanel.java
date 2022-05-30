package com.nullcrew.UI.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.nullcrew.Domain.Models.Alien;
import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.AsteroidType;
import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.GameMode;
import com.nullcrew.Domain.Models.GameObject;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.LaserBall;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.MoveDirection;
import com.nullcrew.Domain.Models.Paddle;
import com.nullcrew.Domain.Models.PowerUp;
import com.nullcrew.Domain.Models.TallerPowerUp;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private GameView gameView;
	private Timer gameTimerUI;
	public static GameMode gameMode;
	public static Graphics paddleGraphics, asteroidGraphics, ballGraphics, alienGraphics,laserGraphics,powerUpGraphics;
	private final int MAX_ROWS = 11;
	private final int MAX_COLUMNS = 15;
	private final int MARGIN_LEFT = 50;
	private final int MARGIN_RIGHT = 50;
	private final int MARGIN_TOP = 50;
	private final int MARGIN_BOTTOM = 200;
	private final int WIDTH = 1536;
	private final int HEIGHT = 1116;
	private ArrayList<MoveDirection> pressedKeys;
	private int pressedKeysLoc;
	private int pressedKeysLocInt;
	private boolean isValidLocation;
	private Asteroid draggedAsteroid;
	private double initialX, initialY;
	/**
	 * Create the panel.
	 */
	public GamePanel(GameView gameView) {
		GameObjectFactory.gameController = gameView.getGameController();
		this.gameView = gameView;

		
		this.pressedKeys = new ArrayList<MoveDirection>();
		this.pressedKeysLoc = 0;
		this.pressedKeysLocInt = 0;
		draggedAsteroid = null;
		initialX=-1;
		initialY=-1;

		createGameObjects();
		configureUI();

		requestFocusInWindow();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		restartAction();
		setFocusable(true);
		gameMode = GameMode.PAUSED;
	}

	private void restartAction() {
		gameTimerUI = new Timer(20, this);
		gameTimerUI.restart();
	}

	public void createGameObjects() {
		gameView.getGameController().setPaddle(GameObjectFactory.createPaddle());
		List<Ball> list= new ArrayList();
		list.add(GameObjectFactory.createBall());
		gameView.getGameController().setBalls(list);
		gameView.getGameController().getList_objects().add(gameView.getGameController().getPaddle());
		for(Ball ball: list) {
			gameView.getGameController().getList_objects().add(ball);
		}

	}
	
	public void createAlien() {
		gameView.getGameController().setAlien(GameObjectFactory.createAlien());
		gameView.getGameController().getList_objects().add(gameView.getGameController().getAlien());
	}

	private void configureUI() {
		setBackground(Color.ORANGE);
	}

	public void pauseTheGame() {
		switch (gameMode) {
		case RESUMED: {
			gameMode = GameMode.PAUSED;
			gameTimerUI.stop();
			break;
		}
		case PAUSED: {
			break;
		}
		}
	}

	public void resumeTheGame() {
		switch (gameMode) {
		case PAUSED: {
			gameMode = GameMode.RESUMED;
			gameTimerUI.start();
			break;

		}
		case RESUMED: {
			break;
		}
		}
	}

	private void paintObjects(Graphics g) {
		for (GameObject object : gameView.getGameController().getList_objects()) {
			if (object instanceof Paddle) {
				paintPaddle(g);
			}

			if (object instanceof Ball) {
				g.setColor(Color.red);
				ballGraphics = g;

				Graphics2D g2 = (Graphics2D) GamePanel.ballGraphics;
				for(Ball ball:gameView.getGameController().getBalls()) {
					g2.fillOval((int)ball.getX(),
							(int)ball.getY(),
							ball.getWidth(),
							ball.getHeight());
				}

			}

			if (object instanceof Asteroid) {
				g.setColor(Color.BLACK);
				asteroidGraphics = g;
				Graphics2D g2 = (Graphics2D) GamePanel.asteroidGraphics;
				List<Asteroid> asteroidList = gameView.getGameController().getAsteroidList();
				for (Asteroid a : asteroidList) {
					g2.setColor(a.getColor());
					if(a.getType() == AsteroidType.Explosive){
						g2.fillOval( (int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight());
					}else {
						g2.fill3DRect( (int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight(), true);
					}
				}
				g.setColor(Color.BLACK);
			}
			
			if (object instanceof Alien) {
				g.setColor(Color.BLUE);
				alienGraphics = g;
				Graphics2D g2 = (Graphics2D) GamePanel.alienGraphics;
				
				Alien alien = gameView.getGameController().getAlien();
				if (alien != null) {
					g2.setColor(alien.getColor());
					g2.fill3DRect((int)alien.getX(), (int)alien.getY(), alien.getWidth(), alien.getHeight(), true);
				}
				
				
			}
			if(object instanceof LaserBall) {
				g.setColor(Color.white);
				laserGraphics=g;
				Graphics2D g2 = (Graphics2D) GamePanel.laserGraphics;
				
				for(LaserBall laser:gameView.getGameController().getLaser_balls()) {
					g2.fillOval(
							(int)laser.getX(),
							(int)laser.getY(),
							laser.getWidth(),
							laser.getHeight());
				}

			}
		}
		paintPowerups(g);

	}
	

	private void paintPaddle(Graphics g) {
		if (pressedKeysLoc < pressedKeys.size()) {
			if (pressedKeysLocInt == 0) {
				isValidLocation = isValidPosition(pressedKeys.get(pressedKeysLoc));
			}
			
			if (isValidLocation) {
				if (pressedKeysLocInt < 5) {
					if (pressedKeys.get(pressedKeysLoc) == MoveDirection.LEFT){
						gameView.getGameController().paddleMoved(MoveDirection.LEFT);;
					} else {
						gameView.getGameController().paddleMoved(MoveDirection.RIGHT);;
					}
					pressedKeysLocInt++;
				} else {
					pressedKeysLoc++;
					pressedKeysLocInt = 0;
				}
			} else {
				pressedKeysLoc++;
			}
		}
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		float rate_x= ((float)gameView.getFrame().getWidth()/(float) gameView.getInitialWidth());
		float rate_y =((float)gameView.getFrame().getHeight()/(float) gameView.getInitialHeight());
		double size_x=(double)rate_x;
		double size_y= (double)rate_y;
		g2d.scale(size_x, size_y);
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(gameView.getGameController().getPaddle().getRotationDegree()),
				gameView.getGameController().getPaddle().getX()
						+ gameView.getGameController().getPaddle().getWidth() / 2,
				gameView.getGameController().getPaddle().getY()
						- gameView.getGameController().getPaddle().getHeight() / 2);
		gameView.getGameController().getPaddle().getObjShape().setTransform(transform);
		gameView.getGameController().getPaddle().changeShape();
		Shape s = transform.createTransformedShape(gameView.getGameController().getPaddle().getObjShape().getRect());
		gameView.getGameController().getPaddle().getObjShape().setShape(s);
		gameView.getGameController().getPaddle().changeShape();
		g2d.fill(s);
		g2d.draw(s);
	}
	private void paintPowerups(Graphics g) {
		for(PowerUp powerup: gameView.getGameController().getPowerups()) {
			g.setColor(Color.white);
			powerUpGraphics=g;
			Graphics2D g2 = (Graphics2D) GamePanel.powerUpGraphics;
			g2.fillRect((int)powerup.getX(), (int)powerup.getY(), powerup.getWidth(), powerup.getHeight());
		}
			
	}
	private boolean isValidPosition(MoveDirection e) {
		switch (e) {
		case LEFT:
			return true;
		case RIGHT:
			return true;
		default:
			return false;
		}
	}

	public int getXLocationSpace() {
		return (WIDTH - MARGIN_LEFT - MARGIN_RIGHT - GameObjectFactory.ASTEROID_WIDTH) / (MAX_COLUMNS - 1);
	}

	public int getYLocationSpace() {
		return (HEIGHT - MARGIN_TOP - MARGIN_BOTTOM - GameObjectFactory.ASTEROID_HEIGHT) / (MAX_ROWS - 1);
	}

	public int getLeftMargin() {
		return MARGIN_LEFT;
	}

	public int getRightMargin() {
		return MARGIN_RIGHT;
	}

	public int getTopMargin() {
		return MARGIN_TOP;
	}

	public int getBottomMargin() {
		return MARGIN_BOTTOM;
	}

	public int getMaxRows() {
		return MAX_ROWS;
	}

	public int getMaxColumns() {
		return MAX_COLUMNS;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintObjects(g);
		if(draggedAsteroid != null){
			g.setColor(draggedAsteroid.getColor());
			g.fill3DRect((int)draggedAsteroid.getX(), (int)draggedAsteroid.getY(), draggedAsteroid.getWidth(), draggedAsteroid.getHeight(), true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case (KeyEvent.VK_LEFT): {
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			pressedKeys.add(MoveDirection.LEFT);
			break;
		}
		case (KeyEvent.VK_RIGHT): {
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			pressedKeys.add(MoveDirection.RIGHT);
			break;
		}
		case (KeyEvent.VK_A): {
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			gameView.getGameController().paddleRotated(MoveDirection.UP);
			break;
		}
		case (KeyEvent.VK_D): {
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			gameView.getGameController().paddleRotated(MoveDirection.DOWN);
			break;
		}
		case(KeyEvent.VK_T):{
			if(e.getKeyChar()==KeyEvent.VK_M) {
				return;
			}
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			gameView.getGameController().activatePowerUp("TallerPowerUp");
		}
		case(KeyEvent.VK_M):{
			if(e.getKeyChar()==KeyEvent.VK_T) {
				return;
			}
			if(gameMode==GameMode.PAUSED) {
				return;
			}
			gameView.getGameController().activatePowerUp("MagnetPowerUp");
			
		}
		case (KeyEvent.VK_ESCAPE): {
			switch (gameMode) {
			case PAUSED: {
				resumeTheGame();
				break;
			}
			case RESUMED: {
				pauseTheGame();
				break;
			}
			}
			break;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(!gameView.getGameController().isGameOver()) {
			gameView.getGameController().ballMoved();
			gameView.getGameController().paddleHitBall();
			gameView.getGameController().ballFalls(); // added for the lives feature
			gameView.getGameController().ballHitAsteroid();
			gameView.getGameController().ballHitBall();
			gameView.getGameController().updateBoosts();
			gameView.getGameController().powerUpMovement();
		}
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		float rate_x= ((float)gameView.getFrame().getWidth()/(float) gameView.getInitialWidth());
		float rate_y =((float)gameView.getFrame().getHeight()/(float) gameView.getInitialHeight());
		double size_x=(double)rate_x;
		double size_y= (double)rate_y;
		if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
			int x = (int)(mouseEvent.getX()/size_x);
			int y = (int)(mouseEvent.getY()/size_y);
			Object[] result = gameView.getGameController().removeAsteroid(x, y);
			if (result[1] == MessageType.NoAsteroidInThisLocation) {
				JOptionPane.showMessageDialog(null, "No asteroid to remove in this location!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (result[1] == MessageType.MinThresholdErrorTotal) {
				JOptionPane.showMessageDialog(null, "Total min threshold (at least 75) is violated!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (result[1] == MessageType.MinThresholdErrorFirm) {
				JOptionPane.showMessageDialog(null, "Firm min threshold (at least 10) is violated!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (result[1] == MessageType.MinThresholdErrorExplosive) {
				JOptionPane.showMessageDialog(null, "Explosive min threshold (at least 5) is violated!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (result[1] == MessageType.MinThresholdErrorGift) {
				JOptionPane.showMessageDialog(null, "Gift min threshold (at least 10) is violated!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if(gameMode == GameMode.PAUSED) {
			return;
		}
		if(gameView.getGameController().getPaddle().onMagnet) {
			gameView.getGameController().getBalls().get(0).setVelocityX(3*Math.sin(Math.toRadians(gameView.getGameController().getPaddle().getRotationDegree()))*1.2f);
			gameView.getGameController().getBalls().get(0).setVelocityY(-3*Math.cos(Math.toRadians(gameView.getGameController().getPaddle().getRotationDegree()))*1.2f);
			gameView.getGameController().getPaddle().onMagnet=false;
		}
	}


	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		float rate_x= ((float)gameView.getFrame().getWidth()/(float) gameView.getInitialWidth());
		float rate_y =((float)gameView.getFrame().getHeight()/(float) gameView.getInitialHeight());
		double size_x=(double)rate_x;
		double size_y= (double)rate_y;
		Rectangle2D temp_paddle=new Rectangle2D.Double(gameView.getGameController().getPaddle().getX(),
				gameView.getGameController().getPaddle().getY(),
				gameView.getGameController().getPaddle().getWidth(),
				gameView.getGameController().getPaddle().getHeight()
				);
		if (mouseEvent.getButton() == MouseEvent.BUTTON1 && draggedAsteroid != null) {
			boolean success;
			int x = (int)(mouseEvent.getX()/size_x);
			int y = (int)(mouseEvent.getY()/size_y);
			boolean intersect_ball=false;
			Rectangle2D temp_asteroid= new Rectangle2D.Double(x,y,draggedAsteroid.getWidth(),draggedAsteroid.getHeight());
			for(Ball ball:gameView.getGameController().getBalls()) {
				Rectangle2D temp_ball=new Rectangle2D.Double(ball.getX(),
						ball.getY(),
						ball.getWidth(),
						ball.getHeight());
				if(temp_ball.intersects(temp_asteroid)) {
					intersect_ball=true;
					break;
				}
			}
			if(!intersect_ball&&temp_paddle.intersects(temp_asteroid)) {
				success=false;
			}
			else if(!intersect_ball&&temp_asteroid.getCenterY()>=temp_paddle.getCenterY() || 0>temp_asteroid.getCenterY()-temp_asteroid.getHeight()/2){
				success=false;
			}
			else if(!intersect_ball&&(temp_asteroid.getCenterX()+temp_asteroid.getWidth()/2)>=gameView.getInitialWidth()) {
				success=false;
			}
			else if(!intersect_ball&&temp_asteroid.getCenterY()+temp_asteroid.getHeight()*3>=gameView.getGameController().getPaddle().getY()) {
				success=false;
			}
			else {
				if(!intersect_ball) {
					draggedAsteroid.setX(initialX);
					draggedAsteroid.setY(initialY);
					success = gameView.getGameController().addAsteroid(draggedAsteroid, x, y);
				}
				else {
					success=false;
				}
			}
			
			if (!success) {
				JOptionPane.showMessageDialog(null, "Can not drop the dragged asteroid on this position", "Error",
						JOptionPane.ERROR_MESSAGE);
				gameView.getGameController().addAsteroid(draggedAsteroid, initialX, initialY);
			}
			draggedAsteroid=null;
			initialX=-1;
			initialY=-1;
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
		if (gameMode == GameMode.RESUMED) return;
		float rate_x= ((float)gameView.getFrame().getWidth()/(float) gameView.getInitialWidth());
		float rate_y =((float)gameView.getFrame().getHeight()/(float) gameView.getInitialHeight());
		double size_x=(double)rate_x;
		double size_y= (double)rate_y;
		if(initialX==-1){
			int x = (int)(mouseEvent.getX()/size_x);
			int y = (int)(mouseEvent.getY()/size_y);
			draggedAsteroid = gameView.getGameController().dragAsteroid(x, y);
			if(draggedAsteroid==null) {
				return;
			}
			initialX = draggedAsteroid.getX();
			initialY = draggedAsteroid.getY();
		}else{
			int x = (int)(mouseEvent.getX()/size_x);
			int y = (int)(mouseEvent.getY()/size_y);
			draggedAsteroid.setX(x);
			draggedAsteroid.setY(y);
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {
	}


}
