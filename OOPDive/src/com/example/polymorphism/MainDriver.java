package com.example.polymorphism;

public class MainDriver {
	
	public static void main(String[] args) {
//		
////		Animal genericAnimal = new Animal();
////		
////		System.out.println(genericAnimal.numberOfEyes);
////		System.out.println(genericAnimal.numberOfLegs);
//		
//		Animal specialAnimal = new Animal(3);
//		
////		System.out.println(specialAnimal.numberOfEyes);
////		System.out.println(specialAnimal.numberOfLegs);
//		
//		specialAnimal.sleep();
//		specialAnimal.sleep("Pillow",2);
//		specialAnimal.sleep(4, "blankets");
//		
//		/*
//		 * Overloading is:
//		 * 		Same method name, but different parameters:
//		 * 			Different number of parameters 
//		 * 			Different types of parameters
//		 * 			Different order of parameters
//		 * 
//		 * 		Compile time polymorphism (static binding)
//		 */
//		
		
		
		/*
		 * OVERRIDING
		 * 
		 * Things that cannot be overridden:
		 * 				Constructors
		 * 				Static Methods -- Already loaded into the stack 
		 * 				Final Methods -- keyword stops overriding
		 * 				Private Methods -- inaccessible to the child classes 
		 * 
		 * When overriding:
		 * 			Visibility has to be more or equal to the inherited method
		 * 
		 * Runtime polymorphism (dynamic binding) -- Object is allocated memory at runtime.
		 * 
		 * Keyword "super", allows you to reference parent methods or constructors. 
		 */
		
		Animal a = new Dog();
		a.move(); //Gets access to Dog method without having to write more code. 
		Dog d = (Dog)a; //Cannot redeclare object
		d.bark();
		
		Animal.eat();
		d.eat();
		
//		Dog d = new Dog();
//		d.move();
//		System.out.println(d.numberOfEyes);
//		System.out.println(d.numberOfLegs);
		
//		GermanShepard g = new GermanShepard();
	}

}
