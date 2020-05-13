package com.bank;

import com.data.JDBC;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//This class just makes the connection to the database and asks user to login or create a new account with a starting balance
public class Main {

	final static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		//Main.log();
		Scanner input = new Scanner(System.in);
		JDBC databaseConnection = new JDBC();		
		JDBC.databaseConnection("accountstable");					
		boolean correctSelection = false;
		
		System.out.println("Welcome to First National Bank of Dave\nYou can either log in or create an account.\n\nPlease make a selection(1 or 2):\n1: Log In\n2: Create Account");
		String inVal;
		Init init = new Init();
		while(!correctSelection) {	
			inVal = input.next();
			if(inVal.equals("1")) {
				//Go to login method and break loop
				correctSelection = true;
				init.logIn();
			}else if(inVal.equals("2")) {
				correctSelection = true;
				try {
					init.createCustomerAccount();
				} catch (SQLException e) {
					System.out.println("There was an error, contact system administrator");
				}
			}else {
				System.out.println("Please enter a valid entry either 1 or 2.");
			}
		}
		
		System.out.println("Good bye, and thank you for choosing First National Bank of Dave!");
	}
	
	
//	public static void log() {
//		log.setLevel(Level.ALL);
//		log.info("This is an update!");
//		log.setLevel(Level.FATAL);
//		
//		if(log.isInfoEnabled()) {
//			log.warn("Message");
//		}
//		
//		//Error() and fatal()
//	}

				
}
	

