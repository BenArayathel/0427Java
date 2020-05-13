package com.example.operations;

import java.util.Scanner;

public class UserInput {
	static Scanner data = new Scanner(System.in);
	
	public static String getInput()
	{
		return data.nextLine();
	}
}
