package com.example.abstraction;

public abstract class Prototype implements Vehicle {

	Prototype(){
		System.out.println("Inside Prototype!");
	}
	
	@Override
	public void move() {
		System.out.println("I want to implement move method");
	}
	
	public abstract void brake();
}
