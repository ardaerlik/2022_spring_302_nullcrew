package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.UI.Views.AppView;

public class AppController {
	private AlienAsteroidGame app;
	protected AppView view;

	public AppController(AppView view, AlienAsteroidGame app) {
		this.view = view;
		this.app = app;
	}
	
	protected void changeView(AppView newView) {
		app.changeView(view, newView);
	}

	public AlienAsteroidGame getApp() {
		return app;
	}

	public void setApp(AlienAsteroidGame app) {
		this.app = app;
	}

	public AppView getView() {
		return view;
	}

	public void setView(AppView view) {
		this.view = view;
	}

}
