package com.nullcrew.Domain.Models;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nullcrew.Domain.Controllers.GameController;

public class ExplosiveAsteroid extends Asteroid {
	private int hit_range;

	public ExplosiveAsteroid(GameController gameController,double x, double y, int width, int height, double speed) {
		super(gameController,x, y, width, height, speed, Color.BLACK, AsteroidType.Explosive);
		lives = 1;
		hit_range = 150;
	}

	public int getLives() {
		return lives;
	}

	@Override
	public void hit(GameController gameController) {
		ArrayList<Asteroid> list = gameController.getAsteroidList();
		list.remove(this);
		gameController.setAsteroids(list);

	}

	@Override
	public Asteroid clone() {
		return new ExplosiveAsteroid(this.getGameController(),this.x, this.y, this.width, this.height, super.getSpeed());
	}

	public void hit_nearby(GameController gameController) {
		ArrayList<Asteroid> list = gameController.getAsteroidList();
		ArrayList<Asteroid> temp_list = new ArrayList<Asteroid>(list.stream().map(x -> (Asteroid) x).collect(Collectors.toList()));

		if (list != null && list.size() != 0) {
			for (Asteroid astr : list) {
				if (astr instanceof ExplosiveAsteroid) {
					continue;
				}
				if (new Rectangle((int)this.x, (int)this.y, hit_range, hit_range)
						.intersects(new Rectangle((int)astr.x, (int)astr.y, astr.getWidth(), astr.getHeight()))) {

					temp_list.remove(astr);
				}

			}

		}

		gameController.setAsteroids(temp_list);
	}

}
