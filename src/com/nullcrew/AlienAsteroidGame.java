package com.nullcrew;

public final class AlienAsteroidGame {
	private static AlienAsteroidGame instance = new AlienAsteroidGame();
	
	private AlienAsteroidGame() {}

	public static void main(String[] args) {}
	
	public static AlienAsteroidGame getInstance() {
		if (instance == null) {
			instance = new AlienAsteroidGame();
		}
		
		return instance;
	}
	
//	private void changeScreen(JFrame from, JFrame to) {
//		if (from == null) {
//			to.setBounds(0, 0, 1000, 1000);
//			to.setVisible(true);
//			
//		} else if (to == null) {
//			// exit application
//		} else {
//			
//		}
//		from.dispose();
//	}
	
}
