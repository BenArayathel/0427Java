package com.example.scanner;

import java.util.Scanner;

public class ScannerExample {
	
	public static void myMethod(Scanner sc) {
		
		
		
		System.out.println("Please input anything for a: ");
		
		if(sc.hasNext()) {
			String a = sc.nextLine();
			
			System.out.println("This is you variable: " + a);
			
			System.out.println("Please input an integer b:");
		}
		
		
		
//		int b = sc.nextInt();
		String si = sc.nextLine();
		int b = Integer.parseInt(si);
		System.out.println("This is your variable: " + b);
		
		try{
			System.out.println("Please input a double c:");
			String s = sc.nextLine();
			double c = Double.parseDouble(s);
			System.out.println("Here's your variable: " + s);
		} catch (NumberFormatException e) {
			System.out.println("Invalid format");
			//if(a == null){ //do this}
			myMethod(sc);
		} finally {
			System.out.println("Thanks for taking part!!!");
		}
		
//		sc.close();
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		myMethod(new Scanner(System.in)); //Here we're creating a brand new Scanner object and passing it in 
//		myMethod(new Scanner(System.in));
		
		myMethod(sc); //Here we're passing in the same scanner object. 
		myMethod(sc);
		
		sc.close();
	}

}
