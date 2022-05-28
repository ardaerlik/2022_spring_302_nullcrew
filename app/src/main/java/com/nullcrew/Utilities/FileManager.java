package com.nullcrew.Utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.bson.BSONDecoder;
import org.bson.BSONObject;
import org.bson.BasicBSONDecoder;
import org.bson.BasicBSONEncoder;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.common.io.Files;
import com.mongodb.BasicDBObject;
import com.nullcrew.Domain.Models.Constants;
import com.nullcrew.Domain.Models.Constants.DatabaseResponses;
import com.nullcrew.Domain.Models.Constants.FileManagerConstants;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;

public final class FileManager implements DataStrategy {
	private static FileManager instance = new FileManager();
	private User user;
	private SaveLoadObserver saveLoadObserver;
	
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
				user.addNewGameId(newGameId);
				user.getAccount().addGame(game);
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
		user.getAccount().setSavedGames(getGamesWithObjectIds(user.getSavedGameIds()));
		if (user.getAccount().getSavedGames() != null) {
			notifySaveLoadObserver(DatabaseResponses.GAMES_LOADED);
		} else {
			notifySaveLoadObserver(DatabaseResponses.DATABASE_ERROR);
		}
	}
	
	public Game loadTheGame(ObjectId id) {
		Game game;
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		ids.add(id);
		game = getGamesWithObjectIds(ids).get(0);
		return game;
	}

	@Override
	public void registerUser(String email, String password, String forgotKey) {
	}

	@Override
	public void loginUser(String email, String password) {
	}

	@Override
	public void resetPassword(String email, String newPassword, String forgotKey) {
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
	}

	@Override
	public void notifyAuthObservers(String response) {
	}

	@Override
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		this.saveLoadObserver = observer;
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		if (response.equals(FileManagerConstants.GAME_UPDATED)) {
			// TODO
			return;
		}
		
		if (response.equals(FileManagerConstants.GAMES_LOADED)) {
			// TODO
			return;
		}
		
		if (response.equals(FileManagerConstants.NEW_GAME_SAVED)) {
			// TODO
			return;
		}
		
		if (response.equals(FileManagerConstants.WRITE_ERROR)) {
			// TODO
			return;
		}
	}
	
	private void writeToFile(Game game) throws IOException {
		Path path = Paths.get(Constants.FileManagerConstants.DIR_PATH + "/" + game.getGameId().toString() + ".bson");
		File file = path.toFile();
		BasicBSONEncoder encoder = new BasicBSONEncoder();
		BasicDBObject dbObject = new BasicDBObject(game.getDocument());
		new File(Constants.FileManagerConstants.DIR_PATH).mkdirs();
		file.createNewFile();
		Files.write(encoder.encode(dbObject), file);
	}
	
	private BSONObject readFromFile(ObjectId id) throws IOException {
		Path path = Paths.get(Constants.FileManagerConstants.DIR_PATH + "/" + id.toString() + ".bson");
		File file = path.toFile();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		BSONDecoder decoder = new BasicBSONDecoder();
		
		BSONObject object = null;
		while (inputStream.available() > 0) {
	        object = decoder.readObject(inputStream);
	        if(object == null){
	          break;
	        }
	    }
		
		inputStream.close();
		
		return object;
	}
	
	private ArrayList<Game> getGamesWithObjectIds(ArrayList<ObjectId> objectIds) {
		ArrayList<Game> games = new ArrayList<Game>();
		
		for (ObjectId gameId: objectIds) {
			try {
				BSONObject object = readFromFile(gameId);
				games.add(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return games;
	}

}
