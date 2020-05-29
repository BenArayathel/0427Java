package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Solarsystem_table")
public class SolarSystem {

	@Id
	@Column(name = "solar_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Column(name = "planet_name")
	private String name;
	
	
	@Column(name = "sun_count")
	private int sunCount;
	
	
	@Column(name = "size_desc")
	private String sizeDescription;
	

	public SolarSystem() {
		super();
	}

	public SolarSystem(String name, int id, int sunCount, String sizeDescription) {
		super();
		this.name = name;
		this.id = id;
		this.sunCount = sunCount;
		this.sizeDescription = sizeDescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSunCount() {
		return sunCount;
	}

	public void setSunCount(int sunCount) {
		this.sunCount = sunCount;
	}

	public String getSizeDescription() {
		return sizeDescription;
	}

	public void setSizeDescription(String sizeDescription) {
		this.sizeDescription = sizeDescription;
	}

	@Override
	public String toString() {
		return "SolarSystem [name=" + name + ", id=" + id + ", sunCount=" + sunCount + ", sizeDescription="
				+ sizeDescription + "]";
	}

}
