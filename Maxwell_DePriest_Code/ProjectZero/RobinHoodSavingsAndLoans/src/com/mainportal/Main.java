package com.mainportal;

import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Lobby l = new Lobby();
		//l.createFile("./customerRecords.txt");
		System.out.println("Welcome. Would like to sign-in or register a new account? \n\nPress 1 to sign-in, and 2 to got to the registration page.");
		String direction = sc.nextLine();
		if (direction.equalsIgnoreCase("1")) {
			System.out.println();
			signIn(sc, l);
		}
		else if (direction.equalsIgnoreCase("2")) {
			l.registerNewUser();
			
		}
		
	}
	
	public static void signIn(Scanner sc, Lobby lob) {
		User person = null;
		System.out.println("Please enter your email address or type quit to Quit");
		String em = sc.nextLine();
		if (em.equalsIgnoreCase("quit")) {
			System.exit(0);
		}
		System.out.println("Please enter your password");
		String pass = sc.nextLine();
		lob.signIn(em, pass, person);
		

		
		
	}

	
}