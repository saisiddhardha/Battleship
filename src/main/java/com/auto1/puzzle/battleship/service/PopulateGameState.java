package com.auto1.puzzle.battleship.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto1.puzzle.battleship.pojo.GameState;
import com.auto1.puzzle.battleship.pojo.SeaCoordinate;
import com.auto1.puzzle.battleship.pojo.Ship;
import com.auto1.puzzle.battleship.repositories.SeaCoordinateRepository;
import com.auto1.puzzle.battleship.repositories.ShipRepository;

/*
 * This PopulateGameState bean have service methods which will be helpful in  populating the data into GameState bean.
 */

@Service
public class PopulateGameState {

	@Autowired
	private GameState gameState;

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private SeaCoordinateRepository seaCoordinateRepository;

	/*
	 * populateResumeGameState() this method is called when game needs to be
	 * resumed populates the data into GameState bean from h2 database. Provided
	 * RECAP of the game played earlier, like bombings and hints done earlier.
	 */
	public void populateResumeGameState() {

		if(shipRepository.findAll().size() != gameState.getEnemyShipsCount()){
			populateNewGameState();
		}
		else{
		resumeInitilizeShips();
		resumeInitializeSea();

		System.out.println("RECAP!!!");
		for (SeaCoordinate seaCoordinate : seaCoordinateRepository.findAll()) {
			System.out.println("X Coordinate ::" + seaCoordinate.getxCoordinate() + " X Coordinate ::"
					+ seaCoordinate.getyCoordinate());
			System.out.println(seaCoordinate.getBlastReport());
		}
		System.out.println();
		System.out.println("Current Sea status , ~ water, * successful blast, # un-successful blast");
		gameState.printSea();
		System.out.println();
		}

	}

	/*
	 * populateNewGameState() is called when fresh/new game needs to be started.
	 * Removes the old games data from database if available. Randomly creates
	 * the ships in different locations.
	 */
	public void populateNewGameState() {

		newInitilizeShips();
		newInitializeSea();

	}

	/*
	 * initialize ships and deletes old data from database.
	 */
	private void newInitilizeShips() {

		shipRepository.deleteAll();

		Random randomGenrator = new Random();

		int intializedShipCount = 0;
		int[][] ships = new int[gameState.getEnemyShipsCount()][2];

		do {
			int randomLength = randomGenrator.nextInt(gameState.getSeaDimensions());
			int randomWidth = randomGenrator.nextInt(gameState.getSeaDimensions());
			if (!distinctShipInitializationCheck(ships, randomLength, randomWidth, intializedShipCount)) {
				ships[intializedShipCount][0] = randomLength;
				ships[intializedShipCount][1] = randomWidth;
				intializedShipCount++;
			}

		} while (intializedShipCount != gameState.getEnemyShipsCount());
		gameState.setShips(ships);

		// TODO store ships coordinates in DB

		for (int i = 0; i < gameState.getEnemyShipsCount(); i++) {
			shipRepository.save(new Ship(ships[i][0], ships[i][1]));
		}

	}

	/*
	 * this method is used by newInitilizeShips() to make sure distinct ships
	 * are initialized
	 */
	private boolean distinctShipInitializationCheck(int[][] ships, int length, int width, int count) {
		boolean alreadyExists = false;

		if (count != 0) {

			for (int i = 0; i <= count - 1; i++) {
				if (ships[i][0] == length) {
					if (ships[i][1] == width) {
						alreadyExists = true;
					}
				}
			}
		}

		return alreadyExists;
	}

	private void newInitializeSea() {

		seaCoordinateRepository.deleteAll();

		int sizeLength = gameState.getSeaDimensions();
		int sizeWidth = gameState.getSeaDimensions();
		char[][] sea = new char[sizeLength][sizeWidth];
		for (int i = 0; i < sizeLength; i++) {
			for (int j = 0; j < sizeWidth; j++) {
				sea[i][j] = '~';
			}
		}
		gameState.setSea(sea);

	}

	/*
	 * resumes sea by referring DB.
	 */
	private void resumeInitializeSea() {
		// TODO
		int shipsBlasted = 0;
		int sizeLength = gameState.getSeaDimensions();
		int sizeWidth = gameState.getSeaDimensions();
		char[][] sea = new char[sizeLength][sizeWidth];
		for (int i = 0; i < sizeLength; i++) {
			for (int j = 0; j < sizeWidth; j++) {
				sea[i][j] = '~';
			}
		}
		List<SeaCoordinate> seaCoordinatesList = seaCoordinateRepository.findAll();

		for (SeaCoordinate seaCoordinate : seaCoordinatesList) {
			if (seaCoordinate.getSuccess().equals("yes")) {
				sea[seaCoordinate.getxCoordinate()][seaCoordinate.getyCoordinate()] = '*';
				shipsBlasted++;
			} else {
				sea[seaCoordinate.getxCoordinate()][seaCoordinate.getyCoordinate()] = '#';
			}
		}
		gameState.setSea(sea);
		gameState.setTotalAttempts(seaCoordinatesList.size());
		gameState.setShipsBlasted(shipsBlasted);

	}

	/*
	 * resumes the ship locations and loads ships locations into GameState.
	 */
	private void resumeInitilizeShips() {
		int count = 0;
		int[][] ships = new int[gameState.getEnemyShipsCount()][2];
		List<Ship> shipsList = shipRepository.findAll();

		if (shipsList.size() <= 3) {
			for (Ship ship : shipsList) {
				ships[count][0] = ship.getxCoordinate();
				ships[count][1] = ship.getyCoordinate();
				count++;
			}
		} else {
			System.out.println("Ships details in DB are corrupt, please start new game");
			System.exit(1);
		}
		gameState.setShips(ships);
	}

}
