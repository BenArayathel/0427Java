package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Jedi_table")
public class Jedi {
	@Id
	@Column(name = "jedi_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jediId;
	
	@Column(name = "jedi_name", unique=true, nullable = false)
	private String name;
	@Column(name = "lightsaberColor", nullable = true)
	private String lightsaberColor;
	
	@Column(name = "alive", nullable = false)
	private String alive;

	public Jedi() {
		
	}

	public Jedi(int jediId, String name, String lightsaberColor, String alive) {
		this.jediId = jediId;
		this.name = name;
		this.lightsaberColor = lightsaberColor;
		this.alive = alive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLightsaberColor() {
		return lightsaberColor;
	}

	public void setLightsaberColor(String lightsaberColor) {
		this.lightsaberColor = lightsaberColor;
	}

	public String isAlive() {
		return alive;
	}

	public void setAlive(String alive) {
		this.alive = alive;
	}

	public int getJediId() {
		return jediId;
	}

	public void setJediId(int jediId) {
		this.jediId = jediId;
	}

	@Override
	public String toString() {
		return "Jedi [jediId=" + jediId + ", name=" + name + ", lightsaberColor=" + lightsaberColor + ", alive=" + alive
				+ "]";
	}

	

	
}
