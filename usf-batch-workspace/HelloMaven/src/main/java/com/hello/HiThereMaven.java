package com.hello;

import com.calculator.Calculator;

public class HiThereMaven {
	
	/*
	 * What is Maven?
	 * 		Maven is a dependency manager, it allows us to intergrate external libraries adn packages
	 * 		(more easily).
	 * What is a dependency?
	 * 	External stand alone program that we will utilize in our project
	 */

//	public static void main(String[] args) {
//		System.out.println("Hi Maven!");
//
//	}
	
	public static void main(String[] args) {
		
		System.out.println(Calculator.add(2, 3) == 5);
	}

}
