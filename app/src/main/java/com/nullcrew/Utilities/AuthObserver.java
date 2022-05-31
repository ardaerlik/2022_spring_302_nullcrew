package com.nullcrew.Utilities;

import com.nullcrew.Domain.Models.User;

/**
 * AuthObserver interface manages the connection between classes implements
 * DataStrategy and controller classes.
 * <p>
 * Gives login, register and forgotPassword signals.
 * 
 * @author Arda Erlik
 * @version 1.0
 * @since 2022-05-01
 */
public interface AuthObserver {
	public void forgotPasswordAccepted(User user, String response);

	public void forgotPasswordRejected(String response);

	public void loginAccepted(User user, String response);

	public void loginRejected(String response);

	public void registerAccepted(User user, String response);

	public void registerRejected(String response);
}
