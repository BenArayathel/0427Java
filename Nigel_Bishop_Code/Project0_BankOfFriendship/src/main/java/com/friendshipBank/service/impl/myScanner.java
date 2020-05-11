package com.friendshipBank.service.impl;

import java.util.Scanner;

public class myScanner 
{
	static Scanner input = new Scanner(System.in);
	
	public static int UserInput_Int() {
		int userInput;
		userInput = input.nextInt();
		return userInput;
	}
	
	public static String UserInput_LongString() {
		String userInput;
		userInput = input.next();
		userInput += input.nextLine();
		return userInput;
	}
	
	public static String UserInput_String() {
		String userInput;
		userInput = input.next();
		return userInput;
	}

}
