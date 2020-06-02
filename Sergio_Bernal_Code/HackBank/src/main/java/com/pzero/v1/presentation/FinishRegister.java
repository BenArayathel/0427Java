package com.pzero.v1.presentation;

import java.util.Scanner;

import com.pzero.v1.Main;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.services.account.AccountService;
import com.pzero.v1.business.services.account.AccountServiceImpl;
import com.pzero.v1.business.services.user.UserService;
import com.pzero.v1.business.services.user.UserServiceImpl;
import com.pzero.v1.persistence.models.User;

public class FinishRegister {
	
	final static AccountService accountSrv = new AccountServiceImpl();
	final static UserService userSrv = new UserServiceImpl();

	public static void openForm(Scanner sc) {
		User user = new User();
		String rePwd = null;
		String accountNumber = null;
		Main.loggy.info("Welcome to Open an Account with us.");
		Main.loggy.info("Enter the folling information to finish the registration.\n");
		Main.loggy.info("--- Account Number:");
		try {
			accountNumber = sc.nextLine();
			Main.loggy.info("--- Email:");
			user.setEmail(sc.nextLine());
			Main.loggy.info("--- Password:");
			user.setPassword(sc.nextLine());
			Main.loggy.info("--- Re - Password:");
			rePwd = sc.nextLine();
			user.setUserType("Customer");
			if(!rePwd.equals(user.getPassword())) {
				Main.loggy.info("Your Password and Your Re-Password don't match. Try again.");
			}else {
				Main.loggy.info("\nIs it the information correct?");
				String choice = WindowAccept.openWindow(sc);
				if (choice.equals("1")) {
					String res = accountSrv.getPersonAccountById(accountNumber);
					if (!res.equals(null)) {
						user.setPersonId(res);
						User iUser = userSrv.createUser(user);
						if (iUser != null) {
							Main.sUser.iUser = iUser;
							LoginForm.openForm(sc);
						}else {
							Main.loggy.info("The user can not be created, please try again.");
						}
					}else {
						Main.loggy.info("The Account was not found.");
					}
				}else if (choice.equals("3")) {
					Main.adminMenu(sc);
				}
			}
		} catch (NumberFormatException e) {
			Main.loggy.info("The entered account number is not valid.");
		} catch (BusinessException e) {
			Main.loggy.info(e.getMessage()+"\n");
		}
		
	}
	
	
}
