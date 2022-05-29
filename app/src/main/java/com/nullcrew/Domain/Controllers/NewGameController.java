package com.nullcrew.Domain.Controllers;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.nullcrew.AlienAsteroidGame;
import com.nullcrew.Domain.Controllers.NewGameController;
import com.nullcrew.Domain.Models.Constants.DatabaseResponses;
import com.nullcrew.Domain.Models.Game;
import com.nullcrew.Domain.Models.User;
import com.nullcrew.UI.Views.NewGameView;
import com.nullcrew.Utilities.SaveLoadObserver;
import com.nullcrew.UI.Views.AppViewType;
import com.nullcrew.UI.Views.GameView;
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
		Game.setCurrentGame(new Game());
		super.changeView(AppViewFactory.getInstance()
				.createAppView(AppViewType.GameView));
	}
	
	public void existingGameStarted(ObjectId id) {
		for (Game game: User.getInstance().getAccount().getSavedGames()) {
			if (game.getGameId().equals(id)) {
				Game.setCurrentGame(game);
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
