package com.example.polymorphism;

public class MainDriver {

	public static void main(String[] args) {
		Animal genericAnimal = new Animal();
		
		System.out.println(genericAnimal.numberOfEyes);
		System.out.println(genericAnimal.numberOfLegs);
		
		Animal specialAnimal = new Animal(3,5);
		System.out.println(specialAnimal.numberOfEyes);
		System.out.println(specialAnimal.numberOfLegs);
		
		MainDriver o = new MainDriver();
		
		specialAnimal.sleep();
		specialAnimal.sleep("Pillow");
		specialAnimal.sleep(4);
		specialAnimal.sleep("Pillow", 4);
		specialAnimal.sleep(4, "Pillow");
		
		/*Overloading(method and constuctor) is:
		 * 	Same method name but different parameters
		 * 	Different number of parameters
		 * 	Different types
		 * 	Different Order
		 *  Compile time polymorphism (static binding)
		 */
		
		
		
		/*
		 * Overriding
		 * 
		 * Things that cannot be overridden:
		 * 		Constructors,
		 * 		private methods-inaccesible to child classes
		 * 		final methods--keyword stops overriding
		 * 		static methods - already loaded into stack
		 * can upgrade visibility but cant decrease visibility./equal or more to inherited method
		 * runtime polymorphisim(dynamic binding) --Obj is allocated memory at runtime
		 * keyword "super" allows you to reference parent methods or constructors
		 */
		
		Animal a = new Dog();
		a.move(); //gets access to dog method without having to write more code
		Dog n = (Dog)a; //cannot redeclare object
		n.bark();
		
		Dog d = new Dog();
		d.move();
		System.out.println(d.numberOfEyes);
		System.out.println(d.numberOfLegs);
		
		GermanSherpard g = new GermanSherpard();
	}

}
