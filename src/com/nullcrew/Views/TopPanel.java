package com.nullcrew.Views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	
	private GameView gameView;
	private JFrame exitFrame;
	private JButton exitButton;
	private JButton yesButton;
	private JButton noButton;

	public TopPanel(GameView gameView) {
		this.gameView = gameView;
		configureUI();
	}

	private void configureUI() {
		setBackground(Color.GREEN);
		createExitButton();
		setLayout(null);
		add(exitButton);
	}
	
	private void createExitButton() {
		exitButton = new JButton("X");
		
		exitButton.setBounds(955,10,45,25);
		exitButton.setBackground(Color.red);
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createExitFrame();
			}
		});	
	}
	
	private void createExitFrame() {
		exitFrame = new JFrame("Exit Game");
		exitFrame.setBounds(384, 288, 256, 192);
		
		createYesButton();
		createNoButton();
		
		JLabel exitLabel = new JLabel("Are you sure you want to exit?");
		exitLabel.setBounds(10, 50, 225, 15);

		exitFrame.setLayout(null);
		exitFrame.add(exitLabel);
		exitFrame.add(yesButton);
		exitFrame.add(noButton);
		
		exitFrame.setVisible(true);
	}
	
	private void createYesButton() {
		yesButton = new JButton("Yes");
		yesButton.setBounds(10,100,100,25);
		yesButton.setBackground(Color.green);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
	}
	
	private void createNoButton() {
		noButton = new JButton("No");
		noButton.setBounds(130, 100, 100, 25);
		noButton.setBackground(Color.red);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitFrame.dispose();
			}	
		});
	}
}














