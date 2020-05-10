package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Account;
import com.company.model.Account;
import com.company.view.BankApp;

public class AccountDaoJdbcImpl implements AccountDao{

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

    ResultSet rs = null;
    PreparedStatement ps = null;

    private static final String INSERT_ACCOUNT_SQL =
            "insert into account (customer_id, account_type, balance, approved) " +
                    "values (?, ?, ?, ?)";

    private static final String SELECT_ACCOUNT_SQL =
            "select * from account where account_id = ?";

    private static final String SELECT_ALL_ACCOUNTS_SQL =
            "select * from account";

    private static final String UPDATE_ACCOUNT_SQL =
            "update account set customer_id = ?, account_type = ?, balance = ?, approved = ? " +
                    "where account_id = ?";

    private static final String DELETE_ACCOUNT_SQL =
            "delete from account where account_id = ?";


	
	@Override
	public Account addAccount(Account account) {
		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			String returnCols[] = { "account_id" };
			
			// PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps = conn.prepareStatement(INSERT_ACCOUNT_SQL, returnCols);
			
			//ps.setInt(1, account.getAccountId());
			ps.setInt(1, account.getCustomerId());
			ps.setString(2, account.getAccountType());
			ps.setBigDecimal(3, account.getBalance());
			ps.setBoolean(4, account.isApproved());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs!= null && rs.next()) {
				account.setAccountId(rs.getString(1));
				BankApp.loggy.info("Successfully added record id: "+rs.getInt(1));
				
			}
			return account;
			
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

	@Override
	public Account getAccount(String accountId) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ACCOUNT_SQL);
			ps.setString(1,accountId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getString("account_id"));
		        account.setCustomerId(rs.getInt("customer_id"));
		        account.setAccountType(rs.getString("account_type"));
		        account.setBalance(rs.getBigDecimal("balance"));
		        account.setApproved(rs.getBoolean("approved"));
				return account;
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

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ALL_ACCOUNTS_SQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getString("account_id"));
		        account.setCustomerId(rs.getInt("customer_id"));
		        account.setAccountType(rs.getString("account_type"));
		        account.setBalance(rs.getBigDecimal("balance"));
		        account.setApproved(rs.getBoolean("approved"));

		        accounts.add(account);
			}
			
			accounts.forEach(c -> BankApp.loggy.info(c));

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
		
		return accounts;
	}

	@Override
	public void updateAccount(Account account) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(UPDATE_ACCOUNT_SQL);
			ps.setInt(1, account.getCustomerId());
			ps.setString(2, account.getAccountType());
			ps.setBigDecimal(3, account.getBalance());
			ps.setBoolean(4, account.isApproved());
			ps.setString(5,account.getAccountId());
			
			int updatedRows = ps.executeUpdate();
			
			if (updatedRows == 0) {
				BankApp.loggy.info("No records updated.");
			} else {
				BankApp.loggy.info(updatedRows + " rows updated.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}

	@Override
	public void deleteAccount(String accountId) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(DELETE_ACCOUNT_SQL);
			ps.setString(1,accountId);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				BankApp.loggy.info("No records deleted.");
			} else {
				BankApp.loggy.info(deletedRows + " rows deleted.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}

}
