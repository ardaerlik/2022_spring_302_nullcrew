package com.nullcrew.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.nullcrew.Models.*;
import com.nullcrew.Utilities.*;

public class GamePanel extends JPanel
	implements ActionListener, KeyListener {
	
	private GameView gameView;
	private Timer gameTimerUI;
	public static GameMode gameMode;
	public static Graphics paddleGraphics, asteroidGraphics;
	
	/**
	 * Create the panel.
	 */
	public GamePanel(GameView gameView) {
		this.gameView = gameView;
		
		createGameObjects();
		configureUI();
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		restartAction();
		gameMode = GameMode.RESUMED;
	}
	
	private void restartAction() {
		gameTimerUI = new Timer(20, this);
		gameTimerUI.restart();
	}
	
	public void createGameObjects() {
		gameView.getGameController().setPaddle(GameObjectFactory.createPaddle());
	}
	
	private void configureUI() {
		setBackground(Color.ORANGE);
	}
	
	private void pauseTheGame() {
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
	
	private void resumeTheGame() {
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
	
	private void paintPaddle(Graphics g) {
		g.setColor(Color.BLACK);
		paddleGraphics = g;
		Graphics2D g2= (Graphics2D) GamePanel.paddleGraphics;
		g2.rotate(Math.toRadians(gameView.getGameController().getPaddle().getRotationDegree()),
				gameView.getGameController().getPaddle().getX()+gameView.getGameController().getPaddle().getWidth()/2,
				gameView.getGameController().getPaddle().getY());
		g2.fill3DRect(gameView.getGameController().getPaddle().getX(),
				gameView.getGameController().getPaddle().getY(),
				gameView.getGameController().getPaddle().getWidth(),
				gameView.getGameController().getPaddle().getHeight(),true);
	}

	private void paintAsteroids(Graphics g) {
		g.setColor(Color.BLACK);
		asteroidGraphics = g;
		Graphics2D g2= (Graphics2D) GamePanel.asteroidGraphics;
		List<Asteroid> asteroidList = gameView.getGameController().getAsteroidList();
		for(Asteroid a: asteroidList){
			g2.setColor(a.getColor());
			switch (a.getType()) {
				case "simple":
					g2.fill3DRect(a.getX(), a.getY(), a.getWidth(), a.getHeight(), true);
					break;
				case "firm":
					g2.fill3DRect(a.getX(), a.getY(), a.getWidth(), a.getHeight(), true);
					break;
				case "explosive":
					g2.fill3DRect(a.getX(), a.getY(), a.getWidth(), a.getHeight(), true);
					break;
				case "gift":
					g2.fill3DRect(a.getX(), a.getY(), a.getWidth(), a.getHeight(), true);
					break;
			}
		}
		g.setColor(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintPaddle(g);
		if( gameView.getNumOfAsteroidTypes() != null) {
			paintAsteroids(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
