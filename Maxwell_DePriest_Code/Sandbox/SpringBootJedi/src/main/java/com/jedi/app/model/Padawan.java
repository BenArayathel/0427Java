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
public class Padawan {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String lightsaberColor;
	
	private int jedi_id;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="jedi_id", referencedColumnName = "id")
//	private Jedi jedi;

	public Padawan() {
		// TODO Auto-generated constructor stub
	}

	public Padawan(String name, String lightsaberColor, int jedi_id) {
		
		this.name = name;
		this.lightsaberColor = lightsaberColor;
		this.jedi_id = jedi_id;

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

	public String getLightsaberColor() {
		return lightsaberColor;
	}

	public void setLightsaberColor(String lightsaberColor) {
		this.lightsaberColor = lightsaberColor;
	}

	public int getJedi_id() {
		return jedi_id;
	}

	public void setJedi_id(int jedi_id) {
		this.jedi_id = jedi_id;
	}

	
	
	
	


	
	
	
	
	
	

}
