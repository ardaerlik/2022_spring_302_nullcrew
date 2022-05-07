package com.nullcrew.Utilities;

public final class FileManager {
	private static FileManager instance = new FileManager();
	
	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		
		return instance;
	}

	public FileManager() {
	}

}
