package com.nullcrew.UI.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.*;

import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.GameObject;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.Paddle;
import com.nullcrew.Utilities.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	private GameView gameView;
	private Timer gameTimerUI;
	public static GameMode gameMode;
	public static Graphics paddleGraphics, asteroidGraphics, ballGraphics;
	private final int MAX_ROWS = 7;
	private final int MAX_COLUMNS = 15;
	private final int MARGIN_LEFT = 50;
	private final int MARGIN_RIGHT = 50;
	private final int MARGIN_TOP = 50;
	private final int MARGIN_BOTTOM = 200;
	private final int WIDTH = 1024;
	private final int HEIGHT = 470;
	public static List<GameObject> list_objects;

	/**
	 * Create the panel.
	 */
	public GamePanel(GameView gameView) {
		this.gameView = gameView;
		list_objects= new ArrayList<GameObject>();
		createGameObjects();
		configureUI();

		requestFocusInWindow();
		addKeyListener(this);
		addMouseListener( this);
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
		gameView.getGameController().setBall(GameObjectFactory.createBall());
		list_objects.add(gameView.getGameController().getPaddle());
		list_objects.add(gameView.getGameController().getBall());
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
		for(GameObject object: list_objects) {
			if(object instanceof Paddle) {
				paintPaddle(g);	
			}

			if(object instanceof Ball) {
				g.setColor(Color.red);
				ballGraphics = g;
				
				Graphics2D g2 = (Graphics2D) GamePanel.ballGraphics;
				g2.fillOval(gameView.getGameController().getBall().getX(),
						gameView.getGameController().getBall().getY(),
						gameView.getGameController().getBall().getWidth(),
						gameView.getGameController().getBall().getHeight());
			}

			if(object instanceof Asteroid) {
				g.setColor(Color.BLACK);
				asteroidGraphics = g;
				Graphics2D g2= (Graphics2D) GamePanel.asteroidGraphics;
				List<Asteroid> asteroidList = gameView.getGameController().getAsteroidList();
				for(Asteroid a: asteroidList){
					g2.setColor(a.getColor());
					g2.fill3DRect(a.getX(), a.getY(), a.getWidth(), a.getHeight(), true);
				}
				g.setColor(Color.BLACK);
			}
		}
	}

	private void paintPaddle(Graphics g) {

		super.paintComponent( g );
	    Graphics2D g2d = (Graphics2D) g;

	    Rectangle2D rect = new Rectangle2D.Double( 
	    		gameView.getGameController().getPaddle().getX(),
				gameView.getGameController().getPaddle().getY(),
				gameView.getGameController().getPaddle().getWidth(),
				gameView.getGameController().getPaddle().getHeight());

	    AffineTransform transform = new AffineTransform();

	    transform.rotate(Math.toRadians(gameView.getGameController().getPaddle().getRotationDegree()),	    		
	    		gameView.getGameController().getPaddle().getX()+gameView.getGameController().getPaddle().getWidth()/2,
				gameView.getGameController().getPaddle().getY()-gameView.getGameController().getPaddle().getHeight()/2);


	    Shape s = transform.createTransformedShape(rect);

	    g2d.fill( s );
	    g2d.draw( s );
	}

	public int getXLocationSpace(){
		return (WIDTH - MARGIN_LEFT - MARGIN_RIGHT - GameObjectFactory.ASTEROID_WIDTH) / (MAX_COLUMNS - 1);
	}

	public int getYLocationSpace(){
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
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_LEFT): {
			gameView.getGameController().paddleMoved(MoveDirection.LEFT);
			break;
		}
		case (KeyEvent.VK_RIGHT): {
			gameView.getGameController().paddleMoved(MoveDirection.RIGHT);
			break;
		}
		case (KeyEvent.VK_A): {
			gameView.getGameController().paddleRotated(MoveDirection.UP);
			break;
		}
		case (KeyEvent.VK_D): {
			gameView.getGameController().paddleRotated(MoveDirection.DOWN);
			break;
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
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameView.getGameController().ballMoved();
		gameView.getGameController().paddleHitBall();
		Asteroid asteroid = gameView.getGameController().ballHitAsteroid();
		if(asteroid!=null)
		{
			gameView.getGameController().reflectFromAsteroid(asteroid);
		}   				

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON3) { //this is right click.
			int x = mouseEvent.getX();
			int y = mouseEvent.getY();
			Object[] result = gameView.getGameController().removeAsteroid(x, y); //returns Asteroid, MessageType
			if(result[1] == MessageType.NoAsteroidInThisLocation){
				JOptionPane.showMessageDialog(null, "No asteroid to remove in this location!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(result[1] == MessageType.MinThresholdErrorTotal) {
				JOptionPane.showMessageDialog(null, "Total min threshold (at least 75) is violated!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(result[1] == MessageType.MinThresholdErrorFirm) {
				JOptionPane.showMessageDialog(null, "Firm min threshold (at least 10) is violated!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(result[1] == MessageType.MinThresholdErrorExplosive) {
				JOptionPane.showMessageDialog(null, "Explosive min threshold (at least 5) is violated!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(result[1] == MessageType.MinThresholdErrorGift) {
				JOptionPane.showMessageDialog(null, "Gift min threshold (at least 10) is violated!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private Asteroid draggedAsteroid = null;
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1) { //this is left click.
			int x = mouseEvent.getX();
			int y = mouseEvent.getY();
			draggedAsteroid = gameView.getGameController().dragAsteroid(x, y);
			if(draggedAsteroid != null) {
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1 && draggedAsteroid != null) { //this is left click.
			int x = mouseEvent.getX();
			int y = mouseEvent.getY();
			boolean success = gameView.getGameController().addAsteroid(draggedAsteroid, x, y);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			if(!success){
				JOptionPane.showMessageDialog(null, "Can not drop over an existing asteroid!", "Error", JOptionPane.ERROR_MESSAGE);
				gameView.getGameController().addAsteroid(draggedAsteroid, draggedAsteroid.getX(), draggedAsteroid.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {}
	
}
