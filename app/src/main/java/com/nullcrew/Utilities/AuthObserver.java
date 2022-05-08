package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.User;

public interface AuthObserver {
	public void loginAccepted(User user, Constants.DatabaseResponses response);
	
	public void loginRejected(Constants.DatabaseResponses response);
	
	public void registerAccepted(User user, Constants.DatabaseResponses response);
	
	public void registerRejected(Constants.DatabaseResponses response);
	
	public void forgotPasswordAccepted(User user, Constants.DatabaseResponses response);
	
	public void forgotPasswordRejected(Constants.DatabaseResponses response);
}
