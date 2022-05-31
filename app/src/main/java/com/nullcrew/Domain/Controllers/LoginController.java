package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.Player.User;
import com.nullcrew.UI.Views.AppView.AppViewFactory;
import com.nullcrew.UI.Views.AppView.AppViewType;
import com.nullcrew.UI.Views.LoginView.LoginView;
import com.nullcrew.Utilities.DataManager.DBManager.AuthObserver;

public class LoginController extends AppController implements AuthObserver {

	/**
	* LoginController controls the login functions.
	* 
	* @param loginView is a parameter.
	* @param app is a parameter.
	*/
	public LoginController(LoginView loginView, AlienAsteroidGame app) {
		super(loginView, app);
		app.getDatabaseAdapter()
		.subscribeAuthObserver(this);
	}
	
	/**
	* loginInfoEntered controls the entering informations.
	* 
	* @param email is a parameter.
	* @param password is a parameter.
	*/
	public void loginInfoEntered(String email, String password) {
		getApp().getDatabaseAdapter()
		.loginUser(email, password);
	}
	
	/**
	* registerInfoEntered controls the registering informations.
	* 
	* @param email is a parameter.
	* @param password is a parameter.
	* @param hint is a parameter.
	*/
	public void registerInfoEntered(String email, String password, String hint) {
		getApp().getDatabaseAdapter()
		.registerUser(email, password, hint);
	}

	@Override
	public void loginAccepted(User user, String response) {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}

	@Override
	public void loginRejected(String response) {
		((LoginView) view).updateUIWithDBResponse(response);
	}

	@Override
	public void registerAccepted(User user, String response) {
		((LoginView) view).updateUIWithDBResponse(response + user.getAccount().getAccountId());
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}

	@Override
	public void registerRejected(String response) {
		((LoginView) view).updateUIWithDBResponse(response);
	}

	@Override
	public void forgotPasswordAccepted(User user, String response) {
		((LoginView) view).updateUIWithDBResponse(response + user.getAccount().getAccountId());
	}

	@Override
	public void forgotPasswordRejected(String response) {
		((LoginView) view).updateUIWithDBResponse(response);
	}

}
