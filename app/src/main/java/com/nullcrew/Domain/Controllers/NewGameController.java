package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.NewGameController;
import com.nullcrew.UI.Views.NewGameView;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.AppViewFactory;

public class NewGameController extends AppController {

	public NewGameController(NewGameView newGameView, AlienAsteroidGame app) {
		super(newGameView, app);
	}

	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
}
