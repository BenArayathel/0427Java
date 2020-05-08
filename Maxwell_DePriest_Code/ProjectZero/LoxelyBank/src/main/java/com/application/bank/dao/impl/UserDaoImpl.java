package com.application.bank.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.application.bank.dao.UserDao;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;

public class UserDaoImpl implements UserDao {
	private static String x = "ccpojkviae8q";
	final static Logger loggy = Logger.getLogger(User.class);
	private static String url =
			"jdbc:oracle:thin:@database-1." + x + ".us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "madmax9242";
	private static String password = "jasonbourne";
	
	public UserDaoImpl() {
		
	}

	@Override
	public User insertUser(User u) throws BusinessException {
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
		
		return u;
		
	}

	@Override
	public void updateUser(String userEmail, String columnName, String newAttribute) throws BusinessException{
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			//String userEmail = u.getEmail();
			PreparedStatement ps = conn.prepareStatement("UPDATE bankuser SET ? = ? WHERE email = ?");
//			ps.setString(0, columnName);
//			ps.setString(1, newAttribute);
//			ps.setString(2, userEmail);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			loggy.warn("Caught SQLException");
			loggy.info(userEmail + " " + password + " " + username);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		
	}

	@Override
	public User selectUserByEmail(String uEmail) throws BusinessException{
		User u = new User();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE email = '" + uEmail + "'");
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM bankuser WHERE email = ?");
			ps2.setString(1, uEmail);
			//loggy.info(ps);
			ResultSet rs = ps2.executeQuery();
			loggy.info(rs);
			while(rs.next()) {
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPhoneNumber(Integer.toString(rs.getInt(4)));
				u.setPassword(rs.getString(5));
				u.setStatus(rs.getString(6));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.warn("Caught SQLException");
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return u;
	}
	
	@Override
	public User selectUserByColumnName(String cName, String cValue) throws BusinessException {
		User u = new User();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE " + cName + " = '" + cValue + "'");
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM bankuser WHERE email = 'henry@email.com'");
			//ps.setString(0, uEmail);
			//loggy.info(ps);
			ResultSet rs = ps.executeQuery();
			loggy.info(rs);
			while(rs.next()) {
				
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPhoneNumber(Integer.toString(rs.getInt(4)));
				u.setPassword(rs.getString(5));
				u.setStatus(rs.getString(6));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.warn("Caught SQLException");
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return u;
	}

	@Override
	public List<User> selectAllUsers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser() throws BusinessException{
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
