package com.auto1.puzzle.battleship.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * This entity table stores the ships coordinates.
 */
@Entity
@Table(name = "ship")
public class Ship {

	  @Id
	  @GeneratedValue
	  @Column(name = "ID")
	  private Long id;

	  @Column(name = "XCOORDINATE")
	  private int xCoordinate;
	  @Column(name = "YCOORDINATE")
	  private int yCoordinate;

	  public Ship() {
	  }

	  public Ship(int xCoordinate, int yCoordinate) {
	    this.xCoordinate = xCoordinate;
	    this.yCoordinate = yCoordinate;
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
}
