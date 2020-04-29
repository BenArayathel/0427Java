package com.example.Wrappers;

public class MainDriver {
	
	// instance level; will only be loaded into the stack when class is instantiated
	// and each instance can have a custom value for c
	int c = 3;
	// class level; does not need to instantiate the class to be loaded into the stack
	// and will be the same across all instances of the class, even if changed
	static int x = 23;
	// convention for static final variables (a.k.a. constants)
	static final int UNITY = 1;
	
	public static Integer myMethod(Integer a) {
		System.out.println("This is a wrapper class");
		return a;
	}
	
	public static int myMethod(int a) {
		System.out.println("This is a primitive");
		return a;
	}
	
	public static int otherMethod(int a) {
		System.out.println("This can take an int or an Integer");
		return a;
	}
	
	public static int anotherMethod(Integer a) {
		System.out.println("This can take an int or an Integer also.");
		return a;
	}

	public static void main(String[] args) {
		/*
		 * A wrapper class will wrap around primitive datatypes to get more functionality
		 * 		boolean -> Boolean
		 * 		int -> Integer
		 * 		char -> Character
		 * 		byte -> Byte
		 * 		short -> Short
		 * 		long -> Long
		 * 		double -> Double
		 * 		float -> Float
		 * 
		 * Why do we need this?
		 * 		Objects have methods associated with them
		 * 		Data structures like Collection API only store Objects
		 */
		
		// Sidenote: anything in the main method is in the stack
		
		// normal primitive declaration
		int i = 5;
		// boxing: making a wrapper with a primitive in it; deprecated since version 9, but valid in version 8
		Integer j = new Integer(5);
		// autoboxing: easier way of making a wrapper with a primitive in it; not deprecated and valid in version >=8
		Integer k = 3; // The new keyword is implied; it's in the common string pool (will be explained later)
		// unboxing: taking a value out of the wrapper
		int l = j;
		Float w = k.floatValue();
		// Useful Integer function: Integer.parseInt(s);
		String s = "121";
		Integer g = Integer.parseInt(s);
		System.out.println(g/11);
		
		MainDriver.myMethod(g);
		MainDriver.myMethod(l);
		// Anything accessible without instantiating and written in upper case is static final by convention
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
	}

}
