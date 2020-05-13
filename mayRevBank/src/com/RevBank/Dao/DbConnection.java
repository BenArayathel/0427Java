package com.RevBank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;

import com.RevBank.Customer;
import com.RevBank.CustomerLogin;
import com.RevBank.UserData;

public class DbConnection {
	
	
	
	String url = "jdbc:mysql://localhost:3306/bankp";
	String user = "246Rec";
	String psw = "369Rev";
	
	
	private Connection conn() {
		return null; }
		
int NewAccount(String fname, String lname, String dob, String email, String City, String Phone ) { 
	    UserData info = new UserData();	
	
		int newcustID = -1;
		int custaccID = -1;
		Connection connection = conn();
			try {
				connection.setAutoCommit(false);
			String bkUser= "insert into newcustomer(fname,lname,dob,email,City,Phone) values(?,?,?,?,?,?)";
				PreparedStatement  addUser = connection.prepareStatement(bkUser, Statement.RETURN_GENERATED_KEYS);
					addUser.setString(1, fname);
					addUser.setString(2, lname);
					addUser.setString(3, dob);
					addUser.setString(4, email);
					addUser.setString(5, City);
					addUser.setString(6, Phone);
					addUser.executeUpdate();
					ResultSet addRes =addUser.getGeneratedKeys();
					if(addRes.next()) {
						newcustID = addRes.getInt(1);
					}
					
			  
				String balanceAcc = "Insert into custacc(lname,balance) values(?,?)";
				PreparedStatement  addcustacc = connection.prepareStatement(balanceAcc, Statement.RETURN_GENERATED_KEYS);
				addcustacc.setString(1, info.getLname());
				addcustacc.setString(2, info.getBalance());
				addcustacc.executeUpdate();
				 ResultSet addAR = addcustacc.getGeneratedKeys();
				 		if(addAR.next()) {
				 			custaccID = addAR.getInt(1);
				 			
				 			
				 			}
				 		if ( newcustID > 0 && custaccID >0) {
				 			String bindAcc  = "insert into binder(newcustId,custaccID) values(?,?)";				 		
				 			PreparedStatement pst = connection.prepareStatement(bindAcc);
				 			pst.setInt(1, newcustID);
				 			pst.setInt(2, custaccID);
				 			pst.executeUpdate();
				 			connection.commit();
									}
				 			else {
				 				connection.rollback();
				 			}
				 			
				 		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return custaccID;
			
			
}	

Customer GetAccount(int custaccID) {
	
	Customer cust = null;
	Connection connection = conn();
	try {
	String retrCustsql = "select fname, lname, city, balance"
						+"from newcustomer nc join binder b on nc.ID = b.newcustID"			
						+"join custacc ca on ca.ID = b.custaccID"
						+"where ca.Id = ?";
		PreparedStatement findCust= connection.prepareStatement(retrCustsql);
		findCust.setInt(1,custaccID);
		ResultSet findCustResults = findCust.executeQuery();
		if(findCustResults.next()) {
			String fname= findCustResults.getString("fname");
			String lname= findCustResults.getString("lname");
			String city= findCustResults.getString("city");
			Double balance= findCustResults.getDouble("balance");
			
			cust = new Customer(fname,lname, city,  balance);
		}
		else {
			System.err.println("Account DNE with that ID #" + custaccID);
		}
			
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return cust;
}

			
}		