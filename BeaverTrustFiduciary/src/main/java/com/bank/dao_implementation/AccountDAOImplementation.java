package com.bank.dao_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao_interface.AccountDAOInterface;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class AccountDAOImplementation implements AccountDAOInterface {

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
			// retrieve id associated with username
//			String findID = "select user_id from bank_user where username = ?";
//			PreparedStatement findUserIDFromUsername = conn.prepareStatement(findID);
//			findUserIDFromUsername.setString(1, username);
//			ResultSet rsID = findUserIDFromUsername.executeQuery();
			
//			while (rsID.next()) {
//				String userID = rsID.getString(1);
//				System.out.println(userID = " user ID");
				
				// now that we have the ID, use that to run an inner join to get their accounts
				String sql = "select account_name, account_balance from bank_account inner join bank_user on bank_account.user_id = bank_user.user_id where username = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					accountList.add
					(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
					System.out.println(accountList + " inside while loop");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("trouble with the account dao");
		}
		
		System.out.println("All accounts: " + accountList);
		return accountList;
	}
}
