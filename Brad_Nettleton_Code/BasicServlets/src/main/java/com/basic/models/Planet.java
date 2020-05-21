package com.basic.models;

public class Planet {
	
	private String name;
	private String id;
	
	
	public Planet() {}

	public Planet(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = this.name.hashCode() + "";
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + ", id=" + id + "]";
	}
	

}
