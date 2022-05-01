package com.nullcrew.UI.Views;

public final class AppViewFactory {
	private static AppViewFactory instance = new AppViewFactory();

	public static AppViewFactory getInstance() {
		if (instance == null) {
			instance = new AppViewFactory();
		}
		
		return instance;
 	}
	
	public AppViewFactory() {
	}
	
	public AppView createAppView(AppViewType type) {
		switch (type) {
		case GameView:
			return new GameView();
		case LoginView:
			return new GameView();
		case MenuView:
			return new GameView();
		default:
			throw new IllegalArgumentException("Illegal AppView type");
		}
	}

}