package com.example.models;

public class Planet {
	
	private String name;
	
	public Planet() {
		
	}
	
	public Planet(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + "]";
	}
	
	
}
