package com.auto1.puzzle.battleship.initiator;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto1.puzzle.battleship.pojo.GameState;
import com.auto1.puzzle.battleship.service.PopulateGameState;

/*
 * BattleshipInitiator will load the game state based on users input to resume game or start new game.
 */

@Service
public class BattleshipInitiator implements GameInitiator{

	@Autowired
	private GameState gameState;

	@Autowired
	private PopulateGameState populateGameState;

	final private String YES = "yes";
	final private String NO = "no";

	@Override
	public void init() {
		// TODO Auto-generated method stub
		boolean valiedInput = false;
		String input = "";

		System.out.println("Battle began!!!");

		Scanner sc = new Scanner(System.in);

		while (!valiedInput) {
			System.out.println("Press yes if you want to resume, no for new game -> ");
			input = sc.next().toLowerCase();
			if (input.equals(YES) || input.equals(NO)) {
				valiedInput = true;
			} else {
				System.out.println("Invalied input!!!");
			}
		}

		if (input.equals(YES)) {
			// resume logic, to load the state from db
			populateGameState.populateResumeGameState();
		} else {
			// generate new game and persist in db
			populateGameState.populateNewGameState();
		}

	}

}
