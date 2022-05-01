package com.nullcrew.Domain.Models;

import java.util.ArrayList;

public final class User {
	private static User instance = new User();
	private String email;
	private String password;
	private ArrayList<Game> savedGames;
	
	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}
		
		return instance;
	}
	
	public User() {
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password, ArrayList<Game> savedGames) {
		this(email, password);
		this.savedGames = savedGames;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Game> getSavedGames() {
		return savedGames;
	}

	public void setSavedGames(ArrayList<Game> savedGames) {
		this.savedGames = savedGames;
	}
		
}
