package com.application.bank;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import com.application.bank.models.Account;
import com.application.bank.dao.impl.AccountDaoImpl;

import org.apache.log4j.Logger;

public class MainDriver {
	final static Logger loggy = Logger.getLogger(User.class);

	public static void main(String[] args) {
		UserDaoImpl uDI = new UserDaoImpl();
		AccountDaoImpl aDI = new AccountDaoImpl();
		
		User u3 = new User();
//		u3.setName("Malcom Reynolds");
//		u3.setEmail("serenity@email.com");
//		u3.setPhoneNumber("2939495");
//		u3.setPassword("malRules");
//		u3.setStatus("customer");
//		try {
//			//uDI.insertUser(u3);
//			uDI.insertUser(u3);
//		} catch (BusinessException e) {
//			loggy.warn("Couldn't create a new user. Please try again.");
//			e.printStackTrace();
//		}
//		try {
//			uDI.selectAllUsers();
//		} catch (BusinessException e1) {
//			loggy.error("Internal Error. Please contact the SYSADMIN");
//		}
		
//		try {
//			uDI.updateUser("vader@email.com", "name", "Anakin Skywalker");
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
//			uDI.deleteUser();
//		} catch (BusinessException e) {
//			loggy.warn("The row still exist because " + e);
//		}	
		
//		try {
//			User u5 = uDI.selectUserByEmail("max@email.com");
//			User u6 = uDI.selectUserByColumnName("name", "Max DePriest");
//			loggy.info("The name of this user is " + u6.getEmail());
//		} catch (BusinessException e) {
//			//loggy.warn("Couldn't select user. Please try again.");
//			e.printStackTrace();
//		}
		
//		try {
//			aDI.deleteAllAccounts();
//		} catch (BusinessException e) {
//			loggy.warn("The records remain, peasant");
//			e.printStackTrace();
//		}
		
		Account a2 = new Account();
		a2.setCheckingAccountNumber(987654);
		a2.setSavingsAccountNumber(7846532);
		a2.setCheckingBalance(1000.00);
		a2.setSavingsBalance(250.00);
		a2.setActive(false);
		a2.setUserEmail("serenity@email.com");
		
		try {
			aDI.insertAccount(a2);
			loggy.info("Added new account");
		} catch (BusinessException e) {
			loggy.error("Internal Error. Please contact the SYSADMIN");
			e.printStackTrace();
		}
		
		
		

		
		
		
		

	}

}




