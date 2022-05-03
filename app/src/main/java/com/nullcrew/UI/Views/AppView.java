package com.nullcrew.UI.Views;

import javax.swing.JFrame;

import com.nullcrew.Domain.Models.Constants;

public class AppView {
	protected JFrame frame;

	public AppView(JFrame frame) {
		this.frame = frame;
		initialize();
	}
	
	private void initialize() {
        frame.setBounds(0, 0, Constants.UIConstants.SCREEN_WIDTH, Constants.UIConstants.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void startView() {
		frame.setVisible(true);
	}
	
	public void endView() {
		frame.setVisible(false);
		frame.dispose();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
