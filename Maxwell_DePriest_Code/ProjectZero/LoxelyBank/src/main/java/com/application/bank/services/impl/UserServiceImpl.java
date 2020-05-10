package com.application.bank.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import com.application.bank.models.Account;
import com.application.bank.services.UserService;
import com.application.bank.dao.impl.AccountDaoImpl;
import com.application.bank.dao.impl.UserDaoImpl;

public class UserServiceImpl implements UserService {
	final static Logger loggy = Logger.getLogger(User.class);
	AccountDaoImpl aDI = new AccountDaoImpl();
	UserDaoImpl uDI = new UserDaoImpl();

	public UserServiceImpl() {
		
	}
	
	@Override
	public User registerNewUser() throws BusinessException {
		User newUser = new User();
		Scanner sc = new Scanner(System.in);
		loggy.info("Please enter your first and last names");
		newUser.setName(sc.nextLine());
		loggy.info("Please enter your email");
		newUser.setEmail(sc.nextLine());
		loggy.info("Please enter your phone number. Ex 3048675309");
		newUser.setPhoneNumber(sc.nextLine());
		loggy.info("Create a password");
		newUser.setPassword(sc.nextLine());
		newUser.setStatus("customer");
		
		if (!isValidName(newUser.getName())) {
			loggy.info("Error. Please use only letters in the name field. Please try again.");
		}
		else if (!isValidPhoneNumber(newUser.getPhoneNumber())) {
			loggy.info("Error. Please input your phone number with 10 digits. Ex. 1234567890");
		}
		else {
			uDI.insertUser(newUser);
			
			loggy.debug("new customer account created with email " + newUser.getEmail());
		}
		sc.close();
		return newUser;
	}// end of registerNewUser
	
	@Override
	public boolean userLogin(String email, String password) throws BusinessException {
		User u = uDI.selectUserByEmail(email);
		if (u != null) {
			try {
				if (u.getPassword().equals(password)) {
					loggy.debug("Passwords match");
					return true;
				}
			} catch (NullPointerException e) {
				return false;
			}
			
		}
		loggy.error("Email or password could not be found matched in DB");
		loggy.info("Either your email or password is incorrect. Please try again.");
		return false;
	} // end of userLogin
	
	@Override
	public User setCurrentUser(String email) throws BusinessException {
		return uDI.selectUserByEmail(email);
	}
	
	@Override
	public void signUpForAccount(String email) throws BusinessException {
		Scanner sc = new Scanner(System.in);
		boolean verified = true;
		loggy.info("How much would you like to put into your checking account?");
		String checkingMoney = sc.nextLine();
		if (!isValidDeposit(checkingMoney)) {
			verified = false;
			
		}
		else {
			String newSavingsNum = Integer.toString(generateAccountNumber());
			String newCheckingNum = Integer.toString(generateAccountNumber());
			aDI.insertAccount(new Account("", newSavingsNum, newCheckingNum, checkingMoney, "0.00", "false", email));
		}	
	} // End of signUpForAccount
	
