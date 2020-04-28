package com.example.abstraction;

public abstract class Prototype implements Vehicle{
	
	Prototype(){
		System.out.println("Inside Prototype!");
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("I want to implement move method");
		
	}
	
	public abstract void brake();
	
//	public static void brake() {
//		System.out.println("Welcome to my static method!!!");
//	} 
//	Abstract 
}
