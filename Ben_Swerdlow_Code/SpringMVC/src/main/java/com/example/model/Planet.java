package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="planet_table")
public class Planet {
	
	@Id
	@Column(name="planet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int planetId;
	@Column(name="planet_name", unique=true)
	@Pattern(regexp="[A-Za-z]+")
	@Size(min=3, max=20)
	private String planetName;
	
	public Planet() {
		super();
		System.out.println("In no args planet constructor");
	}

	public Planet(int planetId, String planetName) {
		super();
		System.out.println("In all args planet constructor");
		this.planetId = planetId;
		this.planetName = planetName;
	}

	public int getPlanetId() {
		return planetId;
	}

	public void setPlanetId(int planetId) {
		System.out.println("Setting planet Id");
		this.planetId = planetId;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		System.out.println("Setting planet name");
		this.planetName = planetName;
		System.out.println("");
	}

	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", planetName=" + planetName + "]";
	}

}
