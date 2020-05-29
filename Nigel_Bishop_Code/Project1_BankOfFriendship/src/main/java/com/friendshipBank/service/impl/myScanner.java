package com.friendshipBank.service.impl;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.friendshipBank.Main.MainDriver;

public class myScanner 
{
	static Scanner input = new Scanner(System.in);
	static DecimalFormat decFormat = new DecimalFormat(".##");
	
	private static boolean isValidChoice(String input) {
		boolean b = false;
		if (input.matches("[0-9]{1}")) {
			b = true;
		}
		return b;
	}
	
	private static boolean isValidNumber(String input) {
		boolean b = false;
		if (input.matches("[0-9]{1,50}")) {
			b = true;
		}
		return b;
	}
	
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
	
	public static int UserInput_menuChoice() {
		String inputString;
		int output = 0;
		inputString = input.next();
		if(isValidChoice(inputString)) 
		{
			output = Integer.parseInt(inputString);
		}
		else {
			MainDriver.SystemLog.info("SYSTEM:  INVALID INPUT (MENU CHOICE ONLY ACCEPT INTEGER INPUT (0-9))");
		}
		return output;
	}
	 

	public static String money() {
		double money = 0;
		double inputmoney = input.nextDouble();

		if(inputmoney > 1) {
			
			money = inputmoney;
			decFormat.format(money);
		}
		else {
			MainDriver.SystemLog.info("SYSTEM:  INVALID INPUT (CANNOT ACCEPT NEGATIVE OR INPUT AMOUNT LESS THAN 1)");
		}
		
		return decFormat.format(money);
	}


}
