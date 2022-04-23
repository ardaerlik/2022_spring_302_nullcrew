package com.nullcrew.Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.nullcrew.Models.Ball;
import com.nullcrew.Models.Paddle;

public class TopPanel extends JPanel {
	
	private GameView gameView;
	private JFrame exitFrame;
	private JButton exitButton;
	private JButton yesButton;
	private JButton noButton;
	private JButton okButton;
	private JToggleButton switchButton;
	private JPopupMenu popupMenu;
	private JLabel simpleLabel, firmLabel, explosiveLabel, giftLabel;
	private JTextField simpleField, firmField, explosiveField, giftField;
	private int[] numOfAsteroidTypes;

	public TopPanel(GameView gameView) {
		this.gameView = gameView;
		numOfAsteroidTypes = null;
		configureUI();
		setFocusable(false);
	}

	private void configureUI() {
		setFocusable(false);
		setBackground(Color.GREEN);
		createExitButton();
		setLayout(new FlowLayout());
		createAsteroidNumbersForm();
		createOkButton();
		createSwitchButton();
		createPopupMenu();
		add(simpleLabel);
		add(simpleField);
		add(firmLabel);
		add(firmField);
		add(explosiveLabel);
		add(explosiveField);
		add(giftLabel);
		add(giftField);
		add(okButton);
		add(switchButton);
		add(exitButton);
	}

	private void createAsteroidNumbersForm(){
		simpleLabel = new JLabel("Simple Asteroids:");
		simpleField = new JTextField(5);
		firmLabel = new JLabel("Firm Asteroids:");
		firmField = new JTextField(5);
		explosiveLabel = new JLabel("Explosive Asteroids:");
		explosiveField = new JTextField(5);
		giftLabel = new JLabel("Gift Asteroids:");
		giftField = new JTextField(5);
	}

	public int[] getNumOfAsteroidTypes(){
		return numOfAsteroidTypes;
	}

	private void createOkButton() {
		okButton = new JButton("Start");
		okButton.setBounds(10,100,100,25);
		okButton.setBackground(Color.green);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().setFocusable(true);
				gameView.getGamePanel().requestFocusInWindow();
				numOfAsteroidTypes = new int[4];
				numOfAsteroidTypes[0] = Integer.parseInt( simpleField.getText());
				numOfAsteroidTypes[1] = Integer.parseInt( firmField.getText());
				numOfAsteroidTypes[2] = Integer.parseInt( explosiveField.getText());
				numOfAsteroidTypes[3] = Integer.parseInt( giftField.getText());
				gameView.setNumOfAsteroidTypes(numOfAsteroidTypes);
				gameView.createAsteroids();
				gameView.getGameController().setBall(new Ball(155, 445, 17, 17));
				gameView.getGameController().setPaddle(new Paddle (100, 470, 120, 10));
				okButton.setText("Restart");
				gameView.getGamePanel().resumeTheGame();
				
			}
		});
	}

	private void createSwitchButton() {
		switchButton = new JToggleButton("Switch Mode");
		switchButton.setBounds(10,100,200,25);
		switchButton.setBackground(Color.green);
		switchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().setFocusable(true);
				gameView.getGamePanel().requestFocusInWindow();
				JToggleButton b = switchButton;
				if (b.isSelected()) {
					popupMenu.show(b, 0, b.getBounds().height);
				} else {
					popupMenu.setVisible(false);
				}
			}
		});
	}

	private void createPopupMenu() {
		popupMenu = new JPopupMenu();
		popupMenu.add("Build Mode");
		popupMenu.add("Run Mode");
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				switchButton.setSelected(false);
			}
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
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














