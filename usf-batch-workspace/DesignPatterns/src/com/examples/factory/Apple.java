package com.examples.factory;

public class Apple implements Fruit{
	
	public String name;
	public double calories;
	public String color;
	
	

	public Apple(String name, double calories, String color) {
		super();
		this.name = name;
		this.calories = calories;
		this.color = color;
	}



	@Override
	public boolean isTasty() {

		System.out.println("Apple ain't tasty!");
		return false;
	}

}
