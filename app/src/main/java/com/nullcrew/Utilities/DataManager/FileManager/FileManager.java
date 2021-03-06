package com.nullcrew.Utilities.DataManager.FileManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import com.nullcrew.Domain.Models.Constants.FileManagerConstants;
import com.nullcrew.Domain.Models.Game.Game;
import com.nullcrew.Domain.Models.Game.Game.DataType;
import com.nullcrew.Domain.Models.Player.User;
import com.nullcrew.Utilities.DataManager.DBManager.Database;
import com.nullcrew.Utilities.DataManager.DBManager.SaveLoadObserver;

/**
 * FileManager class uses local file system of computer for storing game data.
 * 
 * @author Arda Erlik
 * @version 1.0
 * @since 2022-05-01
 */
public final class FileManager implements Database {
	private static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}

		return instance;
	}

	private SaveLoadObserver saveLoadObserver;

	public FileManager() {
	}

	private Document convertBSONObjectToDocument(BSONObject object) {
		Document document = new Document().append("asteroids", object.get("asteroids"))
				.append("score", object.get("score")).append("lives", object.get("lives"))
				.append("powerups", object.get("powerups")).append("aliens", object.get("aliens"));

		return document;
	}

	private ArrayList<Game> getGamesWithObjectIds(ArrayList<ObjectId> objectIds) {
		ArrayList<Game> games = new ArrayList<Game>();

		for (ObjectId gameId : objectIds) {
			try {
				BSONObject object = readFromFile(gameId);
				Document document = convertBSONObjectToDocument(object);

				Game game = new Game(document);
				game.setGameId(gameId);
				game.setLocation(DataType.FILE);
				games.add(game);

				User.getInstance().addNewGameId(gameId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return games;
	}

	private ArrayList<ObjectId> getObjectIdsFromDir() {
		ArrayList<ObjectId> objectIds = new ArrayList<ObjectId>();
		File folder = new File(Constants.FileManagerConstants.DIR_PATH);
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				try {
					objectIds.add(new ObjectId(files[i].getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return objectIds;
	}

	public Game loadTheGame(ObjectId id) {
		Game game;
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		ids.add(id);
		game = getGamesWithObjectIds(ids).get(0);
		return game;
	}

	@Override
	public void loadTheGames() {
		User.getInstance().getAccount().addListOfGames(getGamesWithObjectIds(getObjectIdsFromDir()));

		if (User.getInstance().getAccount().getSavedGames() != null) {
			notifySaveLoadObserver(FileManagerConstants.GAMES_LOADED);
		} else {
			notifySaveLoadObserver(FileManagerConstants.READ_ERROR);
		}
	}

	@Override
	public void notifySaveLoadObserver(String response) {
		if (response.equals(FileManagerConstants.GAME_UPDATED)) {
			saveLoadObserver.gameSaved(response);
			return;
		}

		if (response.equals(FileManagerConstants.GAMES_LOADED)) {
			saveLoadObserver.allGamesLoaded(User.getInstance().getAccount().getSavedGames(), response);
			return;
		}

		if (response.equals(FileManagerConstants.NEW_GAME_SAVED)) {
			saveLoadObserver.gameSaved(response);
			return;
		}

		if (response.equals(FileManagerConstants.WRITE_ERROR)) {
			saveLoadObserver.gameNotSaved(response);
			return;
		}

		if (response.equals(FileManagerConstants.READ_ERROR)) {
			saveLoadObserver.gameNotLoaded(response);
			return;
		}
	}

	private BSONObject readFromFile(ObjectId id) throws IOException {
		Path path = Paths.get(Constants.FileManagerConstants.DIR_PATH + "/" + id.toString());
		File file = path.toFile();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		BSONDecoder decoder = new BasicBSONDecoder();

		BSONObject object = null;
		while (inputStream.available() > 0) {
			object = decoder.readObject(inputStream);
			if (object == null) {
				break;
			}
		}

		inputStream.close();

		return object;
	}

	@Override
	public void saveTheGame(Game game) {
		if (game.getGameId() == null) {
			ObjectId newGameId;
			boolean gameIdExists = true;

			do {
				gameIdExists = false;
				newGameId = new ObjectId();

				for (ObjectId id : User.getInstance().getSavedGameIds()) {
					if (newGameId.equals(id)) {
						gameIdExists = true;
					}
				}
			} while (gameIdExists);

			try {
				game.setGameId(newGameId);
				writeToFile(game);
				User.getInstance().addNewGameId(newGameId);
				User.getInstance().getAccount().addGame(game);
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
	public void subscribeSaveLoadObserver(SaveLoadObserver observer) {
		this.saveLoadObserver = observer;
	}

	private void writeToFile(Game game) throws IOException {
		Path path = Paths.get(Constants.FileManagerConstants.DIR_PATH + "/" + game.getGameId().toString());
		File file = path.toFile();

		BasicBSONEncoder encoder = new BasicBSONEncoder();
		BasicDBObject dbObject = new BasicDBObject(game.getDocument());

		new File(Constants.FileManagerConstants.DIR_PATH).mkdirs();
		file.createNewFile();
		Files.write(encoder.encode(dbObject), file);
	}

}
