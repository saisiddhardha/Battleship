package com.company.puzzle.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.company.puzzle.battleship.initiator.BattleshipInitiator;
import com.company.puzzle.battleship.initiator.GameInitiator;
import com.company.puzzle.battleship.service.BattleshipGame;
import com.company.puzzle.battleship.service.Game;

@SpringBootApplication(scanBasePackages = {"com.company.puzzle.battleship.initiator", "com.company.puzzle.battleship.pojo", "com.company.puzzle.battleship.service", "com.company.puzzle.battleship.repositories", "com.company.puzzle.battleship.initiator"})
public class BattleshipApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BattleshipApplication.class, args);
		
		GameInitiator initiator = context.getBean(BattleshipInitiator.class);
		Game game = context.getBean(BattleshipGame.class); 
		
		initiator.init();
		game.beginGame();
		
	}

}
