package com.nullcrew.UI.Views;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LoginView extends AppView {
    private JPanel loginPanel;
    private JButton register, login;
    private JTextField emailField, passwordField, forgotKeyField;
    private JLabel title, subtitle, forgetText;
    private LoginController loginController;

    /**
	 * Create the application.
	 */
    public LoginView() {
    	super(new JFrame());
    	loginController = new LoginController(this, AlienAsteroidGame.getInstance());
        initalize();
    }

    /**
	 * Initialize the contents of the frame.
	 */
    private void initalize(){
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout( loginPanel, BoxLayout.PAGE_AXIS));

        register = new JButton("Register");
        register.setSize(200,40);
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.setBackground(Color.BLUE);
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setFont(new Font("Arial", Font.BOLD, 12));
        register.setPreferredSize( new Dimension( 200, 24 ));
        register.setMaximumSize( register.getPreferredSize());
        register.addActionListener(new RegisterActionListener());

        login = new JButton("Login");
        login.setSize(200,40);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("Arial", Font.BOLD, 12));
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
        
        forgotKeyField = new JTextField("Password recovery hint");
        forgotKeyField.setPreferredSize( new Dimension( 200, 24 ));
        forgotKeyField.setMaximumSize( emailField.getPreferredSize());
        forgotKeyField.setAlignmentX(Component.CENTER_ALIGNMENT);

        title = new JLabel("Welcome to the Alien Asteroid Game");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle = new JLabel("Please login with your credentials");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgetText = new JLabel("Forget my password");
        forgetText.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgetText.addMouseListener( new ForgetActionListener());

        loginPanel.add(Box.createVerticalStrut(GameView.HEIGHT/3));
        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(subtitle);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(emailField);
        loginPanel.add(passwordField);
        loginPanel.add(forgotKeyField);
        forgotKeyField.setVisible(false);
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
        	forgotKeyField.setVisible(true);
        	subtitle.setText("Please register with your credentials");
        	loginController.registerInfoEntered(emailField.getText(), passwordField.getText(), forgotKeyField.getText());
        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        	forgotKeyField.setVisible(false);
        	subtitle.setText("Please login with your credentials");
        	loginController.loginInfoEntered(emailField.getText(), passwordField.getText());
        }
    }

    private class ForgetActionListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            System.out.println("Forget");
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}
    }
}
