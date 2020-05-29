package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Star_table")
public class Star {
	
	@Id
	@Column(name="star_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int starId;
	// This is a column called planet_name
	@Column(name="star_name", unique=true, nullable=false)
	private String name;
	// This is a column called planet_slogan
	@Column(name="star_slogan")
	private String slogan;
	
	public Star() {
		super();
	}

	public Star(int starId, String name, String slogan) {
		super();
		this.starId = starId;
		this.name = name;
		this.slogan = slogan;
	}

	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
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
		return "Star [starId=" + starId + ", name=" + name + ", slogan=" + slogan + "]";
	}

}
