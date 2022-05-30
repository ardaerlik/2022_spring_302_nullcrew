package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.UI.Views.AppView;

public class AppController {
	private AlienAsteroidGame app;
	protected AppView view;

	
	/**
	* AppController helps the application logic.
	* 
	* @param view is about the appearance.
	* @param app is about the application logic.
	*/
	public AppController(AppView view, AlienAsteroidGame app) {
		this.view = view;
		this.app = app;
	}
	
	/**
	* changeView helps the application characteristics.
	* 
	* @param newView is about the appearance.
	*/
	protected void changeView(AppView newView) {
		app.changeView(view, newView);
	}

	/**
	* getApp from the AlienAsteroidGame.
	* 
	* @return app is about the application idea.
	*/
	public AlienAsteroidGame getApp() {
		return app;
	}

	/**
	* setApp from the AlienAsteroidGame.
	* 
	* @param app is the parameter for setApp.
	*/
	public void setApp(AlienAsteroidGame app) {
		this.app = app;
	}

	/**
	* getView from the AlienAsteroidGame.
	* 
	* @return view is about the appearance.
	*/
	public AppView getView() {
		return view;
	}

	/**
	* setView from the AlienAsteroidGame.
	* 
	* @param view is the parameter for AppView.
	*/
	public void setView(AppView view) {
		this.view = view;
	}

}
