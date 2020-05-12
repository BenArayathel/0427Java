package com.hackbank.presentation;

import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.authentication.AuthenticationSercviceImplementation;
import com.hackbank.business.services.authentication.AuthenticationService;

public class LoginForm {
	
	final static AuthenticationService auth = new AuthenticationSercviceImplementation();

	public static void openForm(Scanner sc) {
		String inpEmail = null;
		String inpPwd = null;
		Main.loggy.info("\n--- Login Form ---");
		Main.loggy.info("--- Enter your Email and Password:");
		Main.loggy.info("--- Email:");
		inpEmail = sc.nextLine();
		Main.loggy.info("--- Password");
		inpPwd = sc.nextLine();
		try {
			if(auth.login(inpEmail, inpPwd).equals("Employee")) {
//			if(auth.login("employee@hackbank.com", "Passw0rd$").equals("Employee")) {
				Main.adminMenu(sc);
			}else{
				Main.customerMenu(sc);
			}
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage()+"\n");
		}
	}
	
}
