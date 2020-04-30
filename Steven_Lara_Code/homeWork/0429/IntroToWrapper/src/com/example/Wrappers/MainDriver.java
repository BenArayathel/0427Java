package com.example.Wrappers;

public class MainDriver {
	
	public void myMethod(int x) {
		System.out.println("Inside primitive");
	}
	
	public void myMethod(Integer x) {
		System.out.println("Inside object");
	}
	
	public static void main(String[] args) {
		
		/*
		 * a wrapper class, will wrap around primitive date types
		 * 
		 * boolean -> Boolean
		 * int -> Integer
		 * char -> Character
		 * byte -> Byte
		 * short -> Short
		 * long -> Long
		 * double -> Double
		 * 
		 * why?
		 * 		Objects have methods associated with them
		 * 		Data structures like the Collection API
		 */
		
		int i = 5;
		
		//boxing
		Integer j = new Integer(5);
		//autoboxing where Java will box primitive into Object
		Integer k = 3;
		//unboxing
		int l = j;
		
		System.out.println(Integer.MAX_VALUE); //if capitalized, is static final variable!
		System.out.println(Long.MAX_VALUE);
	}

}
