package com.nullcrew.UI.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuView extends JFrame{
    private JPanel loginPanel, buttonPanel;
    private JButton credentials, newLoadGame, help;
    private JLabel title, credentialsText, newLoadGameText, helpText;
    private final int WIDTH = GameView.WIDTH/2;
    private final int HEIGHT = GameView.HEIGHT/2;

    public MenuView() throws IOException {
        createComponents();
    }

    public void createComponents() throws IOException {
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout( loginPanel, BoxLayout.PAGE_AXIS));
        buttonPanel = new JPanel();
        GridLayout layout = new GridLayout(2,3);
        layout.setVgap(10);
        layout.setHgap(10);
        buttonPanel.setLayout(layout);


        credentials = new JButton("");
        credentials.setIcon(new ImageIcon(getClass().getResource("credentials.jpg")));
        credentials.setOpaque(true);
        credentials.setFocusPainted(false);
        credentials.setBorderPainted(false);
        credentials.setContentAreaFilled(false);
        credentials.addActionListener(new CredentialsActionListener());

        newLoadGame = new JButton("");
        newLoadGame.setIcon(new ImageIcon(getClass().getResource("credentials.jpg")));
        newLoadGame.setOpaque(true);
        newLoadGame.setFocusPainted(false);
        newLoadGame.setBorderPainted(false);
        newLoadGame.setContentAreaFilled(false);
        newLoadGame.addActionListener(new NewLoadGameActionListener());

        help = new JButton("");
        help.setIcon(new ImageIcon(getClass().getResource("credentials.jpg")));
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
//        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(newLoadGame);
//        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(help);
        buttonPanel.add(credentialsText);
        buttonPanel.add(newLoadGameText);
        buttonPanel.add(helpText);
        this.add(loginPanel);
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

    public static void main(String[] args) throws IOException {
        new MenuView().setVisible(true);
    }
}
