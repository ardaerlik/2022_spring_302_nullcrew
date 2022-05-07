package com.nullcrew.Domain.Models;

import java.util.ArrayList;

public class Account {
	private String accountId;
	private String email;
	private String password;
	private String forgotKey;
	private ArrayList<Game> savedGames;
	
	public Account() {
	}

	public Account(String accountId) {
		this.accountId = accountId;
		this.email = "";
		this.password = "";
		this.forgotKey = "";
		this.savedGames = new ArrayList<Game>();
	}
	
	public Account(String accountId, String email, String password, String forgotKey) {
		this(accountId);
		this.email = email;
		this.password = password;
		this.forgotKey = forgotKey;
	}
	
	public Account(String accountId, String email, String password, String forgotKey, ArrayList<Game> savedGames) {
		this(accountId, email, password, forgotKey);
		this.savedGames = savedGames;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Game> getSavedGames() {
		return savedGames;
	}

	public void setSavedGames(ArrayList<Game> savedGames) {
		this.savedGames = savedGames;
	}

	public String getForgotKey() {
		return forgotKey;
	}

	public void setForgotKey(String forgotKey) {
		this.forgotKey = forgotKey;
	}
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", email=" + email + ", password=" + password + "]";
	}

}
