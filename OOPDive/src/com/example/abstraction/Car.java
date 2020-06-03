package com.example.abstraction;

public interface Car extends Vehicle{
		/*
		 * extends when: class -> class
		 * 				abstract class -> class
		 * 				abstract class -> abstract class
		 * 				interface -> interface
		 * 				(Can only extend linearly)
		 * 
		 * implements when: interface -> class
		 * 			(multiple interfaces)
		 * 
		 * Cannot go the other way:
		 * 		class -> abstract class
		 * 		class -> interface
		 * 		abstract class -> interface
		 */				
	
	//There is no constructor 
	
//	DON'T DO THIS!!!!!!!!!!!!!!!!!!!
//	public class InnerClass {
//		public interface InnerInterface{
//			public interface apples{
//				
//			}
//		}
//	}
	
	double pi = 3.14; //public static final double pi = 3.14;
	
	void startEngine(); //Abstract method 
	
	 default void drive() { //The keyword "default" allows you to create concrete methods inside interfaces
		
		System.out.println("Inside Car!");
		//Methods in Interfaces are by default public and abstract 
	}
}
