package com.auto1.puzzle.battleship.pojo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*GameState bean will have the current state of the game. 
 * When the resume game happens this GameState should be populated by referring embedded h2 database*/

@Configuration
@ConfigurationProperties("")
public class GameState {

	@Value("${battleship.ship.count}")
	private int enemyShips;// total number of enemy ships

	@Value("${battleship.sea.dimensions}")
	private int seaDimensions;// dimensions of the sea example 5*5 or 6*6

	private char[][] sea;// Sea status where ~ means water, # means failed
							// bombing, * successful hit

	private int[][] ships;// [][] location of enemy ships, which is populated
							// using random number algorithm

	private int shipsBlasted;// total ships blasted successfully

	private int totalAttempts;// total bombing attempts

	private String playerName;// Name of the player who finished the game
								// successfully

	public int getEnemyShipsCount() {
		return enemyShips;
	}

	public int getSeaDimensions() {
		return seaDimensions;
	}

	public char[][] getSea() {
		return sea;
	}

	public void setSea(char[][] sea) {
		this.sea = sea;
	}

	public int[][] getShips() {
		return ships;
	}

	public void setShips(int[][] ships) {
		this.ships = ships;
	}

	public void printSea() {

		for (int i = 0; i < seaDimensions; i++) {
			for (int j = 0; j < seaDimensions; j++) {
				System.out.print(sea[i][j] + " ");
			}
			System.out.println();
		}

	}

	// TODO- should be removed
	public void printShips() {
		System.out.println("ships");
		for (int x = 0; x <= 2; x++) {
			for (int y = 0; y <= 1; y++) {
				System.out.print(ships[x][y] + " ");
			}
			System.out.println();
		}
	}

	/*
	 * public List<SeaCoordinate> getSeaCoordinatesBlasted() { return
	 * seaCoordinatesBlasted; }
	 * 
	 * public void setSeaCoordinatesBlasted(List<SeaCoordinate>
	 * seaCoordinatesBlasted) { this.seaCoordinatesBlasted =
	 * seaCoordinatesBlasted; }
	 * 
	 * public void addSeaCoordinateBlasted(SeaCoordinate seaCoordinate){
	 * this.seaCoordinatesBlasted.add(seaCoordinate); }
	 */

	public int getShipsBlasted() {
		return shipsBlasted;
	}

	public void setShipsBlasted(int shipsBlasted) {
		this.shipsBlasted = shipsBlasted;
	}

	public int getTotalAttempts() {
		return totalAttempts;
	}

	public void setTotalAttempts(int totalAttempts) {
		this.totalAttempts = totalAttempts;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
