package com.company.puzzle.battleship.initiator;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.puzzle.battleship.pojo.GameState;
import com.company.puzzle.battleship.service.PopulateGameState;

/*
 * BattleshipInitiator will load the game state based on users input to resume game or start new game.
 */

@Service
public class BattleshipInitiator implements GameInitiator{
	
	private static final Logger log = LoggerFactory.getLogger(BattleshipInitiator.class);

	@Autowired
	private GameState gameState;

	@Autowired
	private PopulateGameState populateGameState;

	final private String YES = "yes";
	final private String NO = "no";

	@Override
	public void init() {
		// TODO Auto-generated method stub
		log.info("Game initialization started");
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
			log.debug("Resuming old game state");
			populateGameState.populateResumeGameState();
		} else {
			// generate new game and persist in db
			log.debug("Loading new game state");
			populateGameState.populateNewGameState();
		}

	}

}
