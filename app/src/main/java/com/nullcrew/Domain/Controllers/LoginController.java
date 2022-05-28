package com.nullcrew.Domain.Controllers;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Models.User;
import com.nullcrew.UI.Views.AppViewFactory;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.LoginView;
import com.nullcrew.Utilities.AuthObserver;

public class LoginController extends AppController implements AuthObserver {

	public LoginController(LoginView loginView, AlienAsteroidGame app) {
		super(loginView, app);
//		AlienAsteroidGame.getInstance()
//		.getDataStrategy()
//		.subscribeAuthObserver(this);
	}
	
	public void loginInfoEntered(String email, String password) {
//		AlienAsteroidGame.getInstance()
//		.getDataStrategy()
//		.loginUser(email, password);
	}
	
	public void registerInfoEntered(String email, String password, String hint) {
//		AlienAsteroidGame.getInstance()
//		.getDataStrategy()
//		.registerUser(email, password, hint);
	}

	@Override
	public void loginAccepted(User user, String response) {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}

	@Override
	public void loginRejected(String response) {
		System.out.println(response);
	}

	@Override
	public void registerAccepted(User user, String response) {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}

	@Override
	public void registerRejected(String response) {
		System.out.println(response);
		
	}

	@Override
	public void forgotPasswordAccepted(User user, String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotPasswordRejected(String response) {
		// TODO Auto-generated method stub
		
	}

}
