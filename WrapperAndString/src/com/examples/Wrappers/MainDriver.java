package com.examples.Wrappers;

public class MainDriver {
	
	int c = 3;
	static int x = 23;
	
	public static int myMethod(int a) {
		System.out.println("Inside primitive");
		return a;
	}
	
	public static Integer myMethod(Integer a) {
		System.out.println("Inside object!");
		return a;
	}
	
	public static void main(String[] args)
	{
		MainDriver asdf = new MainDriver(); // c is added to the heap 
		
		/*
		 * A wrapper class, will wrap around primitive data types. 
		 * 
		 * boolean -> Boolean
		 * int -> Integer
		 * char -> Character
		 * byte -> Byte
		 * short -> Short
		 * long -> Long
		 * double -> Double
		 * 
		 * Why?
		 * 		Objects have methods associated with them
		 * 		Data structures like the Collection API
		 */
		
		int i = 5;
		//boxing
		Integer j = new Integer(5);
		//Autoboxing
		Integer k = 3; //WHERE IS NEW!!???? 
		//unboxing
		int l = k;
		
		String s = "123";
		Integer g = Integer.parseInt(s);
		System.out.println(i);
		
		System.out.println(Integer.MAX_VALUE); //If capitalized, is static final variable!!!
		System.out.println(Long.MAX_VALUE);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Short.MIN_VALUE);
		
		myMethod(3);
		myMethod(g);
	}
}
