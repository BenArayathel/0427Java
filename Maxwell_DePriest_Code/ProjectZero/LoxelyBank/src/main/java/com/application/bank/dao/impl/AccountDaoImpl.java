package com.application.bank.dao.impl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.application.bank.dao.AccountDao;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;

public class AccountDaoImpl implements AccountDao {
	final static Logger loggy = Logger.getLogger(Account.class);
	
	private static String x = "ccpojkviae8q";
	private static String url =
			"jdbc:oracle:thin:@database-1." + x + ".us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "madmax9242";
	private static String password = "jasonbourne";

	@Override
	public Account insertAccount(Account a) throws BusinessException{
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO bankaccount VALUES ?,?,?,?,?,?,?");
			
			ps.setInt(1, a.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error. Please contact SYSADMIN");
		}
		return a;
		
		
	}

	@Override
	public Account selectAccount() throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount() throws BusinessException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account updateAccount(Account a) throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}

}
























