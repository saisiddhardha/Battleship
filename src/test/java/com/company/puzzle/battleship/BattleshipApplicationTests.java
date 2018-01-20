package com.company.puzzle.battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.puzzle.battleship.initiator.BattleshipInitiator;
import com.company.puzzle.battleship.initiator.GameInitiator;
import com.company.puzzle.battleship.pojo.GameState;
import com.company.puzzle.battleship.service.BattleshipGame;
import com.company.puzzle.battleship.service.Game;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BattleshipApplicationTests {

	@Autowired
	private ApplicationContext ctx;

	private GameState gameState;
	private Game battleshipGame;
	private GameInitiator gameInitiator;

	@Before
	public void load() {
		gameState = ctx.getBean(GameState.class);
		assertNotNull(gameState);
		battleshipGame = ctx.getBean(BattleshipGame.class);
		assertNotNull(battleshipGame);
		gameInitiator = ctx.getBean(BattleshipInitiator.class);
		assertNotNull(gameInitiator);

		int[][] testShips = { { 3, 0 }, { 0, 1 }, { 4, 0 } };
		gameState.setShips(testShips);

		// game state
	}

	@Test
	public void testShipLocationBlast() {
		// gameState.printShips();
		assertFalse(battleshipGame.blast(0, 2));
		assertTrue(battleshipGame.blast(3, 0));
		assertFalse(battleshipGame.blast(0, 0));
		assertFalse(battleshipGame.blast(1, 1));
		assertTrue(battleshipGame.blast(0, 1));

	}

	@Test
	public void testHintsLength() {

		assertEquals(battleshipGame.hintLenght(0), 1);
		assertEquals(battleshipGame.hintLenght(1), 0);
		assertEquals(battleshipGame.hintLenght(3), 1);

	}

	@Test
	public void testHintsWidth() {

		assertEquals(battleshipGame.hintWidth(0), 2);
		assertEquals(battleshipGame.hintWidth(1), 1);
		assertEquals(battleshipGame.hintWidth(2), 0);

	}
	
	/*@Test
	public void testHintsType2Missile1() {
		int[][] testShips = { { 0, 1 }, { 2, 2 }, { 4, 2 } };
		gameState.setShips(testShips);
		assertEquals(battleshipGame.showHintMissleType2(1,1), 1);
		
	}*/

}
