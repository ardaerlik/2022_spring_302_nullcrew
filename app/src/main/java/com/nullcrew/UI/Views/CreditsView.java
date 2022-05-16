package com.nullcrew.UI.Views;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CreditsView extends AppView {
    private JPanel creditsPanel;
    private JLabel title, creditsText;

    /**
     * Create the application.
     */
    public CreditsView() {
        super(new JFrame());
        initalize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initalize(){
        creditsPanel = new JPanel();
        creditsPanel.setBackground(Color.BLACK);
        creditsPanel.setLayout(new BoxLayout( creditsPanel, BoxLayout.PAGE_AXIS));

        title = new JLabel("Credits Screen");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.LIGHT_GRAY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        creditsText = new JLabel( "<html>Bu&#287;rahan Yaman <br>" +
                "Can K&#246;z <br>" +
                "Arda Erlik <br>" +
                "Muhammed Ali Kerdi&#287;e <br>" +
                "Atakan &#214;zkan <br>" +
                "School Ko&#231; University <br>" +
                "Instructor G&#246;zde G&#252;l &#350;ahin <br>" +
                "TA Damla &#214;vek <br>" +
                "Course COMP 302 Spring 2022</html>");
        creditsText.setForeground(Color.LIGHT_GRAY);

        creditsPanel.add(Box.createVerticalStrut(30));
        creditsPanel.add(title);
        creditsPanel.add(Box.createVerticalStrut(30));
        creditsPanel.add(creditsText);
        frame.add(creditsPanel);
    }

}
