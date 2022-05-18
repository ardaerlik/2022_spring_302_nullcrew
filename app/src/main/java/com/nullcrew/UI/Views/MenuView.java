package com.nullcrew.UI.Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nullcrew.Domain.Controllers.MenuController;
import com.nullcrew.AlienAsteroidGame;

public class MenuView extends AppView {
    private JPanel loginPanel, midPanel, buttonPanel;
    private JButton credentials, newLoadGame, help;
    private JLabel title, credentialsText, newLoadGameText, helpText;
    private MenuController menuController;

    /**
	 * Create the application.
	 */
    public MenuView() {
    	super(new JFrame());
        menuController = new MenuController(this, AlienAsteroidGame.getInstance());
        initialize();
    }

    /**
	 * Initialize the contents of the frame.
	 */
    public void initialize() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout( loginPanel, BoxLayout.PAGE_AXIS));
        buttonPanel = new JPanel();
        GridLayout layout = new GridLayout(2,3);
        layout.setVgap(10);
        layout.setHgap(10);
        buttonPanel.setLayout(layout);
        buttonPanel.setMaximumSize(new Dimension((int)(GameView.WIDTH*0.5), (int)(GameView.HEIGHT*0.5)));
        midPanel = new JPanel();

        credentials = new JButton("");
        credentials.setIcon(new ImageIcon(((new ImageIcon("assets/info.png")).getImage()).getScaledInstance(130, 70, java.awt.Image.SCALE_SMOOTH)));
        credentials.setOpaque(true);
        credentials.setFocusPainted(false);
        credentials.setBorderPainted(false);
        credentials.setContentAreaFilled(false);
        credentials.addActionListener(new CredentialsActionListener());

        newLoadGame = new JButton("");
        newLoadGame.setIcon(new ImageIcon(((new ImageIcon("assets/new_game.png")).getImage()).getScaledInstance(200, 70, java.awt.Image.SCALE_SMOOTH)));
        newLoadGame.setOpaque(true);
        newLoadGame.setFocusPainted(false);
        newLoadGame.setBorderPainted(false);
        newLoadGame.setContentAreaFilled(false);
        newLoadGame.addActionListener(new NewLoadGameActionListener());

        help = new JButton("");
        help.setIcon(new ImageIcon(((new ImageIcon("assets/help.png")).getImage()).getScaledInstance(110, 70, java.awt.Image.SCALE_SMOOTH)));
        help.setOpaque(true);
        help.setFocusPainted(false);
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.addActionListener(new HelpActionListener());

        title = new JLabel("Alien Asteroid Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        credentialsText = new JLabel("",  SwingConstants.CENTER);
        newLoadGameText = new JLabel("",  SwingConstants.CENTER);
        helpText = new JLabel("",  SwingConstants.CENTER);

        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(120));
        loginPanel.add(midPanel);
        loginPanel.add(Box.createVerticalStrut(120));
        loginPanel.add(buttonPanel);
        buttonPanel.add(credentials);
        buttonPanel.add(newLoadGame);
        buttonPanel.add(help);
        buttonPanel.add(credentialsText);
        buttonPanel.add(newLoadGameText);
        buttonPanel.add(helpText);
        frame.add(loginPanel);
    }

    private class CredentialsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuController.creditsClicked();
        }
    }

    private class NewLoadGameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuController.newGameClicked();
        }
    }

    private class HelpActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            menuController.helpClicked();
        }
    }

}
