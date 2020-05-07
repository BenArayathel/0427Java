package com.application.bank.dao.impl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.application.bank.dao.AccountDao;
import com.application.bank.models.Account;

public class AccountDaoImpl implements AccountDao {
	final static Logger loggy = Logger.getLogger(Account.class);
	
	private static String x = "ccpojkviae8q";
	private static String url =
			"jdbc:oracle:thin:@database-1." + x + ".us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "madmax9242";
	private static String password = "jasonbourne";

	@Override
	public void insertAccount(Account a) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bankaccount VALUES ?,?,?,?,?,?,?");
			
			ps.setInt(1, a.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account selectAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() {
		// TODO Auto-generated method stub
		
	}

}
























