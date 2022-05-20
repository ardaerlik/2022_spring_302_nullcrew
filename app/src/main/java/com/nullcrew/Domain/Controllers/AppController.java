package com.nullcrew.Domain.Controllers;

import java.awt.geom.Rectangle2D;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.Asteroid;
import com.nullcrew.Domain.Models.GameObjectFactory;
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
