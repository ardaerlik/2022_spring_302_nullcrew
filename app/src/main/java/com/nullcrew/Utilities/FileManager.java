package com.nullcrew.Utilities;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bson.BasicBSONEncoder;
import org.bson.types.ObjectId;

import com.google.common.io.Files;
import com.mongodb.BasicDBObject;
import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.Constants.FileManagerConstants;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public final class FileManager implements DataStrategy {
	private static FileManager instance = new FileManager();
	private User user;
	
	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		
		return instance;
	}

	public FileManager() {
	}

	@Override
	public void saveTheGame(Game game) {
		if (game.getGameId() == null) {
			ObjectId newGameId;
			boolean gameIdExists = true;
			
			do {
				gameIdExists = false;
				newGameId = new ObjectId();
				
				for (ObjectId id: user.getSavedGameIds()) {
					if (newGameId.equals(id)) {
						gameIdExists = true;
					}
				}
			} while (gameIdExists);

			try {
				game.setGameId(newGameId);
				writeToFile(game);
				user.getSavedGameIds().add(newGameId);
				user.getAccount().getSavedGames().add(game);
				notifySaveLoadObserver(FileManagerConstants.NEW_GAME_SAVED);
			} catch (IOException e) {
				e.printStackTrace();
				notifySaveLoadObserver(FileManagerConstants.WRITE_ERROR);
			}
		} else {
			try {
				writeToFile(game);
				notifySaveLoadObserver(FileManagerConstants.GAME_UPDATED);
			} catch (IOException e) {
				e.printStackTrace();
				notifySaveLoadObserver(FileManagerConstants.WRITE_ERROR);
			}
		}
	}

	@Override
	public void loadTheGames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUser(String email, String password, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void subscribeAuthObserver(AuthObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyAuthObservers(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		// TODO Auto-generated method stub
		
	}
	
	private void writeToFile(Game game) throws IOException {
		Path path = Paths.get(Constants.FileManagerConstants.DIR_PATH + "/" + game.getGameId().toString());
		BasicBSONEncoder encoder = new BasicBSONEncoder();
		BasicDBObject dbObject = new BasicDBObject(game.getDocument());
		Files.write(encoder.encode(dbObject), path.toFile());
	}

}
