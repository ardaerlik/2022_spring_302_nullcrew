package com.nullcrew.Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel
	implements ActionListener, KeyListener {
	
	private Timer gameTimer;
	private Rectangle paddle;
	private int paddlePositionX = 100;
	private int paddleRotation = 0;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		configureUI();
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		gameTimer = new Timer(20, this);
		gameTimer.start();
	}
	
	private void configureUI() {
		this.setBackground(Color.ORANGE);
		createPaddle();
	}
	
	private void createPaddle() {
		paddle = new Rectangle(100, 470, 120, 10);
	}
	
	private void paintPaddle(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(paddlePositionX, paddle.y, paddle.width, paddle.height);
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
				paddlePositionX -= 5;
				break;
			}
			case (KeyEvent.VK_RIGHT): {
				paddlePositionX += 5;
				break;
			}
			case (KeyEvent.VK_A): {
				break;
			}
			case (KeyEvent.VK_D): {
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
