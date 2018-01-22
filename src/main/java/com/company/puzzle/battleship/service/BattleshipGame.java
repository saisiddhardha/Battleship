package com.company.puzzle.battleship.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.puzzle.battleship.initiator.BattleshipInitiator;
import com.company.puzzle.battleship.pojo.GameState;
import com.company.puzzle.battleship.pojo.Player;
import com.company.puzzle.battleship.pojo.SeaCoordinate;
import com.company.puzzle.battleship.repositories.PlayerRepository;
import com.company.puzzle.battleship.repositories.SeaCoordinateRepository;
import com.company.puzzle.battleship.repositories.ShipRepository;

/*
 * this class contains the services methods involved in the game.
 */

@Service
public class BattleshipGame implements Game{
	
	private static final Logger log = LoggerFactory.getLogger(BattleshipGame.class);

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private SeaCoordinateRepository seaCoordinateRepository;
	
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameState gameState;

	int inputLength;
	int inputWidth;

	StringBuilder hintMessage;
	String playerName;
	
	Scanner in = new Scanner(System.in);

	/*
	 * To begin the game
	 */
	@Override
	public void beginGame(){
		log.info("Intialization completed!! starting the game");
		
	while(gameState.getShipsBlasted()<gameState.getEnemyShipsCount()){
		System.out.println("---------------------------------------------------");
		System.out.println();
		getInput();
		
		if(blast(inputLength, inputWidth)){
			log.debug("blast successfull at coordinates length:"+inputLength +" width:"+inputWidth);
			changesInSea(inputLength, inputWidth, '*');
			System.out.println("Hit successful!!!");
			System.out.println();
			System.out.println("Current Sea status , ~ water, * successful blast, # un-successful blast");
			gameState.printSea();
			System.out.println();
			gameState.setShipsBlasted(gameState.getShipsBlasted()+1);
			gameState.setTotalAttempts(gameState.getTotalAttempts()+1);
			seaCoordinateRepository.save(new SeaCoordinate(inputLength, inputWidth, "Hit successful", 1, "yes"));
			
		}else{
			log.debug("failed hit at coordinates length:"+inputLength +" width:"+inputWidth);
			changesInSea(inputLength, inputWidth, '#');
			System.out.println("Failed to hit, focus on hint");
			gameState.printSea();
			showHint(inputLength, inputWidth);
			hintMessage = new StringBuilder("failed hit, HINT: ships in row::").append(hintLenght(inputLength)).append(" , ships in column::").append(hintWidth(inputWidth));
			gameState.setTotalAttempts(gameState.getTotalAttempts()+1);
			seaCoordinateRepository.save(new SeaCoordinate(inputLength, inputWidth, hintMessage.toString(), 1, "no" ));
		}
		
	}
	log.debug("all ships blasted");
	if(gameState.getEnemyShipsCount() == gameState.getShipsBlasted()){
		System.out.println("All ships fired, in "+ gameState.getTotalAttempts() +" attempts");
		System.out.println("Please enter player name ->");
		playerName = in.next();
		playerRepository.save(new Player(gameState.getTotalAttempts(), playerName));
	}
	log.debug("Ranking of the players");
	System.out.println("------------RANKINGS----------");
	System.out.println("Player -> Attempts");
	for(Player player : playerRepository.findOrderByAttempts()){
		System.out.println(player.getName() + "  ->  " + player.getAttempts());
	}
	}

	/*
	 * for input of coordinates which needs to be blasted.
	 */
	private void getInput() {
		boolean valiedInput = false;
		//Scanner in = new Scanner(System.in);
		while (!valiedInput) {
			try{
			System.out.println("Input length coordinates for blast ->");
			inputLength = Integer.parseInt(in.next());
			System.out.println("Input width coordinates for blast ->");
			inputWidth = in.nextInt();

			if (inputLength < gameState.getSeaDimensions() && inputLength > -1
					&& inputWidth < gameState.getSeaDimensions() && inputWidth > -1) {
				valiedInput = true;
			} else {
				System.out.println("Given input is not valied, input coordinates should be in between -1 and "
						+ gameState.getSeaDimensions());
			}}catch(Exception e){
				System.out.println("Invalied input format, should be a number");
			}

		}
	}

	/*
	 * After bombing, sea in gameState changes.
	 */
	void changesInSea(int length, int width, char c) {
		char[][] sea = gameState.getSea();
		sea[length][width] = c;
		gameState.setSea(sea);
	}

	/*
	 * return true if bombing in that input coordinates is a successful hit, false other ways.
	 */
	@Override
	public boolean blast(int length, int width) {
		log.debug("checking if blast is sucess at length:"+length+" width:"+width);
		int[][] ships = gameState.getShips();
		boolean success = false;
		for (int x = 0; x < gameState.getEnemyShipsCount(); x++) {
			if (ships[x][0] == length) {
				// for(int y=0;y<=1;y++){

				if (ships[x][1] == width) {
					success = true;
				}
				// }
			}
		}

		return success;
	}

	/*
	 * after failed hit, hint is provided how many ships are in that row.
	 */
	@Override
	public int hintLenght(int length) {
		int count = 0;
		int[][] ships = gameState.getShips();

		for (int x = 0; x < gameState.getEnemyShipsCount(); x++) {
			if (ships[x][0] == length) {
				count++;
			}
		}
		return count;

	}

	/*
	 * after failed hit, hint is provided how many ships are in that column.
	 */
	@Override
	public int hintWidth(int width) {
		int count = 0;
		int[][] ships = gameState.getShips();

		for (int x = 0; x < gameState.getEnemyShipsCount(); x++) {
			if (ships[x][1] == width) {
				count++;
			}
		}
		return count;

	}

	private void showHint(int length, int width) {
		log.debug("providing  hint for coordinates length:"+length+" width:"+width);
		System.out.println("hint, number of ships in that column::" + hintWidth(width));
		System.out.println("hint, number of ships in that row::" + hintLenght(length));
	}
	
}
