package com.revature.serialization;

import java.io.Serializable;

public class Television implements Serializable{

	/**
	 * This is for documentation only 
	 */
	private static final long serialVersionUID = 5616583199896677473L;
	
	private int id;
	private String type;
	private String brand;
	private int noOfPorts;
	private double size;
	
	public Television() {
		// TODO Auto-generated constructor stub
	}

	public Television(int id, String type, String brand, int noOfPorts, double size) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.noOfPorts = noOfPorts;
		this.size = size;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getNoOfPorts() {
		return noOfPorts;
	}

	public void setNoOfPorts(int noOfPorts) {
		this.noOfPorts = noOfPorts;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void turnOn() {
		System.out.println("The TV is ON...");
	}
	
	public void turnOff() {
		System.out.println("The TV is OFF...");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Television [ Type = " + type + ", Brand = " + brand + ", #Ports = " + noOfPorts + "-USB, Size = " + size + "in ]";
	}
}
