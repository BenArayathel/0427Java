package com.jedi.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Jedi {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String rank;
	private String lightsaber_color;
	
	public Jedi() {
	
	}

	public Jedi(int id, String name, String rank, String lightsaber_color) {
		super();
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.lightsaber_color = lightsaber_color;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAffiliation() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getLightsaber_color() {
		return lightsaber_color;
	}

	public void setLightsaber_color(String lightsaber_color) {
		this.lightsaber_color = lightsaber_color;
	}
	
	

}
