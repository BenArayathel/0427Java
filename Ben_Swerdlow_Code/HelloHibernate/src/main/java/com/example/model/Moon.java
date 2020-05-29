package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Moons")
public class Moon {
	
	@Id
	@Column(name = "moon_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int moonId;
	
	@Column(name = "moon")
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "planet_FK")
	private Planet myPlanet;

	public Moon() {
		super();
	}

	public Moon(int moonId, String name, Planet myPlanet) {
		super();
		this.moonId = moonId;
		this.name = name;
		this.myPlanet = myPlanet;
	}

	public int getMoonId() {
		return moonId;
	}

	public void setMoonId(int moonId) {
		this.moonId = moonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Planet getMyPlanet() {
		return myPlanet;
	}

	public void setMyPlanet(Planet myPlanet) {
		this.myPlanet = myPlanet;
	}

	// Might cause an issue. Find out soon!
	@Override
	// Remove the myPlanet stuff to prevent recursion when calling Planet to String
	public String toString() {
		return "Moon [moonId=" + moonId + ", name=" + name + /*", myPlanet=" + myPlanet + */"]";
	}
	
}
