package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Planet_table")
public class Planet {
	
	/*
	 * JPA notations (Java Persistence API)\
	 * 	Is a standardized api that deals with mapping java objects to DBs.
	 * 
	 * Hibernate implements JPA annotations.
	 * 
	 *  We often choose JPA annotaions over hibernate annotations because if in the future, if we 
	 *  to change ORM frameworks it'll cause more hassle.  
	 */
	
	@Id
	@Column(name = "planet_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int planetId;
	
	@Column( name = "planet_name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "planet_slogan")
	private String slogan;
	
	/*
	 *no args constructor, getters/setters, all args construct , and a toString() 
	 */

	public Planet() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", name=" + name + ", slogan=" + slogan + "]";
	}
	
	
	
	

}
