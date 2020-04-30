package com.example.scanners;

import java.util.Scanner;

public class ScannerExample {
	
	public static void myMethod(Scanner sc) {
		System.out.println("Please input anything for a:");
		
//		// Could be useful for resolving some errors
//		if (sc.hasNext()) {
//			String a = sc.nextLine();
//			System.out.println("This is your variable "+a);
//		}
		String a = sc.nextLine();
		System.out.println("This is your variable "+a);
		
		System.out.println("Please input the integer b:");
		// will only accept input that is a valid integer
//		int b = sc.nextInt();
		String si = sc.nextLine();
		int b = Integer.parseInt(si);
		System.out.println("This is your variable "+b);
		
		/* However, Scanner can be finiky :(
		 * Top avoid most errors, just use the nextLine() method
		 * to accept anything, then check the type internally
		 */
		
		System.out.println("Please input a double c:");
		try {
			String s = sc.nextLine();
			double c = Double.parseDouble(s);
			System.out.println("Here's your variable "+c);
		} catch (NumberFormatException e) {
			System.out.println("Invalid format");
			ScannerExample.myMethod(sc);
		}
	}
	
	public static void main(String[] args) {
		
		// How to read input in from the console: use Scanner
		Scanner sc = new Scanner(System.in);

		ScannerExample.myMethod(sc);
		ScannerExample.myMethod(sc);
		
		sc.close();
	}

}
