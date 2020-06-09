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

@Entity
@Table(name="PlanetTable")
public class Planet {
	
	@Id
	@Column(name="planet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int planetId;
	@Column(name="planet_name", unique=true, nullable=false)
	private String planetName;
	@OneToMany(mappedBy = "myPlanet", fetch = FetchType.LAZY)
	private List<Moon> moons = new ArrayList<>();

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
	public List<Moon> getMoons() {
		return moons;
	}
	public void setMoons(List<Moon> moons) {
		this.moons = moons;
	}
	@Override
	public String toString() {
		return "Planet [planetId=" + planetId + ", planetName=" + planetName + ", moons=" + moons + "]";
	}

}
