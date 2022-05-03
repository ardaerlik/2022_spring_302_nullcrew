package com.nullcrew.UI.Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends AppView {
    private JPanel loginPanel, buttonPanel;
    private JButton credentials, newLoadGame, help;
    private JLabel title, credentialsText, newLoadGameText, helpText;

    /**
	 * Create the application.
	 */
    public MenuView() {
    	super(new JFrame());
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


        credentials = new JButton("");
        credentials.setIcon(new ImageIcon("app/assets/credentials.jpg"));
        credentials.setOpaque(true);
        credentials.setFocusPainted(false);
        credentials.setBorderPainted(false);
        credentials.setContentAreaFilled(false);
        credentials.addActionListener(new CredentialsActionListener());

        newLoadGame = new JButton("");
        newLoadGame.setIcon(new ImageIcon("app/assets/credentials.jpg"));
        newLoadGame.setOpaque(true);
        newLoadGame.setFocusPainted(false);
        newLoadGame.setBorderPainted(false);
        newLoadGame.setContentAreaFilled(false);
        newLoadGame.addActionListener(new NewLoadGameActionListener());

        help = new JButton("");
        help.setIcon(new ImageIcon("app/assets/credentials.jpg"));
        help.setOpaque(true);
        help.setFocusPainted(false);
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.addActionListener(new HelpActionListener());

        title = new JLabel("Alien Asteroid Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        credentialsText = new JLabel("Credentials");
        newLoadGameText = new JLabel("New/Load Game");
        helpText = new JLabel("Help Screen");

        loginPanel.add(title);
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
            System.out.println("Credentials");
        }
    }

    private class NewLoadGameActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("New/Load Game");
        }
    }

    private class HelpActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Help Screen");
        }
    }

}
