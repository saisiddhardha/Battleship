package com.auto1.puzzle.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.auto1.puzzle.battleship.pojo.Ship;

@Repository
@Service("shipRepository")
public interface ShipRepository extends JpaRepository<Ship, Long> {

}
