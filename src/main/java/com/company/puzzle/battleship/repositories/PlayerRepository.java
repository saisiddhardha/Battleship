package com.company.puzzle.battleship.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.company.puzzle.battleship.pojo.Player;

@Repository
@Service("playerRepository")
public interface PlayerRepository extends JpaRepository<Player, Long>{
	
@Query("from Player p order by p.attempts")	
List<Player> findOrderByAttempts();
}
