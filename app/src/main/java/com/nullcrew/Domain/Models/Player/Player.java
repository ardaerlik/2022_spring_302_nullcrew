package com.nullcrew.Domain.Models.Player;

import java.util.ArrayList;

public class Player {
	
	private int score;
	private int time;
	private int lives;
	private int[] scores;
	
	public Player() {
	}
	
	public boolean repOk() {
		if (score > Double.MAX_VALUE || score < Double.MAX_VALUE - 1) {
			return false;
		} 
		return true;
		
		/*
        if (xLong > Double.MAX_VALUE || xLong < Double.MAX_VALUE * -1) return false;
        return !(yLong > Double.MAX_VALUE) && !(yLong < Double.MAX_VALUE * -1);
        */
    }
	
	public void appendScore(int score) {
		
	}
	
	
	
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	
}
