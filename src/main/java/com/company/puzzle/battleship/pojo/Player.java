package com.company.puzzle.battleship.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Entity class which stores players details like name and number of attempts.
 */

@Entity
@Table(name = "Player")
public class Player {

	  @Id
	  @GeneratedValue
	  @Column(name = "ID")
	  private Long id;

	  @Column(name = "attempts")
	  private int attempts;
	  
	  @Column(name = "Name")
	  private String name;

	  public Player() {
	  }

	  public Player(int attempts, String name) {
	    this.attempts = attempts;
	    this.name = name;
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
