package com.RevBank;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.RevBank.pzero.dbutil.CreateLoan;


public class Main {


	
    public static void main(String[] args) throws Exception {
    	 Application app = new BankApplication("my Justice League Bank");
 	    app.run(args);
		/*
		 * Scanner s=new Scanner(System.in); try { System.out.println("Create userid:");
		 * String userid =s.nextLine(); System.out.println(" enter first name:"); String
		 * fname =s.nextLine(); System.out.println("Enter last name:"); String lname
		 * =s.nextLine(); System.out.println("Enter Address:"); String address =
		 * s.nextLine(); System.out.println("Enter Phonee number:"); String pno =
		 * s.nextLine(); System.out.println("Employment Status:"); String emps
		 * =s.nextLine(); System.out.println("Monthly income:"); String minc
		 * =s.nextLine(); System.out.println("Create Password:"); String psw
		 * =s.nextLine(); // Class.forName("com.mysql.jdbc.Driver"); Connection con =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/customerinfodatab",
		 * "root", "Vincent"); PreparedStatement pst = con.
		 * prepareStatement("insert into loanapp(userid,fname,lname,address,phone,emps,minc,psw) values(?,?,?,?,?,?,?,?)"
		 * );
		 * 
		 * pst.setString(1,userid); pst.setString(2, fname); pst.setString(3, lname);
		 * pst.setString(4, address); pst.setString(5, pno); pst.setString(6, emps);
		 * pst.setString(7, minc); pst.setString(8, psw); int i = pst.executeUpdate();
		 * if(i!=0){ System.out.println("added"); } else{
		 * System.out.println("failed to add"); } } catch (Exception e){
		 * System.out.println(e); }
		 */
	}
}
 		   
	/*
	 * 
	 * 
	 * Connection con =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/customerinfodatab",
	 * "root", "Vincent");
	 * 
	 * //rrayList<UserData> info = getUserDataT("LoanApp.txt");
	 * 
	 * PreparedStatement pst = con.
	 * prepareStatement("insert into bloanapp(userid,fname,lname,address,phone,emps,minc,psw) values(?,?,?,?,?,?,?,?)"
	 * );
	 * 
	 * pst.executeUpdate(); ArrayList<CreateLoan> info =
	 * getUserDataT("LoanApp.txt");
	 * 
	 * for(int i =0; i<info.size(); i++) { pst.setString(1,
	 * info.get(i).getUserid()); pst.setString(2,info.get(i).getFname());
	 * pst.setString(3,info.get(i).getLname());
	 * pst.setString(4,info.get(i).getAddress()); pst.setString(5,
	 * info.get(i).getPho()); pst.setString(6, info.get(i).getEmpS());
	 * pst.setString(7, info.get(i).getMinc());
	 * pst.setString(8,info.get(i).getPsw());
	 * 
	 * pst.executeUpdate(); System.out.println(info.size()); }
	 * 
	 * System.out.println("Restart"); //return con;
	 * 
	 * } catch(SQLException e) { System.out.println(e); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * public static ArrayList<CreateLoan> getUserDataT(String filep) {
	 * FileInputStream fs = null; InputStreamReader ir = null; BufferedReader buffR
	 * = null; ArrayList<CreateLoan> info = new ArrayList<CreateLoan>(); try { fs =
	 * new FileInputStream(filep); ir = new InputStreamReader(fs); buffR = new
	 * BufferedReader(ir);
	 * 
	 * // saving each line in a String String line = null;
	 * 
	 * // ArrayList to save userinfo String[] str = null;
	 * 
	 * while(true) { line = buffR.readLine(); if(line==null) { break; }else { str =
	 * line.split(","); info.add(new CreateLoan(str[0], str[1],
	 * str[2],str[3],str[4],str[5],str[6],str[7],str[8])); } }
	 * 
	 * 
	 * }catch(Exception e) { System.out.println("Error will Robinson");
	 * e.printStackTrace();
	 * 
	 * }finally {
	 * 
	 * try { buffR.close(); ir.close(); fs.close(); }catch(IOException e){
	 * e.printStackTrace(); }
	 * 
	 * 
	 * } return info;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * public static void addInput()throws Exception{ String v1 = "Clark"; String
	 * v2="Kent"; Integer v3 = 3500; try { Connection con = getConnection();
	 * PreparedStatement put = con.
	 * prepareStatement("INSERT INTO testbank(first_name,last_name,deposits) VALUES('"
	 * +v1+"','"+v2+"','"+v3+"')"); put.executeUpdate(); }catch(SQLException e) {
	 * System.out.println("okay "); }
	 * 
	 * } public static void cTable() throws Exception{ try { Connection con =
	 * getConnection();{ PreparedStatement into = con.
	 * prepareStatement("CREATE TABLE  testbank(id int NOT NULL AUTO_INCREMENT,first_name varchar(35),last_name varchar(35), deposits  int, PRIMARY KEY(id))"
	 * ); into.executeUpdate();
	 * 
	 * } }catch(SQLException e) { System.out.println("Now what!!"); } finally {
	 * System.out.println(" I am in there"); } }
	 * 
	 * 
	 * 
	 * public static Connection getConnection() { try {
	 * 
	 * Connection con =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/customerinfodatab",
	 * "root", "Vincent");
	 * 
	 * //rrayList<UserData> info = getUserDataT("LoanApp.txt");
	 * 
	 * PreparedStatement pst = con.
	 * prepareStatement("insert into loanapp(userid,fname,lname,address,phone,emps,minc,psw) values(?,?,?,?,?,?,?,?)"
	 * );
	 * 
	 * pst.executeUpdate(); ArrayList<CreateLoan> info =
	 * getUserDataT("LoanApp.txt");
	 * 
	 * for(int i =0; i<info.size(); i++) { pst.setString(1,
	 * info.get(i).getUserid()); pst.setString(3,info.get(i).getFname());
	 * pst.setString(4,info.get(i).getLname());
	 * pst.setString(5,info.get(i).getAddress()); pst.setString(5,
	 * info.get(i).getPho()); pst.setString(6, info.get(i).getEmpS());
	 * pst.setString(7, info.get(i).getMinc());
	 * pst.setString(8,info.get(i).getPsw());
	 * 
	 * pst.executeUpdate(); System.out.println(info.size()); }
	 * 
	 * System.out.println("Restart"); return con;
	 * 
	 * } catch(SQLException e) { System.out.println(e); }
	 * 
	 * return null ;
	 * 
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 */