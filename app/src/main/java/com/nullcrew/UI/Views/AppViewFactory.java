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
			return new LoginView();
		case MenuView:
			return new MenuView();
		case HelpView:
			return new HelpView();
		case NewGameView:
			return new NewGameView();
		case CreditsView:
			return new CreditsView();
		default:
			throw new IllegalArgumentException("Illegal AppView type");
		}
	}

}
