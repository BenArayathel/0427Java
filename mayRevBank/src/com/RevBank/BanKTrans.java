package com.RevBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BanKTrans implements Selection {

	@Override
	public Selection doSelection(Scanner scan, Application app) throws SQLException {
		
		Scanner strs=new Scanner(System.in);
	    try {
	  System.out.println("Enter Custid:");
	  String cust_id =strs.nextLine();
	  System.out.println("Enter Last Name:");
	  String lname =strs.nextLine();
	  System.out.println("Enter Deposit Amount");
	  String deposit = strs.nextLine();
		
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/revbankpz", "root", "Vincent");
	  PreparedStatement pst = con.prepareStatement("insert into deposits(cust_id,lname,deposit) values(?,?,?)");
	   pst.setString(1, cust_id);
	   pst.setString(2, lname);
	   pst.setString(3, deposit );
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
		
		
		
		
		
		
