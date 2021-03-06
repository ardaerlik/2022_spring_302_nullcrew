package com.nullcrew.UI.Views.GameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.Domain.Models.GameObject.Asteroid;
import com.nullcrew.Domain.Models.GameObject.GameObjectFactory;
import com.nullcrew.UI.Views.AppView.AppView;

public class GameView extends AppView {
	private JPanel topPanel;
	private JPanel belowPanel;
	private GamePanel gamePanel;
	private GameController gameController;
	private int[] numOfAsteroidTypes;
	public static final int WIDTH = 1536;
	public static final int HEIGHT = 1152;

	/**
	 * Create the application.
	 */
	public GameView() {
		super(new JFrame());
		gameController = new GameController(this, AlienAsteroidGame.getInstance());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BorderLayout borderLayout = new BorderLayout();
		frame.getContentPane().setLayout(borderLayout);

		topPanel = new TopPanel(this);
		frame.getContentPane().add(topPanel,BorderLayout.NORTH);

		gamePanel = new GamePanel(this);
		frame.getContentPane().add(gamePanel,BorderLayout.CENTER);
		
		
	}
	
	public void createAsteroids() {
		int[] locSpaces = { gamePanel.getXLocationSpace(), gamePanel.getYLocationSpace()*5/6 };
		int[] margins = { gamePanel.getLeftMargin(), gamePanel.getTopMargin(), gamePanel.getRightMargin(),
				gamePanel.getBottomMargin() };

		int[] maxRowsColumns = { gamePanel.getMaxRows(), gamePanel.getMaxColumns() };
		ArrayList<Asteroid> asteroidList = GameObjectFactory.createAsteroids(numOfAsteroidTypes, locSpaces, margins,
				maxRowsColumns);
		gameController.setAsteroids(asteroidList);
		gameController.setInitialAsteroidCount(asteroidList.size());
		gamePanel.repaint();
	}

	public void gameOver(){
		JOptionPane.showMessageDialog(super.frame, "Game Over!", "Game Over!", JOptionPane.PLAIN_MESSAGE);
	}

	public TopPanel getTopPanel() {
		return (TopPanel) topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getBelowPanel() {
		return belowPanel;
	}

	public void setBelowPanel(JPanel belowPanel) {
		this.belowPanel = belowPanel;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public int getInitialWidth() {
		return WIDTH;
	}
	
	public int getInitialHeight() {
		return HEIGHT;
	}
	
	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void setNumOfAsteroidTypes(int[] numOfAsteroidTypes) {
		this.numOfAsteroidTypes = numOfAsteroidTypes;
	}

	public int[] getNumOfAsteroidTypes() {
		return numOfAsteroidTypes;
	}
	
}
