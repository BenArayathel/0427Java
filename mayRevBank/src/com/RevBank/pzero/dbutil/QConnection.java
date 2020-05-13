/*
 * package com.RevBank.pzero.dbutil;
 * 
 * import java.io.File; import java.io.FileNotFoundException; import
 * java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.SQLException; import
 * java.util.ArrayList; import java.util.Iterator; import java.util.Scanner;
 * 
 * import com.RevBank.ApplyLoan; import com.RevBank.UserData;
 * 
 * public class QConnection extends ApplyLoan { private static Connection
 * connection=null; private QConnection() {
 * 
 * } //adding data to DB
 * 
 * 
 * public static Connection getConnection() throws ClassNotFoundException,
 * SQLException {
 * 
 * 
 * 
 * String url= "jdbc:mysql://localhost:3306/pzerobank"; String username="root";
 * String password="Vincent"; Connection
 * connection=DriverManager.getConnection(url, username, password); // Create
 * prepared statement to execute sql statement String query =
 * "INSERT INTO LOANAPP VALUES(?,?,?,?,?,?,?,?)"; PreparedStatement pst =
 * connection.prepareStatement(query);
 * 
 * gArrayList<UserData> uInfo = getLoanApp("LoanApp.txt"); for(int i =0;
 * i<uInfo.size(); i++) { pst.setString(1, uInfo.get(i).getAddress());
 * pst.setString(2, uInfo.get(i).getFname()); pst.setString(3,
 * uInfo.get(i).getLname()); pst.setString(4, uInfo.get(i).getAddress());
 * pst.setString(5, uInfo.get(i).getPho()); pst.setString(6,
 * uInfo.get(i).getEmpS()); pst.setString(7, uInfo.get(i).getMinc());
 * pst.setString(8, uInfo.get(i).getPsw());
 * 
 * pst.executeUpdate(); System.out.println("We did it fam"+ (i+1)); } return
 * connection;
 * 
 * 
 * 
 * 
 * }
 * 
 * 
 * }
 */