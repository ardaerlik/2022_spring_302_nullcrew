package com.nullcrew.UI.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends AppView {
    private JPanel loginPanel;
    private JButton register, login;
    private JTextField emailField, passwordField;
    private JLabel title, subtitle, forgetText;
    private final int WIDTH = GameView.WIDTH/2;
    private final int HEIGHT = GameView.HEIGHT/2;

    /**
	 * Create the application.
	 */
    public LoginView() {
    	super(new JFrame());
        initalize();
    }

    /**
	 * Initialize the contents of the frame.
	 */
    private void initalize(){
        frame.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout( loginPanel, BoxLayout.PAGE_AXIS));

        register = new JButton("Register");
        register.setSize(200,40);
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setBackground(new Color(59, 89, 182));
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setFont(new Font("Tahoma", Font.BOLD, 12));
        register.setPreferredSize( new Dimension( 200, 24 ));
        register.setMaximumSize( register.getPreferredSize());
        register.addActionListener(new RegisterActionListener());

        login = new JButton("Login");
        login.setSize(200,40);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBackground(new Color(59, 89, 182));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("Tahoma", Font.BOLD, 12));
        login.setPreferredSize( new Dimension( 200, 24 ));
        login.setMaximumSize( login.getPreferredSize());
        login.addActionListener(new LoginActionListener());

        emailField = new JTextField("Email address");
        emailField.setPreferredSize( new Dimension( 200, 24 ));
        emailField.setMaximumSize( emailField.getPreferredSize());
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JTextField("Password");
        passwordField.setPreferredSize( new Dimension( 200, 24 ));
        passwordField.setMaximumSize( passwordField.getPreferredSize());
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        title = new JLabel("Welcome to the Alien Asteroid Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle = new JLabel("Please login with your credentials");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgetText = new JLabel("Forget my password");
        forgetText.setAlignmentX(Component.CENTER_ALIGNMENT);


        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(subtitle);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(emailField);
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(40));
        loginPanel.add(register);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(login);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(forgetText);
        frame.add(loginPanel);
    }

    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Register");
        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Login");
        }
    }

}
