package com.bank.dao_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao_interface.AccountDAOInterface;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class AccountDAOImplementation implements AccountDAOInterface {
	// use this for the doubles $$$$

	@Override
	public Account createAccount(Account account) throws BankException {
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "{call create_new_account(?,?,?,?)}";
			CallableStatement cb = conn.prepareCall(sql);
			
			cb.setString(2, account.getUser_id());
			cb.setString(3, account.getAccount_name());
			cb.setDouble(4, account.getBalance());
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			account.setAccount_id(cb.getString(1));
			System.out.println("New account is added");
					
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("ACCOUNT DAO IMPLEMENTATION ERROR");
		}
		
		return account;
	}
	
	// LIST ALL THE ACCOUNTS, for the EMPLOYEE
	@Override
	public List<Account> listAccounts() throws BankException {
		List<Account> accountList = new ArrayList<Account>();
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "select * from bank_account";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				accountList.add
					(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("trouble with the account dao");
		}
		
		System.out.println("All accounts: " + accountList.toString());
		return accountList;
	}

	// LIST JUST THE CURRENT USERS ACCOUNTS
	@Override
	public List<Account> listUserAccounts(String username) throws BankException {
		List<Account> accountList = new ArrayList<Account>();
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "select * from bank_account inner join bank_user on bank_account.user_id = bank_user.user_id where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				accountList.add
				(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BankException("something wrong with list users account dao layer");
		}
		return accountList;
	}

	
	// DEPOSIT INTO ACCOUNT
	@Override
	public void deposit(String username, String accountName, String depositAmount) throws BankException {
		String user_id;
		String sql1 = "select user_id from bank_user where username = ?";
		String sql2 = 
				"update bank_account set account_balance = (account_balance + ?) where account_name = ? and user_id = ?";

				
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			user_id = rs.getString(2);
			System.out.println(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("can't get user_id in account dao");
		}
		
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql2);
			ps.setDouble(1, Double.parseDouble(depositAmount));
			int rs = ps.executeUpdate();
			
//			user_id = rs.getString(2);
//			System.out.println(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("trouble with make deposit in accout dao");
		}
		
	}
}
