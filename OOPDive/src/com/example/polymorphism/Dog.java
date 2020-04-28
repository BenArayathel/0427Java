package com.example.polymorphism;

public class Dog extends Animal{
	
	boolean hasFur;
	
	public Dog() {
//		this.numberOfEyes = 2;
//		this.numberOfLegs = 4;
		this(true);
	}
	
	public Dog(boolean hasFur) {
		super(); //Can be implicit
		System.out.println("Inside Dog Constructor");
		this.hasFur = hasFur;
	}

	@Override //Override annotation, to ensure a method is being overridden, otherwise an exception is thrown.
	public void move() {
		
		System.out.println("Chasing the bone");
		super.move();
	}
	
	public void bark() {
		System.out.println("Barking");
	}
}
