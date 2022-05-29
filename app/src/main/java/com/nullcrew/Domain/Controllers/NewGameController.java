package com.nullcrew.Domain.Controllers;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.NewGameController;
import com.nullcrew.Domain.Models.Constants.DatabaseResponses;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.UI.Views.NewGameView;
import com.nullcrew.Utilities.SaveLoadObserver;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.AppViewFactory;

public class NewGameController extends AppController implements SaveLoadObserver {

	public NewGameController(NewGameView newGameView, AlienAsteroidGame app) {
		super(newGameView, app);
		app.getDatabaseAdapter()
		.subscribeSaveLoadObserver(this);
		app.getFileManager()
		.subscribeSaveLoadObserver(this);
	}

	public void backClicked() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.MenuView));
	}
	
	public void newGameStarted() {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.GameView));
	}
	
	public void existingGameStarted(ObjectId id) {
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.GameView));
	}

	@Override
	public void allGamesLoaded(ArrayList<Game> games, String response) {
		((NewGameView) view).updateUIWithGames();
	}

	@Override
	public void allGamesSaved(String response) {
	}

	@Override
	public void gameLoaded(Game game, String response) {
	}

	@Override
	public void gameSaved(String response) {
	}

	@Override
	public void gameNotSaved(String response) {
	}

	@Override
	public void gameNotLoaded(String response) {
	}
	
}
