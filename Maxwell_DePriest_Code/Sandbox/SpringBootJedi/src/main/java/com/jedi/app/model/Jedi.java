package com.jedi.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private boolean havePadawan;
	
	
	public Jedi() {
	
	}

	public Jedi( String name, String rank, String lightsaber_color, boolean havePadawan) {
		this.name = name;
		this.rank = rank;
		this.lightsaber_color = lightsaber_color;
		this.havePadawan = havePadawan;

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

	public String getRank() {
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
