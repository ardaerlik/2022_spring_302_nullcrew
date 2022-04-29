package com.nullcrew.Domain.Models;

import java.util.List;

public class User {
	
	String password;
	
	String email;
	
	List<Game> list_of_games;
	

	public List<Game> getList_of_games() {
		return list_of_games;
	}

	public void setList_of_games(List<Game> list_of_games) {
		this.list_of_games = list_of_games;
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
	

	
	
}
