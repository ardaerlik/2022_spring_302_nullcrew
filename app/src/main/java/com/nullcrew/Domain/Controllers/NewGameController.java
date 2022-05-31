package com.nullcrew.Domain.Controllers;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.NewGameController;
import com.nullcrew.Domain.Models.Constants.DatabaseResponses;
import com.nullcrew.Domain.Models.Game.Game;
import com.nullcrew.Domain.Models.Player.User;
import com.nullcrew.Utilities.DataManager.DBManager.SaveLoadObserver;
import com.nullcrew.UI.Views.AppView.AppViewFactory;
import com.nullcrew.UI.Views.AppView.AppViewType;
import com.nullcrew.UI.Views.GameView.GameView;
import com.nullcrew.UI.Views.GameView.NewGameView;

public class NewGameController extends AppController implements SaveLoadObserver {

	
	/**
	* NewGameController controls the DataBaseAdapter, SaveLoadObserver
	* and FileManager parts.
	* 
	* @param newGameView is a parameter that affects game view.
	* @param app is a parameter.
	*/
	public NewGameController(NewGameView newGameView, AlienAsteroidGame app) {
		super(newGameView, app);
		app.getDatabaseAdapter()
		.subscribeSaveLoadObserver(this);
		app.getFileManager()
		.subscribeSaveLoadObserver(this);
	}

	/**
	* backClicked controls the AppView part.
	* Such as AppView Factory and Type.
	*/
	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
	
	/**
	* newGameStarted controls the changeView part.
	* Such as AppView Factory and Type.
	*/
	public void newGameStarted() {
		Game.setCurrentGame(new Game());
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.GameView));
	}
	
	/**
	* existingGameStarted gets and makes some special movements 
	* with instance, account and saved games values.
	* 
	* @param id is a special appearance for the player.
	*/
	public void existingGameStarted(ObjectId id) {
		for (Game game: User.getInstance().getAccount().getSavedGames()) {
			if (game.getGameId().equals(id)) {
				Game.setCurrentGame(game);
				System.out.println("Current game loaded");
				System.out.println(Game.getCurrentGame());
				break;
			}
		}

		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.GameView));
	}

	@Override
	public void allGamesLoaded(ArrayList<Game> games, String response) {
		((NewGameView) view).updateUIWithGames();
	}

	@Override
	public void gameSaved(String response) {
	}

	@Override
	public void gameNotSaved(String response) {
	}

	@Override
	public void gameNotLoaded(String response) {
		System.err.println(response);
	}
	
}
