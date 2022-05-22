package com.nullcrew.Domain.Models;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * OVERVIEW: Account is a class which contains the prior scores of a user along with their e mail, accountId.
 * <p>
 * The abstraction function is
 * AF(x) = {account.totalScore < Integer.MAX_VALUE && account.totalScore >= 0 && account.savedGames.size() <= 8 && 
 * account.savedGames.size() >= 0}
 * <p>
 * The rep invariant is
 * account.totalScore >= 0 && account.savedGames != null && Integer.MAX_VALUE > account.totalScore && account.savedGames.size() <= 8 && account.savedGames.size() >= 0, 
 * account.totalScore should be int.
 */
public class Account {
	private ObjectId accountId;
	private String email;
	private String password;
	private String forgotKey;
	private ArrayList<Game> savedGames;
	private int totalScore;
	
	public Account() {
	}

	public Account(ObjectId accountId) {
		this.accountId = accountId;
		this.email = "";
		this.password = "";
		this.forgotKey = "";
		this.savedGames = new ArrayList<Game>();
	}
	
	public Account(ObjectId accountId, String email, String password, String forgotKey) {
		this(accountId);
		this.email = email;
		this.password = password;
		this.forgotKey = forgotKey;
	}
	
	public Account(ObjectId accountId, String email, String password, String forgotKey, ArrayList<Game> savedGames) {
		this(accountId, email, password, forgotKey);
		this.savedGames = savedGames;
	}

	public void calculateTotalScore() {
		if (totalScore < 0) {
			throw new IllegalArgumentException();
		}
		
		if (savedGames == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < savedGames.size(); i++) {
			if (totalScore < Integer.MAX_VALUE) {
				totalScore += savedGames.get(i).getScore();
			} else {
				throw new ArithmeticException();
			}
		}
	}
	
	public void addGame(Game game) {
		if (game == null) {
			throw new NullPointerException();
		}
		
		if (savedGames.size() < 8) {
			savedGames.add(game);
		}
	}
	
	public boolean repOk() {
        if (totalScore >= 0 && totalScore < Integer.MAX_VALUE 
        		&& savedGames.size() >= 0 && savedGames.size() <= 8) {
        	return true;
        } else {
        	return false;
        }
    }
	
	public ObjectId getAccountId() {
		return accountId;
	}

	public void setAccountId(ObjectId accountId) {
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
	
	public Document getDocument() {
		Document document = new Document()
				.append("_id", accountId)
				.append("email", email)
				.append("password", password)
				.append("forgotkey", forgotKey);
		
		return document;
	}

}
