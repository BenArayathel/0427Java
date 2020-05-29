package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// This should be an entity that is saved in the database
@Entity
// Specifically, save it in the Planets table
@Table(name="Planet_table")
public class Planet {
	
	/*
	 * JPA Annotations (Java Persistence API)
	 * 	- A standardized api that deals with mapping java objects to DBs
	 * 	- Hibernate implements JPA annotations
	 * 	- We often choose JPA annotations over hibernate annotations because it's generalizable
	 * 	- If in the future we want to change ORM frameworks, we will have less hassle.
	 */
	
	// This is the primary key
	@Id
	@Column(name="planet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int planetId;
	// This is a column called planet_name
	@Column(name="planet_name", unique=true, nullable=false)
	private String name;
	// This is a column called planet_slogan
	@Column(name="planet_slogan")
	private String slogan;
	
	@OneToMany(mappedBy = "myPlanet", fetch = FetchType.LAZY)
	private List<Moon> moons = new ArrayList<>();
	
	/*
	 * Hibernate needs a no args constructor, getters and setters, and toString()
	 * 	(Hibernate will make an empty object, then populate with getters and setters [and write to database with toString?])
	 * 
	 */

	public Planet() {
		super();
	}
	
	public Planet(int planetId, String name, String slogan) {
		super();
		this.planetId = planetId;
		this.name = name;
		this.slogan = slogan;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public List<Moon> getMoons() {
		return moons;
	}

	public void setMoons(List<Moon> moons) {
		this.moons = moons;
	}

	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", name=" + name + ", slogan=" + slogan + ", moons=" + moons + "]";
	}

}
