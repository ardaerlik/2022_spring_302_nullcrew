package com.nullcrew.UI.Views;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class NewGameView extends AppView {
    private JPanel newGamePanel, buttonPanel;
    private JButton startButton;
    private JButton[] gameButtons;
    private JLabel title;
    private String[] updateLabels, scoreLabels;
    private final int GRID_ROWS = 2;
    private final int GRID_COLUMNS = 5;

    /**
     * Create the application.
     */
    public NewGameView() {
        super(new JFrame());
        initalize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initalize(){
        newGamePanel = new JPanel();
        newGamePanel.setBackground(Color.BLACK);
        newGamePanel.setLayout(new BoxLayout( newGamePanel, BoxLayout.PAGE_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setMaximumSize(new Dimension((int)(GameView.WIDTH*0.5), (int)(GameView.HEIGHT*0.5)));
        GridLayout gridLayout = new GridLayout(GRID_ROWS, GRID_COLUMNS);
        gridLayout.setHgap(15);
        gridLayout.setVgap(15);
        buttonPanel.setLayout(gridLayout);

        title = new JLabel("Create a new game or continue with the existing ones");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.LIGHT_GRAY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start a new game");
        startButton.setSize(200,40);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 12));
        startButton.setPreferredSize( new Dimension( 200, 24 ));
        startButton.setMaximumSize( startButton.getPreferredSize());
        startButton.addActionListener(new StartActionListener());


        updateLabels = new String[GRID_ROWS*GRID_COLUMNS];
        scoreLabels = new String[GRID_ROWS*GRID_COLUMNS];
        for(int i=0; i<updateLabels.length; i++){
            updateLabels[i] = "March 22";            //controller should set labels text here!
            scoreLabels[i] = i+"";                   //controller should set labels text here!
        }


        gameButtons = new JButton[GRID_ROWS*GRID_COLUMNS];
        for(int i=0; i<gameButtons.length; i++){
            gameButtons[i] = new JButton("<html><center>Last updated: "+updateLabels[i]+"<br>"
                                        + "<center>Score: "+scoreLabels[i]+"<br>"
                                        + "<br><br><center><b>Game "+(i+1)+"</b>");
            Font font = gameButtons[i].getFont().deriveFont(Font.PLAIN);
            gameButtons[i].setFont(font);
            gameButtons[i].addActionListener( new GameButtonActionListener());
        }

        newGamePanel.add(Box.createVerticalStrut(30));
        newGamePanel.add(title);
        newGamePanel.add(Box.createVerticalStrut(30));
        newGamePanel.add(startButton);
        newGamePanel.add(Box.createVerticalStrut(40));
        for(JButton b: gameButtons) {
            buttonPanel.add(b);
        }
        newGamePanel.add(buttonPanel);
        frame.add(newGamePanel);
    }

    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Start a new game");
        }
    }

    private class GameButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            for(int i=0; i<gameButtons.length; i++) {
                JButton b = gameButtons[i];
                if (button == b){
                    System.out.println(i+1);
                    break;
                }
            }

        }
    }
}