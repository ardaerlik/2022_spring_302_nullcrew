package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.HelpsController;
import com.nullcrew.UI.Views.HelpView;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.AppViewFactory;

public class HelpsController extends AppController {

	public HelpsController(HelpView helpView, AlienAsteroidGame app) {
		super(helpView, app);
	}

	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
}
