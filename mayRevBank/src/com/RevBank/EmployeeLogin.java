package com.RevBank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeLogin implements Selection{

	public void emLog () {

		Scanner s=new Scanner(System.in);
        try {
    System.out.println("Create userid:");
    String userid =s.nextLine();
    System.out.println(" enter first name:");
     String fname =s.nextLine();
     System.out.println("Enter last name:");
     String lname =s.nextLine();
     System.out.println("Enter Address:");
     String address = s.nextLine();
     System.out.println("Enter Phonee number:");
     String pno = s.nextLine();
     System.out.println("Employment Status:");
     String emps =s.nextLine();
     System.out.println("Monthly income:");
     String minc =s.nextLine();
	System.out.println("Create Password:");
     String psw =s.nextLine();
           // Class.forName("com.mysql.jdbc.Driver");
  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerinfodatab", "root", "Vincent");
  PreparedStatement pst = con.prepareStatement("insert into loanapp(userid,fname,lname,address,phone,emps,minc,psw) values(?,?,?,?,?,?,?,?)");

       pst.setString(1,userid);
       pst.setString(2, fname);
       pst.setString(3, lname);
       pst.setString(4, address);
       pst.setString(5, pno);
       pst.setString(6, emps);
       pst.setString(7, minc);
      pst.setString(8, psw);
     int i = pst.executeUpdate();
     if(i!=0){
       System.out.println("added");
     }
     else{
       System.out.println("failed to add");
     }
   }
   catch (Exception e){
    System.out.println(e);
   }
	}

	@Override
	public Selection doSelection(Scanner scan, Application app) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}	
