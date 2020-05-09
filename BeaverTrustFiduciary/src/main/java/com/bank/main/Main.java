package com.bank.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.User;
import com.bank.presentation.WelcomeView;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.service_interface.UserServiceInterface;
import com.bank.tools.BankException;

public class Main {
	
	final static Logger myLog = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		
     	// central hub for behavior
		// should have very few commands, just start the ball rolling
	
//		if (myLog.isInfoEnabled()) {
//			myLog.info("Hey, it workith!");			
//		}
//				
//		myLog.warn("HEY!");
		
	     
	    //my logger only works if my call to this object/method comes after the logging stuff!
//		WelcomeView.welcome();
	
	    //using new structure, for now calling all the way to DAO from presentation layer
		//this is just a temporary scanner


//		UserServiceInterface usi = new UserServiceImplementation();
//		User myUser = new User();
//		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("enter new username");
//		myUser.setUsername(scan.nextLine());
//		System.out.println("enter new password");
//		myUser.setPassword(scan.nextLine());
//		
//		try {
//			myUser = usi.createUser(myUser);
//		} catch (BankException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		UserServiceImplementation usi = new UserServiceImplementation();
		
		try {
			List<User> users = usi.listUsers();
//			for (User u : users) {
//				System.out.println(u);
//			}
		} catch (BankException e) {
			e.printStackTrace();
		}
		
//		List<Planet> planets = pDaoImpl.selectAllPlanets();
//		for(Planet i: planets) {
//			System.out.println(i);
//		}
		
		
	

	}

}
