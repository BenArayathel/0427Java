package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Planet_Table")
public class Planet {
	
	@Id
	@Column(name = "planet_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int planetId;
	
	@Column(name = "planet_name", unique = true, nullable = false)
	private String planetName;
	
	public Planet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Planet(int planetId, String planetName) {
		super();
		this.planetId = planetId;
		this.planetName = planetName;
	}

	public int getPlanetId() {
		return planetId;
	}
	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}


	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", planetName=" + planetName + "]";
	}
	
	
	
	

}
