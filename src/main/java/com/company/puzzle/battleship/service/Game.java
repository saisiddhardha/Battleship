package com.company.puzzle.battleship.service;

public interface Game {

	void beginGame();
	
	boolean blast(int length, int width) ;
	
	int hintLenght(int length) ;
	
	int hintWidth(int width) ;
	
}
