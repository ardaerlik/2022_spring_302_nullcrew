package com.nullcrew.UI.Views.HelpView;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;
import com.nullcrew.UI.Views.AppView.AppView;
import com.nullcrew.UI.Views.GameView.GameView;
import com.nullcrew.Domain.Controllers.HelpsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class HelpView extends AppView {
//    private JPanel helpPanel, asteroidPanel;
    private JPanel helpPanel, topPanel, titlePanel, asteroidPanel;
    private JLabel title, simpleText, firmText, giftText, expText, gangText, extraText, laserText,
            magnetText, tallerText, wrapText, repairAlienText, timeAlienText, protectAlienText, coopAlienText;
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
//        topPanel.setLayout(new BoxLayout( topPanel, BoxLayout.PAGE_AXIS));
        topPanel.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout( titlePanel, BoxLayout.LINE_AXIS));
        asteroidPanel = new JPanel();
        asteroidPanel.setBackground(Color.BLACK);
        asteroidPanel.setMaximumSize(new Dimension((int)(GameView.WIDTH*0.75), (int)(GameView.HEIGHT*0.5)));
//        asteroidPanel.setLayout( new GridLayout(4,1));
        asteroidPanel.setLayout( new BoxLayout( asteroidPanel, BoxLayout.PAGE_AXIS));

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
        gangText = new JLabel( "<html><img src=\"file:assets/gangballs.png\" width=\"50\" height=\"50\"> &emsp; Gang of Balls: Once a gift asteroid having gang-of-balls is hit, the hitting ball will turn into 10 replicas.\n</html>");
        gangText.setForeground(Color.LIGHT_GRAY);
        extraText = new JLabel( "<html><img src=\"file:assets/ExtraLife.png\" width=\"50\" height=\"50\"> &emsp; Extra Chance: This power-up increases the player’s chances(lives) by 1.\n</html>");
        extraText.setForeground(Color.LIGHT_GRAY);
        laserText = new JLabel( "<html><img src=\"file:assets/laser.png\" width=\"50\" height=\"50\"> &emsp; Laser gun:  Attached to both ends of the paddle, it can destroy a full column of blocks, even the firm asteroids.\n</html>");
        laserText.setForeground(Color.LIGHT_GRAY);
        magnetText = new JLabel( "<html><img src=\"file:assets/magnet.png\" width=\"50\" height=\"50\"> &emsp; Magnet: This feature allows the paddle to catch the ball instead of reflecting it, and then throwing it using the mouse or W, this can help to direct the ball to hit more beneficial positions of the wall.\n</html>");
        magnetText.setForeground(Color.LIGHT_GRAY);
        tallerText = new JLabel( "<html><img src=\"file:assets/taller.png\" width=\"50\" height=\"50\"> &emsp; Taller: This doubles the length of the paddle, the effect of this power-up stays for 30 seconds. It might not be used directly, and it can be stored for the future.\n</html>");
        tallerText.setForeground(Color.LIGHT_GRAY);
        wrapText = new JLabel( "<html><img src=\"file:assets/wrap.png\" width=\"50\" height=\"50\"> &emsp; Wrap: This power-up allows player’s paddle to go through the side of the screen and re-appear on the opposite side.\n</html>");
        wrapText.setForeground(Color.LIGHT_GRAY);
        repairAlienText = new JLabel( "<html><img src=\"file:assets/repairAlien.jpg\" width=\"50\" height=\"50\"> &emsp; Repairing Alien\n</html>");
        repairAlienText.setForeground(Color.LIGHT_GRAY);
        timeAlienText = new JLabel( "<html><img src=\"file:assets/timeAlien.jpg\" width=\"50\" height=\"50\"> &emsp; Time Wasting Alien\n</html>");
        timeAlienText.setForeground(Color.LIGHT_GRAY);
        protectAlienText = new JLabel( "<html><img src=\"file:assets/protectAlien.jpg\" width=\"50\" height=\"50\"> &emsp; Protecting Alien\n</html>");
        protectAlienText.setForeground(Color.LIGHT_GRAY);
        coopAlienText = new JLabel( "<html><img src=\"file:assets/coopAlien.jpg\" width=\"50\" height=\"50\"> &emsp; Cooperative Alien\n</html>");
        coopAlienText.setForeground(Color.LIGHT_GRAY);

        backButton = new JButton("Back");
        backButton.addActionListener( new BackActionListener());
        titlePanel.add(Box.createVerticalStrut(200));
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(400));
        titlePanel.add(backButton);
        titlePanel.add(Box.createHorizontalStrut(700));
        asteroidPanel.add(simpleText);
        asteroidPanel.add(firmText);
        asteroidPanel.add(expText);
        asteroidPanel.add(giftText);
        asteroidPanel.add(gangText);
        asteroidPanel.add(extraText);
        asteroidPanel.add(laserText);
        asteroidPanel.add(magnetText);
        asteroidPanel.add(tallerText);
        asteroidPanel.add(wrapText);
        asteroidPanel.add(repairAlienText);
        asteroidPanel.add(timeAlienText);
        asteroidPanel.add(protectAlienText);
        asteroidPanel.add(coopAlienText);
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(Box.createHorizontalStrut(400), BorderLayout.WEST);
        topPanel.add(asteroidPanel, BorderLayout.CENTER);
        topPanel.add(Box.createHorizontalStrut(400), BorderLayout.EAST);
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
