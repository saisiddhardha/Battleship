package com.auto1.puzzle.battleship.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * this entity class will store the state of the sea coordinates and 
 * reports of the bombing in that coordinates, hints of that bombing.
 */

@Entity
@Table(name = "SeaCoordinate")
public class SeaCoordinate {

	  @Id
	  @GeneratedValue
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "XCOORDINATE")
	  private int xCoordinate;
	  
	  @Column(name = "YCOORDINATE")
	  private int yCoordinate;
	  
	  @Column(name = "REPORT")
	  private String blastReport;
	  
	  @Column(name = "MISSILETYPE")
	  private int missileType;
	  
	  @Column(name = "SUCCESS")
	  private String success;
	  
	  public SeaCoordinate(){
		  
	  }
	  
	  public SeaCoordinate(int xCoordinate, int yCoordinate, String report, int missileType, String success) {
		    this.xCoordinate = xCoordinate;
		    this.yCoordinate = yCoordinate;
		    this.blastReport = report;
		    this.missileType = missileType;
		    this.success = success;
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

	public String getBlastReport() {
		return blastReport;
	}

	public void setBlastReport(String blastReport) {
		this.blastReport = blastReport;
	}

	public int getMissileType() {
		return missileType;
	}

	public void setMissileType(int missileType) {
		this.missileType = missileType;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	  
	
}
