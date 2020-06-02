package com.pzero.v1.presentation;

import java.util.Scanner;

import com.pzero.v1.Main;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.authentication.AuthenticationSercviceImplementation;
import com.pzero.v1.business.services.authentication.AuthenticationService;
import com.pzero.v1.persistence.models.User;

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
				Main.loggy.debug(user.getEmail() + " - Login");
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
