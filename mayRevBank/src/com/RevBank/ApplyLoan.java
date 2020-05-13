package com.RevBank;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplyLoan implements Selection {


@Override
public Selection doSelection(Scanner scan, Application app) {
	
	Scanner strs=new Scanner(System.in);
    try {
System.out.println("Enter Custid:");
  String cust_id =strs.nextLine();
System.out.println(" Enter First Name:");
 String fname =strs.nextLine();
 System.out.println("Enter Last name:");
 String lname =strs.nextLine();
 System.out.println("Enter DOB dd.MM.YY");
 String dob = strs.nextLine();
 System.out.println("Enter Email:");
 String email = strs.nextLine();
 System.out.println("City:");
 String city =strs.nextLine();
 System.out.println("Enter Pnone Number:");
 String pno =strs.nextLine();

       // Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/revbankpz", "root", "Vincent");
PreparedStatement pst = con.prepareStatement("insert into bcustomers(cust_id,fname,lname,dob,email,city,phone) values(?,?,?,?,?,?,?)");

   pst.setString(1,cust_id);
   pst.setString(2, fname);
   pst.setString(3, lname);
   pst.setString(4, dob);
   pst.setString(5, email);
   pst.setString(6, city);
   pst.setString(7, pno);
 
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

