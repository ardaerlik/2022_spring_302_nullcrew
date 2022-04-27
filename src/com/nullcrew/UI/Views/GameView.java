package com.nullcrew.UI.Views;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

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
	private final int WIDTH = 1536;
	private final int HEIGHT = 1152;

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
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		topPanel = new TopPanel(this);
		springLayout.putConstraint(SpringLayout.NORTH, topPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, topPanel, 172, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, topPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, topPanel, WIDTH, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(topPanel);

		gamePanel = new GamePanel(this);
		springLayout.putConstraint(SpringLayout.NORTH, gamePanel, 172, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, gamePanel, 934, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, gamePanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, gamePanel, WIDTH, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(gamePanel);

		belowPanel = new JPanel();
		belowPanel.setBackground(Color.CYAN);
		springLayout.putConstraint(SpringLayout.NORTH, belowPanel, 934, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, belowPanel, 1152, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, belowPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, belowPanel, WIDTH, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(belowPanel);
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
