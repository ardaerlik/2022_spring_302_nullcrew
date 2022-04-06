package com.nullcrew.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.nullcrew.Models.*;
import com.nullcrew.Utilities.*;

public class GamePanel extends JPanel
	implements ActionListener, KeyListener {
	
	private GameView gameView;
	private Timer gameTimerUI;
	private GameMode gameMode;

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
	
	private void createGameObjects() {
		gameView.getGameController().setPaddle(GameObjectFactory.createPaddle());
	}
	
	private void configureUI() {
		setBackground(Color.ORANGE);
	}
	
	private void pauseTheGame() {
		switch (gameMode) {
		case RESUMED: {
			System.out.println("paused");
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
			System.out.println("resumed");
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
		g.fillRect(gameView.getGameController().getPaddle().getX(),
				gameView.getGameController().getPaddle().getY(),
				gameView.getGameController().getPaddle().getWidth(),
				gameView.getGameController().getPaddle().getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintPaddle(g);
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
