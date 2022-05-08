package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.User;

public interface AuthObserver {
	public void loginAccepted(User user, String response);
	
	public void loginRejected(String response);
	
	public void registerAccepted(User user, String response);
	
	public void registerRejected(String response);
	
	public void forgotPasswordAccepted(User user, String response);
	
	public void forgotPasswordRejected(String response);
}
