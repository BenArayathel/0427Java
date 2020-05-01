package com.hello;

import com.calculator.Calculator;

public class HiThereMaven {
	
	/*
	 * What is Maven?
	 * 		Maven is a dependency manager (yay!).
	 * 		It allows us to implement external libraries and packages (woot).
	 * What is a dependency?
	 * 		An external standalone program that we will utilize in our project
	 * 
	 */

	public static void main(String[] args) {
		System.out.println("Hi Maven!");
		System.out.println(Calculator.add(2, 3)==5);
		//^^^ We don't do this and instead do unit tests, because it's intractable to do this for every method of every class
		// in an enterprise application
	}

}
