package com.nullcrew.UI.Views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import com.nullcrew.Domain.Models.MagnetPowerUp;
import com.nullcrew.Domain.Models.MessageType;
import com.nullcrew.Domain.Models.Paddle;
import com.nullcrew.Domain.Models.TallerPowerUp;

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
	private JButton magnet_button;
	private JButton taller_button;
	private JLabel gangballs_label;
	private JLabel laser_label;
	private JLabel wrap_label;
	private JLabel chance_label;
	private Icon magnet_icon,taller_icon,laser_icon, gang_icon,chance_icon,wrap_icon;
	public TopPanel(GameView gameView) {
		this.gameView = gameView;
		numOfAsteroidTypes = null;
		configureIcons();
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
		createPowerUpView();
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
		magnet_button.setVisible(false);
		taller_button.setVisible(false);
		gangballs_label.setVisible(false);
		laser_label.setVisible(false);
		wrap_label.setVisible(false);
		chance_label.setVisible(false);
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
					gameView.getGameController().restartGame();
				}
			}
		});
	}
	private void createPowerUpView() {
		magnet_button= new JButton(magnet_icon);
		magnet_button.setVisible(true);
		magnet_button.setBounds(10,100, 10, 10);
		magnet_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().setFocusable(true);
				gameView.getGamePanel().requestFocusInWindow();
				for(int a=0;a<gameView.getGameController().getPowerups().size();a++) {
					if(gameView.getGameController().getPowerups().get(a) instanceof MagnetPowerUp) {
						gameView.getGameController().activatePowerUp("MagnetPowerUp");
					}
				}
			
			}
		});
		this.add(magnet_button);
		taller_button= new JButton(taller_icon);
		taller_button.setVisible(true);
		taller_button.setBounds(30,200, 100, 25);
		taller_button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gameView.getGamePanel().setFocusable(true);
				gameView.getGamePanel().requestFocusInWindow();
				for(int a=0;a<gameView.getGameController().getPowerups().size();a++) {
					if(gameView.getGameController().getPowerups().get(a) instanceof TallerPowerUp) {
						gameView.getGameController().activatePowerUp("TallerPowerUp");			
					}
				}
			}
		});
		this.add(taller_button);
		gangballs_label= new JLabel(gang_icon);
		laser_label= new JLabel(laser_icon);
		wrap_label= new JLabel(wrap_icon);
		chance_label= new JLabel(chance_icon);
		add(gangballs_label);
		add(laser_label);
		add(wrap_label);
		add(chance_label);
	}
	private void configureIcons() {
		Path currentRelativePath = Paths.get("");
		String magnet_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"magnet.png";
		String taller_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"taller.png";
		String laser_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"laser.png";
		String gang_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"gangballs.png";
		String chance_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"ExtraLife.png";
		String wrap_path = currentRelativePath.toAbsolutePath().toString()+File.separator+"assets"+File.separator+"wrap.png";
		magnet_icon= new ImageIcon(new ImageIcon(magnet_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		taller_icon= new ImageIcon(new ImageIcon(taller_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		laser_icon= new ImageIcon(new ImageIcon(laser_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		gang_icon= new ImageIcon(new ImageIcon(gang_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		chance_icon= new ImageIcon(new ImageIcon(chance_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		wrap_icon= new ImageIcon(new ImageIcon(wrap_path).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
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

	public JButton getMagnet_button() {
		return magnet_button;
	}

	public void setMagnet_button(JButton magnet_button) {
		this.magnet_button = magnet_button;
	}

	public JButton getTaller_button() {
		return taller_button;
	}

	public void setTaller_button(JButton taller_button) {
		this.taller_button = taller_button;
	}

	public JLabel getGangballs_label() {
		return gangballs_label;
	}

	public void setGangballs_label(JLabel gangballs_label) {
		this.gangballs_label = gangballs_label;
	}

	public JLabel getLaser_label() {
		return laser_label;
	}

	public void setLaser_label(JLabel laser_label) {
		this.laser_label = laser_label;
	}

	public JLabel getWrap_label() {
		return wrap_label;
	}

	public void setWrap_label(JLabel wrap_label) {
		this.wrap_label = wrap_label;
	}

	public JLabel getChance_label() {
		return chance_label;
	}

	public void setChance_label(JLabel chance_label) {
		this.chance_label = chance_label;
	}

	public Icon getMagnet_icon() {
		return magnet_icon;
	}

	public Icon getTaller_icon() {
		return taller_icon;
	}

	public Icon getLaser_icon() {
		return laser_icon;
	}

	public Icon getGang_icon() {
		return gang_icon;
	}

	public Icon getChance_icon() {
		return chance_icon;
	}

	public Icon getWrap_icon() {
		return wrap_icon;
	}

}
