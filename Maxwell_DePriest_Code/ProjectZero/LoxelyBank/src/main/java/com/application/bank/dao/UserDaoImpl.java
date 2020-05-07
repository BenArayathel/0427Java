package com.application.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.application.bank.Account;
import com.application.bank.models.User;

public class UserDaoImpl implements UserDao {
	private static String x = "ccpojkviae8q";
	final static Logger loggy = Logger.getLogger(User.class);

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	private static String url =
			"jdbc:oracle:thin:@database-1." + x + ".us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "madmax9242";
	private static String password = "jasonbourne";

	@Override
	public void insertUser(User u) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bankuser VALUES(?,?,?,?,?,?)");
			
			ps.setInt(1, u.getId());
			ps.setString(2, u.getName());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPhoneNumber());
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getStatus());
			
			ps.executeUpdate();
			loggy.info("Inserting new User");
			
		} catch (SQLException e) {
			loggy.warn("SQLException caught- " + e);
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUserByEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser() {
		// TODO Auto-generated method stub
		
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
	
//	public static Customer newCustomerRegistration() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Please enter your full name");
//		String n = sc.nextLine();
//		System.out.println("Please enter your email");
//		String e = sc.nextLine();
//		System.out.println("Please enter your phone number. Ex 3048675309");
//		String pN = sc.nextLine();
//		System.out.println("Create a password");
//		String pW = sc.nextLine();
//		System.out.println("How much would you like to put into your checking account?");
//		String checkingMoney = sc.nextLine();
//		int newSavingsNum = generateAccountNumber();
//		int newCheckingNum = generateAccountNumber();
//		Account act = new Account(newSavingsNum, newCheckingNum, 0.00, Integer.parseInt(checkingMoney), e);
//		Customer c = new Customer(n, e, pN, pW, act);
//		loggy.info("new customer and associated account created");
//		System.out.println("Created new object- " + c );
//		System.out.println();
//		//Lobby.addNewCustomer("./customerRecords.txt", c);
//		
//		sc.close();
//		
//		return c;
//	}
	
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
	
	public static int generateAccountNumber() {
		Random rand = new Random();
		int randomAccountNumber = rand.nextInt(100000);
		
		return randomAccountNumber;
	}

}
