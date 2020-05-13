/*
 * package com.RevBank;
 * 
 * import java.io.BufferedOutputStream; import java.io.BufferedWriter; import
 * java.io.File; import java.io.FileOutputStream; import java.io.FileWriter;
 * import java.io.OutputStream; import java.io.PrintWriter; import
 * java.sql.SQLException; import java.util.Scanner;
 * 
 * public class CustomerFile {
 * 
 * 
 * 
 * public void cust() {
 * 
 * UserData dat = new UserData(); Scanner input = new Scanner(System.in);
 * 
 * 
 * try { System.out.println("Create Userid"); dat.setUserid(input.nextLine());
 * System.out.println("Enter Name"); dat.setFname(input.nextLine());
 * System.out.println("Enter last name"); dat.setLname(input.nextLine());
 * System.out.println("Enter Address"); dat.setAddress(input.nextLine());
 * System.out.println("Enter Phone"); dat.setPho(input.nextLine());
 * System.out.println("Enter Employment status self/w2");
 * dat.setEmpS(input.nextLine()); System.out.println("Enter Monthly Income");
 * dat.setMinc(input.nextLine()); System.out.println("Create Password");
 * dat.setPsw(input.nextLine());
 * 
 * }catch(NumberFormatException e) {
 * System.out.println("Please re=entery nnnname Aa to Zz"); }
 * 
 * 
 * 
 * try { File file = new File("NewCust.txt"); FileWriter fw = new
 * FileWriter(file,true); BufferedWriter buf = new BufferedWriter(fw);
 * PrintWriter ptw = new PrintWriter(buf);
 * ptw.append(dat.getUserid()+","+dat.getFname()+","+dat.getLname()+","+dat.
 * getAddress()+","+dat.getEmpS()+","+dat.getMinc()+","+dat.getPsw()+"\n");
 * ptw.close(); }catch(Exception e) {
 * 
 * }finally { if(input!=null) input.close();
 * 
 * }
 * 
 * 
 * return; } }
 */	
		
