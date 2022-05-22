package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.CreditsController;
import com.nullcrew.UI.Views.CreditsView;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.AppViewFactory;

public class CreditsController extends AppController {

	public CreditsController(CreditsView menuView, AlienAsteroidGame app) {
		super(menuView, app);
	}

	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
}
