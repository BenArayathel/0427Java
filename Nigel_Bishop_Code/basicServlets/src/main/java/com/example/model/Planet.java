package com.example.model;

public class Planet {
	
	String name;
	String moonname;
	double numberofmoon;
	
	public Planet() {
		// TODO Auto-generated constructor stub
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

	public String getMoonname() {
		return moonname;
	}

	public void setMoonname(String moonname) {
		this.moonname = moonname;
	}

	public double getNumberofmoon() {
		return numberofmoon;
	}

	public void setNumberofmoon(double numberofmoon) {
		this.numberofmoon = numberofmoon;
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + ", moonname=" + moonname + ", numberofmoon=" + numberofmoon + "]";
	}

	public Planet(String name, String moonname, double numberofmoon) {
		super();
		this.name = name;
		this.moonname = moonname;
		this.numberofmoon = numberofmoon;
	}


	

}
