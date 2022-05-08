package com.nullcrew.Domain.Models;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

public final class User {
	private static User instance = new User();
	private Account account;
	private ArrayList<ObjectId> savedGameIds;
	
	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}
		
		return instance;
	}
	
	public User() {
	}
	
	public User(Account account, ArrayList<ObjectId> savedGameIds) {
		this.account = account;
		this.savedGameIds = savedGameIds;
	}
	
	@SuppressWarnings("unchecked")
	public User(Document document) {
		this.account = new Account(
				(ObjectId) document.get("_id"),
				(String) document.get("email"),
				(String) document.get("password"),
				(String) document.get("forgotKey"));
		this.savedGameIds = (ArrayList<ObjectId>) document.get("savedGameIds");
	}
	
	public void login(Account account, ArrayList<ObjectId> savedGameIds) {
		this.account = account;
		this.savedGameIds = savedGameIds;
	}
	
	public void logout() {
		this.account = null;
		this.savedGameIds = null;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public ArrayList<ObjectId> getSavedGameIds() {
		return savedGameIds;
	}

	public void setSavedGameIds(ArrayList<ObjectId> savedGameIds) {
		this.savedGameIds = savedGameIds;
	}

	@Override
	public String toString() {
		return "User [account=" + account + "]";
	}
	
	public Document getDocument() {
		Document document = account.getDocument()
				.append("savedGameIds", savedGameIds);
		
		return document;
	}
		
}
