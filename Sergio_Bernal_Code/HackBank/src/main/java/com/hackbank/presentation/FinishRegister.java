package com.hackbank.presentation;

import java.util.Scanner;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.business.services.account.AccountService;
import com.hackbank.business.services.account.AccountServiceImpl;
import com.hackbank.business.services.user.UserService;
import com.hackbank.business.services.user.UserServiceImpl;
import com.hackbank.persistence.models.User;

public class FinishRegister {
	
	final static AccountService accountSrv = new AccountServiceImpl();
	final static UserService userSrv = new UserServiceImpl();

	public static void openForm(Scanner sc) {
		User user = new User();
		String rePwd = null;
		String accountNumber = null;
		Main.loggy.info("Welcome to Open an Account with us.");
		Main.loggy.info("Enter the folling information to finish the register.\n");
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
				String choice = WindowAccept.openWindow(sc);
				if (choice.equals("1")) {
					String res = accountSrv.getPersonAccountById(accountNumber);
					if (!res.equals(null)) {
						user.setPersonId(res);
						if (userSrv.createUser(user) != null) {
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
