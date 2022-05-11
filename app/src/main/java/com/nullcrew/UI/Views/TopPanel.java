package com.nullcrew.UI.Views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.nullcrew.Domain.Models.Ball;
import com.nullcrew.Domain.Models.GameObjectFactory;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.Paddle;

public class TopPanel extends JPanel {

	private static final long serialVersionUID = 1L;
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

	private void createAsteroidNumbersForm() {
		simpleLabel = new JLabel("Simple Asteroids:");
		simpleField = new JTextField(5);
		firmLabel = new JLabel("Firm Asteroids:");
		firmField = new JTextField(5);
		explosiveLabel = new JLabel("Explosive Asteroids:");
		explosiveField = new JTextField(5);
		giftLabel = new JLabel("Gift Asteroids:");
		giftField = new JTextField(5);
	}

	public int[] getNumOfAsteroidTypes() {
		return numOfAsteroidTypes;
	}

	private void createOkButton() {
		okButton = new JButton("Start");
		okButton.setBounds(10, 100, 100, 25);
		okButton.setBackground(Color.green);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().setFocusable(true);
				gameView.getGamePanel().requestFocusInWindow();
				numOfAsteroidTypes = new int[4]; // simple, firm, explosive, gift
				numOfAsteroidTypes[0] = Integer.parseInt(simpleField.getText());
				numOfAsteroidTypes[1] = Integer.parseInt(firmField.getText());
				numOfAsteroidTypes[2] = Integer.parseInt(explosiveField.getText());
				numOfAsteroidTypes[3] = Integer.parseInt(giftField.getText());
				MessageType msg = gameView.getGameController().checkNumAsteroids(numOfAsteroidTypes);
				if (msg == MessageType.MinThresholdErrorTotal) {
					JOptionPane.showMessageDialog(null, "Total min threshold (at least 75) is violated!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (msg == MessageType.MaxThresholdErrorTotal) {
					JOptionPane.showMessageDialog(null, "Total max threshold (at most 165) is violated!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (msg == MessageType.MinThresholdErrorFirm) {
					JOptionPane.showMessageDialog(null, "Firm min threshold (at least 10) is violated!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (msg == MessageType.MinThresholdErrorExplosive) {
					JOptionPane.showMessageDialog(null, "Explosive min threshold (at least 5) is violated!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (msg == MessageType.MinThresholdErrorGift) {
					JOptionPane.showMessageDialog(null, "Gift min threshold (at least 10) is violated!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					okButton.setText("Restart");
					gameView.setNumOfAsteroidTypes(numOfAsteroidTypes);
					gameView.createAsteroids();
					gameView.getGameController().setBall(new Ball(GameObjectFactory.BALL_X, GameObjectFactory.BALL_Y, 17, 17));
					gameView.getGameController().setPaddle(new Paddle(GameObjectFactory.PADDLE_X, GameObjectFactory.PADDLE_Y, 120, 10));
				}
			}
		});
	}

	private void createSwitchButton() {
		switchButton = new JToggleButton("Switch Mode");
		switchButton.setBounds(10, 100, 200, 25);
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
		JMenuItem build_item = new JMenuItem("Build Mode");
		JMenuItem run_item = new JMenuItem("Run Mode");
		build_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().pauseTheGame();
			}
		});
		run_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().resumeTheGame();
			}
		});
		popupMenu.add(build_item);
		popupMenu.add(run_item);
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				switchButton.setSelected(false);
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});
	}

	private void createExitButton() {
		exitButton = new JButton("X");
		exitButton.setBounds(955, 10, 45, 25);
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
		yesButton.setBounds(10, 100, 100, 25);
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
