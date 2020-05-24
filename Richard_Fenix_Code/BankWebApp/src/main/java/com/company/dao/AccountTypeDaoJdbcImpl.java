package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.company.model.Account;
import com.company.model.AccountType;
import com.company.view.BankApp;

public class AccountTypeDaoJdbcImpl implements AccountTypeDao {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

    ResultSet rs = null;
    PreparedStatement ps = null;

    private static final String SELECT_ACCOUNT_TYPE_SQL =
            "select * from account_type where account_type = ?";


	public AccountType getAccountType(String accType) {
		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ACCOUNT_TYPE_SQL);
			ps.setString(1,accType);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				AccountType accountType = new AccountType();
				accountType.setAccountType(rs.getString("account_type"));
				accountType.setDescription(rs.getString("description"));
				return accountType;
			} else {
				BankApp.loggy.info("Record not found.");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	}


}
