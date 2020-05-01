package DesignPatterns;

public class SingletonCar {
	
	private static SingletonCar car;
	
	public String color;

	private SingletonCar() {
		this.color = "red";
	}
	
	// Use to access a private constructor
	public static SingletonCar getCar() {
		
		if(car == null) { // if car obj doesn't exist, we create one
			car = new SingletonCar();
		}
		
		return car;
	}

}
