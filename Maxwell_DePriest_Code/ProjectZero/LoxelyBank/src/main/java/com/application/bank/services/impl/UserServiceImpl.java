package com.application.bank.services.impl;

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
		
		boolean verified = true;
		if (!isValidName(newUser.getName())) {
			verified = false;
		}
		else if (!isValidPhoneNumber(newUser.getPhoneNumber())) {
			verified = false;
		}
		else {
			uDI.insertUser(newUser);
			
			loggy.debug("new customer account created with email " + newUser.getEmail());
		}

		
		
		
		
//		loggy.info("How much would you like to put into your checking account?");
//		String checkingMoney = sc.nextLine();
//		String newSavingsNum = generateAccountNumber();
//		String newCheckingNum = generateAccountNumber();
//		Account act = new Account();
		

		
		sc.close();
		return null;
	}
	
	@Override
	public User selectCurrentUser(String email) throws BusinessException {
		return null;
	}
	
	@Override
	public void signUpForAccount() throws BusinessException {
		
	}

	@Override
	public User updateProfile(User u) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkCheckingBalance() throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkSavingsBalance() throws BusinessException {
		// TODO Auto-generated method stub
		
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
	
	
	
	
//	public static User newCustomerRegistration() {

//	
//	return c;
//}
	
//	public void activateCustomerAccounts(ArrayList<Customer> allRecords) {
//
//		for(Customer record : allRecords) {
//			if(!record.getAccount().getActive()) {
//				record.getAccount().setActive(true);
//			}
//			loggy.info("Customer accounts activated");
//			updateCustomerInfo(allRecords);
//			
//		}// end for loop
//		
//		
//
//	}// end activateUserAccounts()
	
	private boolean isValidName(String testName) {
		if (testName.matches("[a-zA-Z ]{2,20}")) {
			loggy.debug("Name passed validation check");
			return true;
		}
		loggy.error("Name failed validation check");
		return false;
	}
	private boolean isValidPhoneNumber(String testNumber) {
		if (testNumber.matches("[0-9]{10}")) {
			loggy.debug("Phone number passed validation check");
			return true;
		}
		loggy.error("Phone number failed validation check");
		return false;
	}
	
//	private boolean isValidEmail(String testEmail) {
//		Pattern pattern = Pattern.compile(".com");
//		Matcher matcher = pattern.matcher(testEmail);
//		if (matcher.find()) {
//			loggy.debug("Found '.com' in the email submission");
//			System.out.println("Found the dot com");
//			return true;
//		}
//		loggy.error("Email failed validation test");
//		return false;
//	}

}
