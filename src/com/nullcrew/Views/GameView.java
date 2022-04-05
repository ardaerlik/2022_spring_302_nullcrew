package com.nullcrew.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SpringLayout;

public class GameView {

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, topPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, topPanel, 115, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, topPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, topPanel, 1024, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(topPanel);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(Color.ORANGE);
		springLayout.putConstraint(SpringLayout.NORTH, gamePanel, 115, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, gamePanel, 623, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, gamePanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, gamePanel, 1024, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(gamePanel);
		
		JPanel belowPanel = new JPanel();
		belowPanel.setBackground(Color.CYAN);
		springLayout.putConstraint(SpringLayout.NORTH, belowPanel, 623, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, belowPanel, 768, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, belowPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, belowPanel, 1024, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(belowPanel);
	}
}
