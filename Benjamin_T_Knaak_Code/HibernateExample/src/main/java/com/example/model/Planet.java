package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Planets")
public class Planet {
	
	/*
	 * JPA notations (Java Persistence API)
	 * JPA is a standardized API that deals with mapping Java objects to DBs.
	 * 
	 * Hibernate implements JPA annotations.
	 * 
	 * We often choose JPA annotations over Hibernate annotations because if we wish to change ORM framesworks in the future
	 * it will cause more hassle to choose Hibernate
	 */

	@Id
	@Column(name = "planet_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int planetId;
	
	@Column(name = "planet_name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "planet_slogan")
	private String slogan;
	
	/*
	 * no args constructor, getters/setters
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
	
	
}
