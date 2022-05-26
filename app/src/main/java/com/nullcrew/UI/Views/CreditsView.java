package com.nullcrew.UI.Views;

import javax.swing.*;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.LoginController;
import com.nullcrew.Domain.Controllers.CreditsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CreditsView extends AppView {
    private JPanel creditsPanel, topPanel, titlePanel, textPanel;
    private JLabel title, creditsText;
    private JButton backButton;
    private CreditsController creditsController;

    /**
     * Create the application.
     */
    public CreditsView() {
        super(new JFrame());
        creditsController = new CreditsController(this, AlienAsteroidGame.getInstance());
        initalize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initalize(){
        creditsPanel = new JPanel();
        creditsPanel.setBackground(Color.BLACK);
        creditsPanel.setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BoxLayout( topPanel, BoxLayout.PAGE_AXIS));
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(new BoxLayout( titlePanel, BoxLayout.LINE_AXIS));
        textPanel = new JPanel();
        textPanel.setBackground(Color.BLACK);
        textPanel.setLayout(new BoxLayout( textPanel, BoxLayout.LINE_AXIS));

        title = new JLabel("Credits Screen");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.LIGHT_GRAY);

        creditsText = new JLabel( "<html>Student Developers: <br> &nbsp;&nbsp; Bu&#287;rahan Yaman <br>" +
                "&nbsp;&nbsp; Can K&#246;z <br>" +
                "&nbsp;&nbsp; Arda Erlik <br>" +
                "&nbsp;&nbsp; Muhammed Ali Kerdi&#287;e <br>" +
                "&nbsp;&nbsp; Atakan &#214;zkan <br><br>" +
                "School: Ko&#231; University <br><br>" +
                "Instructor: <br> &nbsp;&nbsp; G&#246;zde G&#252;l &#350;ahin <br><br>" +
                "TA: <br> &nbsp;&nbsp; Damla &#214;vek <br><br>" +
                "Course: <br> &nbsp;&nbsp; COMP 302 Spring 2022 <br><br>" +
                "MIT License<br>" +
                "<br>" +
                "Copyright (c) 2022 NullCrew<br>" +
                "<br>" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy<br>" +
                "of this software and associated documentation files (the \"Software\"), to deal<br>" +
                "in the Software without restriction, including without limitation the rights<br>" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell<br>" +
                "copies of the Software, and to permit persons to whom the Software is<br>" +
                "furnished to do so, subject to the following conditions:<br>" +
                "<br>" +
                "The above copyright notice and this permission notice shall be included in all<br>" +
                "copies or substantial portions of the Software.<br>" +
                "<br>" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR<br>" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,<br>" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE<br>" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER<br>" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,<br>" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE<br>" +
                "SOFTWARE.<br>" +
                "</html>");
        creditsText.setForeground(Color.LIGHT_GRAY);


        backButton = new JButton("Back");
        backButton.addActionListener( new BackActionListener());
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalStrut(300));
        titlePanel.add(backButton);
        textPanel.add(Box.createHorizontalStrut(700));
        textPanel.add(creditsText);
        topPanel.add(Box.createVerticalStrut(150));
        topPanel.add(titlePanel);
        topPanel.add(textPanel);
        topPanel.add(Box.createVerticalStrut(200));
        creditsPanel.add(topPanel, BorderLayout.CENTER);
        frame.add(creditsPanel);
    }

    private class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            creditsController.backClicked();
        }
    }
}
