package com.hackbank.presentation;

import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.authentication.AuthenticationSercviceImplementation;
import com.hackbank.business.services.authentication.AuthenticationService;
import com.hackbank.persistence.models.User;

public class LoginForm {
	
	final static AuthenticationService auth = new AuthenticationSercviceImplementation();

	public static void openForm(Scanner sc) {
		String inpEmail = null;
		String inpPwd = null;
		User user = null;
		Main.loggy.info("\n--- Login Form ---");
		Main.loggy.info("--- Enter your Email and Password:");
		Main.loggy.info("--- Email:");
		inpEmail = sc.nextLine();
		Main.loggy.info("--- Password");
		inpPwd = sc.nextLine();
		try {
			user = auth.login(inpEmail, inpPwd);
			if (user != null) {
				Main.sUser.iUser = user;
				if(user.getUserType().equals("Employee")) {
	//			if(auth.login("employee@hackbank.com", "Pwd0123").equals("Employee")) {
					Main.adminMenu(sc);
				}else{
					Main.customerMenu(sc);
				}
			}
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage()+"\n");
		}
	}
	
}
