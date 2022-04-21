package com.nullcrew.Views;

import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;



import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;



public class MenuView {

	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView window = new MenuView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container container = frame.getContentPane();
		container.setLayout(null);
		
		
		frame.getContentPane().setBackground(Color.BLACK);
		JLabel text = new JLabel("ALIEN ASTEROID GAME");
		text.setBounds(340, 200, 1000, 100);
		text.setForeground(Color.BLUE);
		text.setFont(new Font("Calibri", Font.BOLD, 31));

		container.add(text);
		JButton button1 =new JButton("New Game");
		button1.setBounds(384, 384, 200, 40);
		container.add(button1);
		
		JButton button2 =new JButton("Load Game");
		button2.setBounds(384, 450, 200, 40);
		container.add(button2);
		
		JButton button3 =new JButton("Help");
		button3.setBounds(384, 516, 200, 40);
		container.add(button3);
		
		JButton button4 =new JButton("Quit");
		button4.setBounds(384, 582, 200, 40);
		container.add(button4);
		
		frame.setVisible(true);
		
		

		frame.setResizable(false);
	}
}
