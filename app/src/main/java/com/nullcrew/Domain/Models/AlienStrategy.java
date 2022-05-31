package com.nullcrew.Domain.Models;

import com.nullcrew.Domain.Controllers.GameController;

public interface AlienStrategy {
	
	public abstract void hit(GameController gameController);
	
	public abstract void act(GameController gameController);
}
