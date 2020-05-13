package com.RevBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BankWith implements Selection {

	@Override
	public Selection doSelection(Scanner scan, Application app) throws SQLException {
		UserData info = new UserData();
		
		Scanner strs=new Scanner(System.in);
	    try {
	  System.out.println("Enter Custid:");
	  info.setCust_id(strs.nextLine());
	  System.out.println("Enter Last Name:");
	  info.setLname(strs.nextLine());
	  System.out.println("Enter Deposit Amount");
	  info.setWithdr( strs.nextLine());
		
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/revbankpz", "root", "Vincent");
	  PreparedStatement pst = con.prepareStatement("insert into withdraw(cust_id,lname, withdr) values(?,?,?)");
	   pst.setString(1, info.getCust_id());
	   pst.setString(2, info.getLname());
	   pst.setString(3, info.getWithdr());
	   int i = pst.executeUpdate();
	 if(i!=0){
	   System.out.println("Confirm");
	 }
	 else{
	   System.out.println("Errors  ");
	 }
	    }
	catch (Exception e){
	System.out.println(e);
	}
		return null;
	}
	}	
		
	
