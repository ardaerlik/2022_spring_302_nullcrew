package com.nullcrew.UI.Views;

import javax.swing.JFrame;

public class AppView {
	protected JFrame frame;

	public AppView(JFrame frame) {
		this.frame = frame;
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
