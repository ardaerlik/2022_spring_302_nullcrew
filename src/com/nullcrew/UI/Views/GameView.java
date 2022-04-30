package com.nullcrew.UI.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nullcrew.Domain.Controllers.GameController;
import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.GameObjectFactory;

public class GameView {

	private JFrame frame;
	private JPanel topPanel;
	private JPanel belowPanel;
	private GamePanel gamePanel;
	private GameController gameController;
	private int[] numOfAsteroidTypes;
	public static final int WIDTH = 1536;
	public static final int HEIGHT = 1152;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView window = new GameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public GameView() {
		gameController = new GameController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout();
		frame.getContentPane().setLayout(borderLayout);

		topPanel = new TopPanel(this);
		topPanel.setPreferredSize(new Dimension(WIDTH,172));
		frame.getContentPane().add(topPanel,BorderLayout.NORTH);

		gamePanel = new GamePanel(this);
		gamePanel.setPreferredSize(new Dimension(WIDTH,762));
		frame.getContentPane().add(gamePanel,BorderLayout.CENTER);

		belowPanel = new JPanel();
		belowPanel.setBackground(Color.CYAN);
		gamePanel.setPreferredSize(new Dimension(WIDTH,500));
		frame.getContentPane().add(belowPanel,BorderLayout.SOUTH);
		
		frame.setMinimumSize(new Dimension(1024,768));
	}
	
	public void createAsteroids() {
		int[] locSpaces = { gamePanel.getXLocationSpace(), gamePanel.getYLocationSpace() };
		int[] margins = { gamePanel.getLeftMargin(), gamePanel.getTopMargin(), gamePanel.getRightMargin(),
				gamePanel.getBottomMargin() };
		int[] maxRowsColumns = { gamePanel.getMaxRows(), gamePanel.getMaxColumns() };
		List<Asteroid> asteroidList = GameObjectFactory.createAsteroids(numOfAsteroidTypes, locSpaces, margins,
				maxRowsColumns);
		gameController.setAsteroids(asteroidList);
		gamePanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getTopPanel() {
		return topPanel;
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
		this.numOfAsteroidTypes = numOfAsteroidTypes; // number of simple, firm, explosive and gift asteroids.
	}

	public int[] getNumOfAsteroidTypes() {
		return numOfAsteroidTypes;
	}
}
