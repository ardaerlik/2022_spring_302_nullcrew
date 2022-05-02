package com.nullcrew.Domain.Models;

public final class User {
	private static User instance = new User();
	private Account account;
	
	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}
		
		return instance;
	}
	
	public User() {
	}
	
	public User(Account account) {
		this.account = account;
	}
	
	public void login(Account account) {
		this.account = account;
	}
	
	public void logout() {
		this.account = null;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [account=" + account + "]";
	}
		
}
