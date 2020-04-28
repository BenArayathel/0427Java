package com.example.polymorphism;

public class Animal {
	
	public int numberOfEyes;
	public int numberOfLegs;
	
	/*
	 * JVM will give every class a default constructor (if there is no explicit constructor)
	 * visibility will also be default
	 */
	
	//Constructor overloading
	public Animal() { //No args constructor 
		this(2,4);
//		super();
//		new Animal(1); // A separate memory gets created, less efficient
		System.out.println("No args constructor!!!");
//		this.numberOfEyes = 2;
//		this.numberOfLegs = 4;
	}
	
	public Animal(int numberOfLegs) {
		this(5,numberOfLegs);
		System.out.println("1 args constructor");
	}
	
	public Animal(int numberOfEyes, int numberOfLegs) { //Parametrized constructor 
//		this(); //Imagine this(); being replaced with Animal();
		System.out.println("Parametrized constructor");
		this.numberOfEyes = numberOfEyes; //keyword "this" references to variables belonging to the instance level
		this.numberOfLegs = numberOfLegs;
	}
	
	void move() {
		System.out.println("Animal is moving");
	}
	
	public static void eat() {
		System.out.println("Animal is consuming calories");
	}
	
	//Method Overloading
	public void sleep() {
		System.out.println("Sleeping!!!");
	}
	
	public void sleep(String item, int otherItem) {
		System.out.println("Sleeping with " + item);
	}
	
	public void sleep(int otheritem, String item) {
		System.out.println("Sleeping with numbers " + item);
	}

}
