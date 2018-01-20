package com.auto1.puzzle.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.auto1.puzzle.battleship.initiator.BattleshipInitiator;
import com.auto1.puzzle.battleship.initiator.GameInitiator;
import com.auto1.puzzle.battleship.service.BattleshipGame;
import com.auto1.puzzle.battleship.service.Game;

@SpringBootApplication(scanBasePackages = {"com.auto1.puzzle.battleship.initiator", "com.auto1.puzzle.battleship.pojo", "com.auto1.puzzle.battleship.service", "com.auto1.puzzle.battleship.repositories", "com.auto1.puzzle.battleship.initiator"})
public class BattleshipApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BattleshipApplication.class, args);
		
		GameInitiator initiator = context.getBean(BattleshipInitiator.class);
		Game game = context.getBean(BattleshipGame.class); 
		
		initiator.init();
		game.beginGame();
		
	}

}
