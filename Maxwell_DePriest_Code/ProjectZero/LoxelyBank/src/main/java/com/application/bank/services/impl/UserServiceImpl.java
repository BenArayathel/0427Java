package com.application.bank.services.impl;

import org.apache.log4j.Logger;

import com.application.bank.models.User;

public class UserServiceImpl {
	final static Logger loggy = Logger.getLogger(User.class);

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
	
//	public static User newCustomerRegistration() {
//	Scanner sc = new Scanner(System.in);
//	System.out.println("Please enter your full name");
//	String n = sc.nextLine();
//	System.out.println("Please enter your email");
//	String e = sc.nextLine();
//	System.out.println("Please enter your phone number. Ex 3048675309");
//	String pN = sc.nextLine();
//	System.out.println("Create a password");
//	String pW = sc.nextLine();
//	System.out.println("How much would you like to put into your checking account?");
//	String checkingMoney = sc.nextLine();
//	int newSavingsNum = generateAccountNumber();
//	int newCheckingNum = generateAccountNumber();
//	Account act = new Account(newSavingsNum, newCheckingNum, 0.00, Integer.parseInt(checkingMoney), e);
//	loggy.info("new customer and associated account created");
//	System.out.println("Created new object- " + c );
//	System.out.println();
//	//Lobby.addNewCustomer("./customerRecords.txt", c);
//	
//	sc.close();
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

}
