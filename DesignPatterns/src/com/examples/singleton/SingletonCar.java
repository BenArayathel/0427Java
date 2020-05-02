package com.examples.singleton;

public class SingletonCar {
	
	private static SingletonCar car = new SingletonCar();
	
	private String color;
	
	private SingletonCar() { //Make constructor private 
		this.color = "red";
	}
	
	public static SingletonCar getCar() {//Create a static public method to access the constructor from inside the class
		
//		if(car == null) { //if car object doesn't exist, we create a new one
//			car = new SingletonCar(); 
//		}
		
		//otherwise we pass the same instance back.
		return car;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	

}
