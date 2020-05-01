package com.examples.singleton;

public class SingletonCar {
	
	// Make the instance of car static, so it won't be changed
	// can only be accessed within the class
	private static SingletonCar car;
	// Normal class variable
	private String color;
	
	private SingletonCar() {
		// make constructor private, will be accessed by public static method
		this.color = "red";
	}
	
	public static SingletonCar getCar() {
		// Now we can access a private constructor via the public static method
		// from within the class
		if (car==null) {// if car object exists, we make one
			car = new SingletonCar();
		}// if car object exist, pass the same instance back
		
		return car;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
