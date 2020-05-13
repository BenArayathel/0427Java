package com.RevBank;
import java.util.logging.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class NewCustomer implements Selection{

	@Override
	public Selection doSelection(Scanner scan, Application app) {
		
		UserData info = new UserData();
		
		Scanner strs=new Scanner(System.in);
	    try {
	   
	 System.out.println("Create cust ID:");
	 info.setCust_id(strs.nextLine());
	 System.out.println(" Enter First Name:");
	 info.setFname(strs.nextLine());
	 System.out.println("Enter Last name:");
	 info.setLname(strs.nextLine());
	 System.out.println("Enter DOB yyyy-dd-mm");
	 info.setDob(strs.nextLine());
	 System.out.println("Enter Email:");
	 info.setEmail(strs.nextLine());
	 System.out.println("City:");
	 info.setCity(strs.nextLine());
	 System.out.println("Enter Pnone Number:");
	 info.setPho(strs.nextLine());
	 System.out.println("Enter Deposit amount:");
	 info.setPho(strs.nextLine());
	 
	       // Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/revbankpz", "root", "Vincent");
	PreparedStatement pst = con.prepareStatement("insert into aaanewcustomers(cust_id,fname,lname,dob,email,city,phone) values(?,?,?,?,?,?,?,?)");

	   pst.setString(1, info.getCust_id);
	   pst.setString(2, info.getFname());
	   pst.setString(3, info.getLname());
	   pst.setString(4, info.getDob());
	   pst.setString(5,  info.getEmail());
	   pst.setString(6,  info.getCity());
	   pst.setString(7,  info.getPho());
	   pst.setString(8,  info.getBalance());
	   
	   
	   
	 int i = pst.executeUpdate();
	 if(i!=0){
	   System.out.println("Confirm");
	 }
	 else{
	   System.out.println("Error  ");
	 }
	}
	catch (Exception e){
	System.out.println(e);
	}
		return null;
	}
	}



