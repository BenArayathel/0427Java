package com.application.bank;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.application.bank.Lobby;

import java.util.Scanner;

public class Main {
	final static Logger loggy = Logger.getLogger(Main.class);	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Lobby l = new Lobby();
		
		//l.createFile("./customerRecords.txt");
		System.out.println("Welcome to Loxely Savings and Loans.");
		loggy.info("Main function running");
		
		l.signIn();
		//signIn(sc, l);
		
	}
	
	
	
//	loggy.info("THIS IS AN INFO UPDATE!!!!");
//	loggy.warn("WARNING AGAIN!");
//	
//	if(loggy.isInfoEnabled()) {
//		loggy.warn("THIS IS A WARNING!!!");
//	}
//	
//	loggy.error("This is an error", new IndexOutOfBoundsException());
//	loggy.fatal("This is fatal!");
	
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



