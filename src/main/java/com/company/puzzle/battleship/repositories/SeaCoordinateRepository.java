package com.company.puzzle.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.company.puzzle.battleship.pojo.SeaCoordinate;

@Repository
@Service("seaCoordinateRepository")
public interface SeaCoordinateRepository extends JpaRepository<SeaCoordinate, Long>{

}