	public void activatePendingAccounts(User u) {
		Scanner sc = new Scanner(System.in);
		List<Account> notActiveAccounts= new ArrayList<>();
		if(isEmployee(u)) {
			try {
				notActiveAccounts = aDI.selectAllAccountsByColumnName("active", "false");
			} catch (BusinessException e) {
				loggy.info(e.getMessage());
				loggy.error("Could not select all accounts by column name");
			}
			
			for (Account a : notActiveAccounts) {
				String printOut = "Email- " + a.getEmail() + ", Checking Account Number- " + a.getCheckingAccountNumber() +
						", Savings Account Number " + a.getSavingsAccountNumber() + ", Account Active- " + a.getActive();
				loggy.info(printOut);
			}
			
			loggy.info("Would you like to activate all pending accounts? Y or N");
			String answer = sc.next();
			if (answer.equalsIgnoreCase("y")) {
				for (Account pAccount : notActiveAccounts) {
					try {
						aDI.updateAccount(pAccount.getEmail(), "active", "true");
					} catch (BusinessException e) {
						loggy.error("Error while activating non-active accounts");
						e.printStackTrace();
					}
				}
				loggy.debug("Activating all pending accounts");
			}
			else {
				loggy.debug("Employee opted to not activate all pending accounts");
			}
			
		}
		else {
			loggy.info("You must be an employee to continue");
		}
	}// End of activatePendingAccounts
	

//	@Override
//	public User updateProfile(User u) throws BusinessException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String checkCheckingBalance(User u) throws BusinessException {
		return aDI.selectAccountByEmail(u.getEmail()).getCheckingBalance();
	}

	@Override
	public String checkSavingsBalance(User u) throws BusinessException {
		return aDI.selectAccountByEmail(u.getEmail()).getSavingsBalance();
	}

	@Override
	public void removeUserProfile(User u) throws BusinessException {
		String uEmail = u.getEmail();
		uDI.deleteUser(uEmail);
		aDI.deleteAccount(uEmail);
		
	}
	
	public static int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}
	
	private boolean isValidName(String testName) {
		if (testName.matches("[a-zA-Z ]{2,20}")) {
			loggy.debug("isValidName passed");
			return true;
		}
		loggy.error("isValidName failed");
		return false;
	}
	
	private boolean isValidPhoneNumber(String testNumber) {
		if (testNumber.matches("[0-9]{10}")) {
			loggy.debug("isValidPhoneNumber passed");
			return true;
		}
		loggy.error("isValidPhoneNumber failed");
		return false;
	}
	
	private boolean isValidDeposit(String testMoney) {
		if(testMoney.matches("[0-9.]{1,7}") && Double.parseDouble(testMoney) > 0 && Double.parseDouble(testMoney) <= 1000.0) {
			loggy.debug("isValidDeposit passed");
			return true;
		}
		else {
			loggy.info("Error. Please try again. Amount cannot be larger than $1000");
			loggy.error("isValidDeposit failed");
			return false;
		}
	}
	private boolean isEmployee(User u) {
		return u.getStatus().equals("employee") ? true : false;
	}

	@Override
	public void depositMoney(String whichAccount, String amount, User u) throws BusinessException {
		Account userAccount = aDI.selectAccountByEmail(u.getEmail());
		double currentBalance = 0.0;
		try {
			loggy.info("whichAccount- " + whichAccount + " amount- " + amount + " currentUser email- " + u.getEmail());
			
			//currentBalance = whichAccount.equalsIgnoreCase("checkingBalance") ? Double.parseDouble(userAccount.getCheckingBalance()) : Double.parseDouble(userAccount.getSavingsBalance());
			if (whichAccount.equalsIgnoreCase("checkingBalance")) {
				currentBalance = Double.parseDouble(userAccount.getCheckingBalance());
			}
			else {
				currentBalance = Double.parseDouble(userAccount.getSavingsBalance());
			}
			loggy.info("Current balance- " + currentBalance);
			if(amount.matches("[0-9.]{1,7}"))  {
				currentBalance += Double.parseDouble(amount);
				loggy.info("Your new balance is " + currentBalance);
				aDI.updateAccount(u.getEmail(), whichAccount, (currentBalance + ""));
			}
		}catch(NullPointerException e) {
			loggy.error("depositMoney threw NullPointException e- " + e);
			e.printStackTrace();
			loggy.info("Error. Please try again");
		}
	} // End of depositMoney()

	@Override
	public void withdrawMoney(String whichAccount, String amount, User u) throws BusinessException {
		double currentBalance = 0.00;
		Account userAccount = aDI.selectAccountByEmail(u.getEmail());
		try {
			currentBalance = whichAccount.equalsIgnoreCase("checkingBalance") ? Double.parseDouble(userAccount.getCheckingBalance()) : Double.parseDouble(userAccount.getSavingsBalance());
			loggy.info("Current balance is " + currentBalance);
			if(currentBalance - Double.parseDouble(amount) > 0)  {
				currentBalance -= Double.parseDouble(amount);
				loggy.info("Here's $" + amount);
				loggy.info("Your new balance is $" + currentBalance);
				loggy.debug(amount + " was taken from account");
				aDI.updateAccount(u.getEmail(), whichAccount, (currentBalance + ""));
			}
			else {
				loggy.info("Error. Insufficient funds. Your current balance is $" + currentBalance);
				loggy.error("Current balance was smaller than requested amount during withdrawl");
			}
		} catch(NullPointerException e){
			loggy.error("Caught NullPointerException at withdrawMoney()");
			loggy.info("Error. Please try again");
		}
		
	}// end of withdrawMoney()
	
	public static String passwordEncryption(String pw) {
	loggy.info("Encrypting password");
	StringBuilder newPassword = new StringBuilder();
	String original = "abcdefghijklmnopqrstuvwxyz0987654321";
	String alternate = "1234567890zyxwvutsrqponmlkjihgfedcba";
	String[] arr = alternate.split("");
	String[] wordArray = pw.toLowerCase().split("");
	int tempIndex;
	for(int k = 0; k < (wordArray.length); k++) {
		String tempLetter = wordArray[k];
		tempIndex = original.indexOf(tempLetter); 
		newPassword.append(arr[tempIndex]);
	}
	return newPassword.toString();
	
	}

}// End of class
