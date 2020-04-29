package com.example.abstraction;

public interface Car extends Vehicle {
	/*
	 * extends when class to class
	 * 				Abstract class to class
	 * 				abstract class to abstract class
	 * 				interface to interface
	 * 				(can only extend linearly)
	 * 
	 * implements when interface to class
	 * 			(multiple interfaces)
	 * 
	 * interface cannot extend to a class
	 */
	
	//There is no constructor
	
//	Can do this, but not practical so dont do it
//	public class InnerClass {
//		 public interface InnerInterface{
//			 public interface apples{
//				 
//			 }
//		 }
//	}
	double pi = 3.14; //public static final double pi = 3.14;
	
	void startEngine();//abstract method
	
	public abstract void drive();//methods in interfaces are default public and abstract
	//keyword default allows creation of concrete methods to allow interface references like truck.super.drive(); in volvo
}
//not alloud to have static or final on interface or abstract class because it is inheriteded
//abstract static and final cannot be used together, final and static can be used together