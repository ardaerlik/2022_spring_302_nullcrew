package com.nullcrew.Utilities;

public final class DBManager {
	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public DBManager() {
	}

}
