package com.mainportal;

import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Lobby l = new Lobby();
		//l.createFile("./customerRecords.txt");
		System.out.println("Welcome to Loxely Savings and Loans.");
		l.signIn();
		//signIn(sc, l);
		
	}
	
//	public static void signIn(Scanner sc, Lobby lob) {
//		//User person = null;
//		System.out.println("Please enter your email address or type quit to Quit");
//		String em = sc.nextLine();
//		if (em.equalsIgnoreCase("quit")) {
//			System.exit(0);
//		}
//		System.out.println("Please enter your password");
//		String pass = sc.nextLine();
//		lob.signIn(em, pass);
//
//	}

	
}