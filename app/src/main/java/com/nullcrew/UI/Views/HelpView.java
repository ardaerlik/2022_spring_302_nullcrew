package com.nullcrew.UI.Views;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;
import com.nullcrew.Domain.Controllers.HelpsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class HelpView extends AppView {
//    private JPanel helpPanel, asteroidPanel;
    private JPanel helpPanel, topPanel, titlePanel, asteroidPanel;
    private JLabel title, simpleText, firmText, giftText, expText;
    private JButton backButton;
    private HelpsController helpsController;

    /**
     * Create the application.
     */
    public HelpView() {
        super(new JFrame());
        helpsController = new HelpsController(this, AlienAsteroidGame.getInstance());
        initalize();
    }

    /**
     * Initialize the contents of the frame.
     */
//    private void initalize(){
//        helpPanel = new JPanel();
//        helpPanel.setBackground(Color.BLACK);
//        helpPanel.setLayout(new BoxLayout( helpPanel, BoxLayout.PAGE_AXIS));
//
//        asteroidPanel = new JPanel();
//        asteroidPanel.setBackground(Color.BLACK);
//        asteroidPanel.setMaximumSize(new Dimension((int)(GameView.WIDTH*0.5), (int)(GameView.HEIGHT*0.5)));
//        asteroidPanel.setLayout( new GridLayout(4,1));
//
//        title = new JLabel("Help Screen");
//        title.setFont(new Font("Arial", Font.BOLD, 16));
//        title.setForeground(Color.LIGHT_GRAY);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        simpleText = new JLabel( "<html><img src=\"file:assets/simple.png\"> &emsp; Simple asteroid: Can be broken in one hit. When broken, an asteroid just disappears.</html>");
//        simpleText.setForeground(Color.LIGHT_GRAY);
//        firmText = new JLabel( "<html><img src=\"file:assets/firm.png\"> &emsp; Firm-asteroid: These asteroids have strong metal inside them. To destroy these asteroids, the player needs to hit more than one, depending on the radius of the asteroid. The radius of the asteroids are decided randomly and every time a player hits the asteroid, it gets smaller and eventually gets destroyed. </html>");
//        firmText.setForeground(Color.LIGHT_GRAY);
//        expText = new JLabel( "<html><img src=\"file:assets/exp.png\"> &emsp; Explosive-asteroids: These asteroids have explosive gas in their components. These asteroids have circular shapes and they explode once being hit. Once exploded, they destroy the neighbor asteroids.</html>");
//        expText.setForeground(Color.LIGHT_GRAY);
//        giftText = new JLabel( "<html><img src=\"file:assets/gift.png\"> &emsp; Gift asteroids: These asteroids can be destroyed in one hit like simple ones. However, they are hiding inside power-ups or triggers for the aliens to start repairing or protecting the wall.</html>");
//        giftText.setForeground(Color.LIGHT_GRAY);
//
//        backButton = new JButton("Back");
//        backButton.addActionListener( new BackActionListener());
//
//        asteroidPanel.add(simpleText);
//        asteroidPanel.add(firmText);
//        asteroidPanel.add(expText);
//        asteroidPanel.add(giftText);
//
//        helpPanel.add(Box.createVerticalStrut(30));
//        helpPanel.add(title);
//        helpPanel.add(Box.createVerticalStrut(30));
//        helpPanel.add(asteroidPanel);
//        helpPanel.add(Box.createVerticalStrut(40));
//        frame.add(helpPanel);
//        helpPanel.add(Box.createVerticalStrut(30));
//        helpPanel.add(backButton);
//    }
    private void initalize(){
        helpPanel = new JPanel();
        helpPanel.setBackground(Color.BLACK);
        helpPanel.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BoxLayout( topPanel, BoxLayout.PAGE_AXIS));
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout( titlePanel, BoxLayout.LINE_AXIS));
        asteroidPanel = new JPanel();
        asteroidPanel.setBackground(Color.BLACK);
        asteroidPanel.setMaximumSize(new Dimension((int)(GameView.WIDTH*0.5), (int)(GameView.HEIGHT*0.5)));
        asteroidPanel.setLayout( new GridLayout(4,1));

        title = new JLabel("Help Screen");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.LIGHT_GRAY);

        simpleText = new JLabel( "<html><img src=\"file:assets/simple.png\"> &emsp; Simple asteroid: Can be broken in one hit. When broken, an asteroid just disappears.</html>");
        simpleText.setForeground(Color.LIGHT_GRAY);
        firmText = new JLabel( "<html><img src=\"file:assets/firm.png\"> &emsp; Firm-asteroid: These asteroids have strong metal inside them. To destroy these asteroids, the player needs to hit more than one, depending on the radius of the asteroid. The radius of the asteroids are decided randomly and every time a player hits the asteroid, it gets smaller and eventually gets destroyed. </html>");
        firmText.setForeground(Color.LIGHT_GRAY);
        expText = new JLabel( "<html><img src=\"file:assets/exp.png\"> &emsp; Explosive-asteroids: These asteroids have explosive gas in their components. These asteroids have circular shapes and they explode once being hit. Once exploded, they destroy the neighbor asteroids.</html>");
        expText.setForeground(Color.LIGHT_GRAY);
        giftText = new JLabel( "<html><img src=\"file:assets/gift.png\"> &emsp; Gift asteroids: These asteroids can be destroyed in one hit like simple ones. However, they are hiding inside power-ups or triggers for the aliens to start repairing or protecting the wall.</html>");
        giftText.setForeground(Color.LIGHT_GRAY);



        backButton = new JButton("Back");
        backButton.addActionListener( new BackActionListener());
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(300));
        titlePanel.add(backButton);
//        asteroidPanel.add(Box.createHorizontalStrut(700));
        asteroidPanel.add(simpleText);
        asteroidPanel.add(firmText);
        asteroidPanel.add(expText);
        asteroidPanel.add(giftText);
        topPanel.add(Box.createVerticalStrut(200));
        topPanel.add(titlePanel);
        topPanel.add(asteroidPanel);
        topPanel.add(Box.createVerticalStrut(500));
        helpPanel.add(topPanel, BorderLayout.CENTER);
        frame.add(helpPanel);
    }

    private class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            helpsController.backClicked();
        }
    }
}
