package com.mainportal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String filename = "./customerRecords.txt";
		signIn(sc);
	}
	
	public static void signIn(Scanner sc) {
		Lobby l = new Lobby();
		l.createFile("./employeesInfo");
		User person = null;
		System.out.println("Please enter your email address or type quit to Quit");
		String em = sc.nextLine();
		if (em.equalsIgnoreCase("quit")) {
			System.exit(0);
		}
		System.out.println("Please enter your password");
		String pass = sc.nextLine();
		l.signIn(em, pass, person);
		

		
		
	}

	
}