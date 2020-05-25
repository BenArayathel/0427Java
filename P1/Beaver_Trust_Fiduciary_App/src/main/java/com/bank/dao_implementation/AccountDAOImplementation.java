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
import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.models.User;
import com.bank.presentation.WelcomeView;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class AccountDAOImplementation implements AccountDAOInterface {

	// CREATE new BANK account
	@Override
	public Account createAccount(User user, String accountName, String depositAmount) throws BankException {
		Account account = new Account();
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "{call create_new_account(?,?,?,?)}";
			CallableStatement cb = conn.prepareCall(sql);
			
			cb.setString(2, user.getUser_id());
			cb.setString(3, accountName);
			cb.setDouble(4, Double.parseDouble(depositAmount));
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			account.setAccount_id(cb.getString(1));					
					
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
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
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to list accounts.");
		}
		
//		Main.myLog.info("All accounts: " + accountList.toString());
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
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to list user accounts.");
		}
		return accountList;
	}
	
	@Override
	public Account listAccountByID(String account_id) throws BankException {
		Account account = new Account();
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "select * from bank_account where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				account.setAccount_id(rs.getString(1));
				account.setUser_id(rs.getString(2));
				account.setAccount_name(rs.getString(3));
				account.setBalance(rs.getDouble(4));
			}
			
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to return account by id");
		}
		return account;
	}
	
	@Override
	public Account listAccountByNameAndUserID(String account_name, String user_id) throws BankException {
		Account account = new Account();
		
		try (Connection conn = DataConnection.getConnection()) {
			String sql = "select * from bank_account where account_name = ? and user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account_name);
			ps.setString(2, user_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				account.setAccount_id(rs.getString(1));
				account.setUser_id(rs.getString(2));
				account.setAccount_name(rs.getString(3));
				account.setBalance(rs.getDouble(4));
			}
			
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to return account by account name and user_id");
		}
		return account;
	}
	
	// LOG ALL TRANSACTIONS WHEN MAKING DEPOSIT or WITHDRAWAL
	public void logTransaction(String amount, String accountName, String user_id, String type) throws BankException {
		String sql = "{call create_new_transaction(?,?,?,?,?)}";
		try (Connection conn = DataConnection.getConnection()) {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDouble(2, Double.parseDouble(amount));
			cs.setString(3, accountName);
			cs.setString(4, user_id);
			cs.setString(5, type);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.execute();
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to log transaction.");
		}
	}
	

	// DEPOSIT INTO ACCOUNT
	@Override
	public void deposit(User user, String accountName, String depositAmount) throws BankException {
		String user_id = user.getUser_id();
		String sql2 = 
				"update bank_account set account_balance = (account_balance + ?) where account_name = ? and user_id = ?";

				// then use that user id to access account and update balance
				try (Connection conn2 = DataConnection.getConnection()) {
					PreparedStatement ps2 = conn2.prepareStatement(sql2);
					ps2.setDouble(1, Double.parseDouble(depositAmount));
					ps2.setString(2, accountName);
					ps2.setString(3, user_id);
					logTransaction(depositAmount, accountName, user_id, "deposit");
					ps2.executeUpdate();
					

				} catch (SQLException | NumberFormatException e) {
					Main.myLog.error(e.getMessage() + e.getStackTrace());
					throw new BankException("There was a problem with the deposit, please try again later.");
				}
			}
	

	// WITHDRAW from ACCOUNT
	@Override
	public void withdraw(User user, String accountName, String withdrawalAmount) throws BankException {
			
		String user_id = user.getUser_id();
		String sql2 = 
				"update bank_account set account_balance = (account_balance - ?) where account_name = ? and user_id = ? and account_balance >= ?";

				try (Connection conn2 = DataConnection.getConnection()) {
					PreparedStatement ps2 = conn2.prepareStatement(sql2);
					ps2.setDouble(1, Double.parseDouble(withdrawalAmount));
					ps2.setString(2, accountName);
					ps2.setString(3, user_id);
					ps2.setDouble(4, Double.parseDouble(withdrawalAmount));
					logTransaction(withdrawalAmount, accountName, user_id, "withdrawal");
					
					int rs2 = ps2.executeUpdate();
				} catch (SQLException | BankException e) {
					Main.myLog.error(e.getMessage() + e.getStackTrace());
					throw new BankException("Could not make withdrawal, insufficient funds.");
				}
			}
	
	// LIST ALL OF THE TRANSACTION by the EMPLOYEE
	@Override
	public List<Transaction> listAllTransactions() throws BankException {
		List<Transaction> transactionList = new ArrayList<Transaction>();
			
			try (Connection conn = DataConnection.getConnection()) {
				String sql = "select * from bank_transaction";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					transactionList.add
						(new Transaction(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
				
			} catch (SQLException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				throw new BankException("Unable to list transactions");
			}			
			return transactionList;
		}

	
	// FOR TESTING and CLEANING UP AFTER TESTS
	@Override
	public void deleteTransaction(String account_name) throws BankException {
		String sql = "delete from bank_transaction where account_name = ?";
				
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account_name);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to delete transaction");
		}
	}	
	
	// FOR TESTING and CLEANING UP AFTER TESTS
	public void deleteAccount(String accountName) throws BankException {
		String sql = "delete from bank_account where account_name = ?";
				
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			throw new BankException("Unable to delete account");
		}
	}	
}
