package com.auto1.puzzle.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.auto1.puzzle.battleship.pojo.SeaCoordinate;

@Repository
@Service("seaCoordinateRepository")
public interface SeaCoordinateRepository extends JpaRepository<SeaCoordinate, Long>{

}
