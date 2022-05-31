package com.nullcrew.UI.Views.LoginView;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;
import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.UI.Views.AppView.AppView;
import com.nullcrew.UI.Views.GameView.GameView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LoginView extends AppView {
    private JPanel loginPanel;
    private JButton authButton, switchButton;
    private JTextField emailField, passwordField, forgotKeyField;
    private JLabel title, subtitle, forgetText, infoText;
    private LoginController loginController;
    private AuthMode authMode;
    
    public enum AuthMode {
    	LOGIN, REGISTER
    }

    /**
	 * Create the application.
	 */
    public LoginView() {
    	super(new JFrame());
    	loginController = new LoginController(this, AlienAsteroidGame.getInstance());
    	authMode = AuthMode.LOGIN;
        initalize();
        createActionListeners();
    }

    /**
	 * Initialize the contents of the frame.
	 */
    private void initalize(){
        loginPanel = new JPanel() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		ImageIcon imageIcon = new ImageIcon("assets/login_background.jpg");
        		Image image = imageIcon.getImage().getScaledInstance(Constants.UIConstants.SCREEN_WIDTH, 
        				Constants.UIConstants.SCREEN_HEIGHT,
        				Image.SCALE_SMOOTH);
        	    g.drawImage(image, 0, 0, null);
        	}
        };
        loginPanel.setLayout(new BoxLayout( loginPanel, BoxLayout.PAGE_AXIS));

        authButton = new JButton("Login");
        authButton.setSize(200,40);
        authButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        authButton.setBackground(Color.BLUE);
        authButton.setForeground(Color.WHITE);
        authButton.setFocusPainted(false);
        authButton.setFont(new Font("Arial", Font.BOLD, 12));
        authButton.setPreferredSize( new Dimension( 200, 24 ));
        authButton.setMaximumSize( authButton.getPreferredSize());

        switchButton = new JButton("Switch to Register");
        switchButton.setSize(200,40);
        switchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchButton.setBackground(Color.BLUE);
        switchButton.setForeground(Color.WHITE);
        switchButton.setFocusPainted(false);
        switchButton.setFont(new Font("Arial", Font.BOLD, 12));
        switchButton.setPreferredSize( new Dimension( 200, 24 ));
        switchButton.setMaximumSize( switchButton.getPreferredSize());

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
        infoText = new JLabel("");
        infoText.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginPanel.add(Box.createVerticalStrut(GameView.HEIGHT/3));
        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(subtitle);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(emailField);
        loginPanel.add(passwordField);
        loginPanel.add(forgotKeyField);
        loginPanel.add(Box.createVerticalStrut(40));
        loginPanel.add(authButton);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(switchButton);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(forgetText);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(infoText);
        forgotKeyField.setVisible(false);
        frame.add(loginPanel);
    }
    
    private void createActionListeners() {
    	authButton.addActionListener(new AuthActionListener());
    	switchButton.addActionListener(new SwitchActionListener());
    	forgetText.addMouseListener( new ForgetActionListener());
    }
    
    public void updateUIWithDBResponse(String response) {
    	infoText.setText(response);
    }

    private class AuthActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        	switch (authMode) {
        	case LOGIN:
        		loginController.loginInfoEntered(emailField.getText(), passwordField.getText());
        		break;
        	case REGISTER:
        		loginController.registerInfoEntered(emailField.getText(), passwordField.getText(), forgotKeyField.getText());
        		break;
        	}
        }
    }

    private class SwitchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        	switch (authMode) {
        	case LOGIN:
        		forgotKeyField.setVisible(true);
        		authButton.setText("Register");
        		switchButton.setText("Switch to Login");
        		subtitle.setText("Please register with your credentials");
        		authMode = AuthMode.REGISTER;
        		break;
        	case REGISTER:
        		forgotKeyField.setVisible(false);
        		authButton.setText("Login");
        		switchButton.setText("Switch to Register");
        		subtitle.setText("Please login with your credentials");
        		authMode = AuthMode.LOGIN;
        		break;
        	}
        }
    }

    private class ForgetActionListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            System.out.println("Forget the password button is clicked");
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
