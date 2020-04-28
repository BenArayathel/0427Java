package com.example.abstraction;

public interface Truck {
	
	default void drive() {
		System.out.println("Inside Truck!");
	}
	
	void liftHeavyStuff(String item);
	
	public static void myMethod() {
		System.out.println("My truck, from java 8");
	}
	
	default void otherMethod() {
		System.out.println("Inside other method, deafult implementation from Java 8");
	}

}
