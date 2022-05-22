package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.UI.Views.MenuView;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.AppViewFactory;

public class MenuController extends AppController {

	public MenuController(MenuView menuView, AlienAsteroidGame app) {
		super(menuView, app);
	}

	public void helpClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.HelpView));
	}

	public void newGameClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.NewGameView));
	}

	public void creditsClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.CreditsView));
	}
}
