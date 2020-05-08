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
import com.application.bank.secrets.SecretStuff;

public class AccountDaoImpl implements AccountDao {
	final static Logger loggy = Logger.getLogger(Account.class);
	
	private static final String myKey = SecretStuff.getAwsKey();
	public static final String URL =
			"jdbc:oracle:thin:@database-1." +  myKey + ".us-east-2.rds.amazonaws.com:1521:orcl";
	public static final String USERNAME = SecretStuff.getAwsUserName();
	public static final String PASSWORD = SecretStuff.getAwsPassword();

	@Override
	public Account insertAccount(Account a) throws BusinessException{
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("call CREATEBANKACCOUNT(?,?,?,?,?,?,?)");
			
			ps.setString(1, "");
			ps.setInt(2, a.getSavingsAccountNumber());
			ps.setInt(3, a.getCheckingAccountNumber());
			ps.setDouble(4, a.getCheckingBalance());
			ps.setDouble(5, a.getSavingsBalance());
			ps.setBoolean(6, a.isActive());
			ps.setString(7, a.getUserEmail());
			
			ps.execute();
			
			loggy.info("Creating new account");
			
			
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
	
	public void deleteAllAccounts() throws BusinessException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankaccount");
			
			ps.execute();
			loggy.info("Deleting all rows from Account table");
		} catch (SQLException e) {
			loggy.warn("Caught SQLException- " + e);
			e.printStackTrace();
		}
	}

}
























