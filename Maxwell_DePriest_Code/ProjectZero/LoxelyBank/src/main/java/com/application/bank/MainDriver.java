package com.application.bank;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import com.application.bank.models.Account;
import com.application.bank.dao.impl.AccountDaoImpl;
import com.application.bank.services.impl.UserServiceImpl;
import com.application.bank.services.impl.AccountServiceImpl;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainDriver {
	final static Logger loggy = Logger.getLogger(User.class);

	public static void main(String[] args) {
		
		UserDaoImpl uDI = new UserDaoImpl();
		AccountDaoImpl aDI = new AccountDaoImpl();
		AccountServiceImpl aSI = new AccountServiceImpl();
		UserServiceImpl uSI = new UserServiceImpl();
		User currentUser = new User();
		
		Scanner sc = new Scanner(System.in);
		loggy.info("Welcome to Loxely Bank, where we save for the rich and loan to the poor.");
		loggy.info("Please enter your email");
		String em = sc.nextLine();
		loggy.info("Please enter your password");
		String pw = sc.nextLine();
		try {
			if(uSI.userLogin(em, pw)) {
				currentUser = uSI.setCurrentUser(em);
				loggy.info("Hello, " + currentUser.getName());	
			}
		} catch (BusinessException e1) {
			loggy.info(e1.getMessage());
			e1.printStackTrace();
		}
		
//		try {
//			uSI.withdrawMoney("checkingBalance", "3000.00", currentUser);
//		} catch (BusinessException e1) {
//			loggy.info("Error. Please try again");
//			loggy.error("Exception caught while trying to withdraw money");
//			e1.printStackTrace();
//		}
		
		try {
			uSI.depositMoney("savingsBalance", "150.00", currentUser);
		} catch (BusinessException e1) {
			loggy.error("Exception thrown during depositMoney");
			e1.printStackTrace();
		}
		

		
//		try {
//			String chkB = uSI.checkSavingsBalance(currentUser);
//			loggy.info("You currently have $" + chkB + " in your savings account.");
//		} catch (BusinessException e1) {
//			loggy.error("Error while checking checkingBalance");
//			loggy.info("Something went wrong. Please try again");
//		}
//		
//		uSI.activatePendingAccounts(currentUser);
		
		
//		try {
//			uSI.registerNewUser();           
//		} catch (BusinessException e1) {
//			loggy.info(e1.getMessage());
//		}
		
//		try {
//			List<User> userList = uDI.selectAllUsers();
//		} catch (BusinessException e1) {
//			loggy.error("Internal Error. Please contact the SYSADMIN");
//		}
		
//		try {
//			u3 = uDI.selectUserByColumnName("email", "ben@email.com");
//		} catch (BusinessException e) {
//			loggy.info("Internal Error. Please try again or contact your SYSADMIN");
//			e.printStackTrace();
//		}
//		
//		System.out.println(u3);
		
//		try {
//			uDI.updateUser("serenity@email.com", "name", "Malcom Reynolds");
//		} catch (BusinessException e) {
//			loggy.warn("We couldn't seem to find that account. Please try again");
//			e.printStackTrace();
//		}
		
//		try {
//			uDI.deleteAllUsers();
//		} catch (BusinessException e) {
//			loggy.info("The rows still exist because " + e);
//		}
		
//		try {
//			uDI.deleteUser(u3.getEmail());
//		} catch (BusinessException e) {
//			loggy.warn("The row still exist because " + e);
//		}	
		
//		try {
//			Account a5 = aDI.selectAccountByEmail("serenity@email.com");
//		} catch (BusinessException e) {
//			loggy.error("BusinessException caught");
//			loggy.info("Couldn't select user. Please try again.");
//			e.printStackTrace();
//		}
		
//		try {
//			List<Account> accountList = aDI.selectAllAccounts();
//		} catch (BusinessException e) {
//			loggy.warn("Internal Error. Please try again or contact your SYSADMIN");
//			e.printStackTrace();
//		}
		
//		try {
//			aDI.deleteAllAccounts();
//		} catch (BusinessException e) {
//			loggy.warn("The records remain, peasant");
//			e.printStackTrace();
//		}
		
//		try {
//			aDI.deleteAccount(u3.getEmail());
//		} catch (BusinessException e) {
//			loggy.info("Internal Error. Please try again or contact your SYSADMIN");
//			e.printStackTrace();
//		}
		
//		try {
//			aDI.updateAccount("leia@email.com", "active", "true");
//		} catch (BusinessException e) {
//			
//			loggy.info("Internal Error. Please try again or contact your SYSADMIN");
//			e.printStackTrace();
//		}
		
//		Account a2 = new Account();
//		a2.setCheckingAccountNumber("985751");
//		a2.setSavingsAccountNumber("7847831");
//		a2.setCheckingBalance("5000.00");
//		a2.setSavingsBalance("2500.00");
//		a2.setActive("false");
//		a2.setEmail("leia@email.com");
//		
//		Account a3 = new Account();
//		a3.setCheckingAccountNumber("1296011");
//		a3.setSavingsAccountNumber("1910001");
//		a3.setCheckingBalance("150.00");
//		a3.setSavingsBalance("2050.00");
//		a3.setActive("false");
//		a3.setEmail("han@email.com");
//		
//		Account a4 = new Account();
//		a4.setCheckingAccountNumber("1296694");
//		a4.setSavingsAccountNumber("1910932");
//		a4.setCheckingBalance("550.00");
//		a4.setSavingsBalance("100.00");
//		a4.setActive("false");
//		a4.setEmail("kirk@email.com");
//		try {
//			aDI.insertAccount(a4);
//			aDI.insertAccount(a2);
//			aDI.insertAccount(a3);
//		} catch (BusinessException e) {
//			loggy.error("Internal Error. Please contact the SYSADMIN");
//			e.printStackTrace();
//		}
		sc.close();
	

	}// End of main

}// End of class




