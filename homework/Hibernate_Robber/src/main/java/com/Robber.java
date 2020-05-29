package com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// with these annotations, hibernate will know how to automatically map these
// model fields to columns in the db

@Entity
@Table(name = "bank_robber")
public class Robber {
	
	@Id
	@Column(name = "id")
	// this is an automatically generated incrementing id number
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column( name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "weapon")
	private String weapon;
	
	
	public Robber() {
		super();
	}


	public Robber(int id, String name, String weapon) {
		super();
		this.name = name;
		this.id = id;
		this.weapon = weapon;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getid() {
		return id;
	}


	public void setid(int id) {
		this.id = id;
	}


	public String getWeapon() {
		return weapon;
	}


	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}


	@Override
	public String toString() {
		return "Robber [name=" + name + ", id=" + id + ", weapon=" + weapon + "]";
	}

}
