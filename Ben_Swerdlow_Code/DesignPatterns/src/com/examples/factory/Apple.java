package com.examples.factory;

public class Apple implements Fruit {

	public String name;
	public int calories;
	public String color;
	
	public Apple(String name, int calories, String color) {
		super();
		this.name = name;
		this.calories = calories;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Apple [name=" + name + ", calories=" + calories + ", color=" + color + "]";
	}

	@Override
	public boolean isTasty() {
		// TODO Auto-generated method stub
		System.out.println("Apple is rotten");
		return false;
	}

}
