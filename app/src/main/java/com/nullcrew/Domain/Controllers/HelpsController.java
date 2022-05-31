package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.HelpsController;
import com.nullcrew.UI.Views.AppView.AppViewFactory;
import com.nullcrew.UI.Views.AppView.AppViewType;
import com.nullcrew.UI.Views.HelpView.HelpView;

public class HelpsController extends AppController {

	/**
	* HelpsController manages the controllers. It is a constructor.
	* 
	* @param helpView is a parameter.
	* @param app is a parameter.
	*/
	public HelpsController(HelpView helpView, AlienAsteroidGame app) {
		super(helpView, app);
	}

	/**
	* backClicked has functions that help click functions. 
	* It is a constructor.
	*/
	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
}
