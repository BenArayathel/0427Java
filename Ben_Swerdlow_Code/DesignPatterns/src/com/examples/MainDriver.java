package com.examples;

import com.examples.factory.Fruit;
import com.examples.factory.FruitFactory;
//import com.examples.singleton.Car;
//import com.examples.singleton.SingletonCar;

public class MainDriver {

	public static void main(String[] args) {
		/*
		 * What are design patterns?
		 * 		Language agnostic solutions to problem scenarios (can be in C#, Java, Python, etc.)
		 * 		Because it's not Java-specific, Eclipse won't make it for you
		 * 
		 * Allows for more reusable and maintainable code.
		 * 
		 * Creational DPs
		 * 		Meant to resolve issues with creating objects in a system
		 * 		Singleton: Simple design to enforce only one instance of a class
		 * 			Basic outline: make a private static variable of the same class within the class
		 * 							make the constructor(s) private
		 * 							make a public static method that either calls the constructor if no car
		 * 								else returns the existing car
		 * 			Useful for shopping carts, store-wide discounts, and many other things
		 * 
		 * 		Factory:
		 * 
		 * Structural DPs
		 * 		Focuses on how classes and objects can be composed. It identifies the simplest way to realize relationships
		 * 				between entities.
		 * 			Adapter:
		 * 
		 * Behavioral DPs
		 * 		Deal with how object interactions communicate with each other.
		 * 		To achieve simpler formats of communication
		 * 			Chain of Responsibility:
		 */
		
		/* SINGLETON PATTERN CODE
		Car car1 = new Car();
		Car car2 = new Car();
		
		car1.color = "blue";
		
		System.out.println(car1.color);
		System.out.println(car2.color);
		
		SingletonCar myCar = SingletonCar.getCar();
		// we're telling the static method to call the constructor for us, rather than calling it directly
		
		SingletonCar myCar2 = SingletonCar.getCar();
		
		myCar.setColor("blue");
		
		System.out.println(myCar.getColor());
		System.out.println(myCar2.getColor());
		
		SingletonCar myCar3 = SingletonCar.getCar();
		myCar2.setColor("green");
		
		System.out.println(myCar.getColor());
		System.out.println(myCar2.getColor());
		System.out.println(myCar3.getColor());
		*/
		
		/*
		 * Factory Design: We don't want to display how we create the class, but we want people to use it
		 */
		
		FruitFactory ff = new FruitFactory();
		Fruit ra = ff.getFruit("red apple");
		Fruit k = ff.getFruit("kiwi");
		Fruit ga = ff.getFruit("green apple");
		// The responsibility of creation logic is abstracted away from the user and the user just has to know they want a red apple
		// and doesn't have to worry about setting the appropriate attributes each time

		System.out.println(ra);
		System.out.println(k);
		System.out.println(ga);
	}

}
