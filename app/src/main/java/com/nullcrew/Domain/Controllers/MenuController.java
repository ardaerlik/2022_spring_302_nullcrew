package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.UI.Views.AppView.AppViewFactory;
import com.nullcrew.UI.Views.AppView.AppViewType;
import com.nullcrew.UI.Views.MenuView.MenuView;

public class MenuController extends AppController {

	/**
	* MenuController controls menu appearances.
	* 
	* @param menuView is a parameter.
	* @param app is a parameter.
	*/
	public MenuController(MenuView menuView, AlienAsteroidGame app) {
		super(menuView, app);
	}

	/**
	* helpClicked controls the AppViewFactory part.
	*/
	public void helpClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.HelpView));
	}

	/**
	* newGameClicked gets values from DataBaseAdapter and FileManager.
	*/
	public void newGameClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.NewGameView));
		getApp().getDatabaseAdapter().loadTheGames();
		getApp().getFileManager().loadTheGames();
	}

	/**
	* creditsClicked controls the changeView and AppView.
	*/
	public void creditsClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.CreditsView));
	}
}
