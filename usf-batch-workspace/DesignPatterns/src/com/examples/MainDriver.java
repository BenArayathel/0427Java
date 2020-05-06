package com.examples;

import com.examples.factory.Fruit;
import com.examples.factory.FruitFactory;
import com.examples.singleton.Car;
import com.examples.singleton.SingletonCar;

public class MainDriver {

	public static void main(String[] args) {
		
		/*
		 * What are design patterns?
		 * 		Language agnostic solutions to common problem scenarios
		 * 
		 * Allows for more reusable and maintable code.
		 * 
		 * Creational Design Patterns
		 * 		Meant to resolve issues with creating objects in a system.
		 * 		Singleton: Simple design, it forces there to be only one instance of a class.
		 * 		Factory: Allows use to create multiple instances and hides creation logic
		 * 
		 * Structural Design Patterns
		 * 		Focuses on how classes and objects can be composed. It identifies the 
		 * 		simplest way to realize realitionships between enitites. 
		 * 		Adapter:
		 * 
		 * Behavioral Design Patterns
		 * 		Deal with how object interactions, how they communicate with each other. 
		 * 		To achieve simpler formats of communications
		 * 		Chain of REsponsibility:
		 */
		
		/*SINGLETON PATTERN CODE
		 * 
		 * Car car1 = new Car();
		Car car2 = new Car();
		
		car1.color = "blue";
		
		System.out.println(car1.color);
		System.out.println(car2.color);

		SingletonCar myCar = SingletonCar.getCar();
		SingletonCar myCar2 = SingletonCar.getCar();
		SingletonCar myCar3 = SingletonCar.getCar();
		
		SingletonCar myCar4 = myCar;
		
		myCar.setColor("blue");
		myCar2.setColor("green");
		
		System.out.println(myCar.getColor());
		System.out.println(myCar2.getColor());
		System.out.println(myCar3.getColor());
		 * 
		 */
		
		
		/*
		 * FACTORY DESIGN 
		 */
		
		FruitFactory ff = new FruitFactory();
		Fruit a = ff.getFruit("green apple");
		System.out.println(a.isTasty());
		
		
	}

}
