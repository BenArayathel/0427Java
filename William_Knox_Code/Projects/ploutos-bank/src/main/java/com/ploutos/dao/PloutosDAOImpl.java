package com.ploutos.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ploutos.model.Account;
import com.ploutos.model.Login;
import com.ploutos.model.Transaction;
import com.ploutos.exception.BusinessException;

public class PloutosDAOImpl implements PloutosDAO {
	final static Logger L = Logger.getLogger(PloutosDAOImpl.class);
	
	@Override
	public void insertLogin(Login login) throws BusinessException {
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "call add_login(?,?,?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1, login.getUsername());
			cs.setString(2, login.getPassword());
			cs.setInt(3, 0);
			cs.execute();
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
	}
	
	@Override
	public List<Login> getLoginList(int status) throws BusinessException {
		List<Login> result = new ArrayList<>();
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "select * from logins where is_active = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Login l = new Login(rs.getString("Username"), rs.getString("password"), rs.getInt("is_active"));
				result.add(l);
			}
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return result;
	}

	@Override
	public Account insertAccount(Account account) throws BusinessException {
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "{call add_bank_account(?,?,?)}";
			CallableStatement cs = c.prepareCall(sql);
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, account.getUserID());
			cs.setInt(3, account.getBalance());
			cs.execute();
			account.setAccountNumber(cs.getLong(1));
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return account;
	}

	@Override
	public Account getAccount(long accountNumber) throws BusinessException {
		Account res = new Account();
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "Select * from bank_accounts where account_num = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = new Account(rs.getInt("account_num"), rs.getString("user_id"), rs.getInt("balance"));
			}
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return res;
	}

	@Override
	public List<Account> getAccountList(Login login) throws BusinessException {
		List<Account> res = new ArrayList<>();
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "Select * from bank_accounts where user_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, login.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account a = new Account(rs.getInt("Account_num"), rs.getString("user_id"), rs.getInt("Balance"));
				res.add(a);
			}
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return res;
	}

	@Override
	public Transaction insertTransaction(Transaction transaction) throws BusinessException {
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "call add_transaction(?,?,?,?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setLong(2, transaction.getToAccountNum());
			cs.setLong(3, transaction.getFromAccountNum());
			cs.setInt(4, transaction.getAmount());
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			
			cs.execute();
			
			transaction.setTransactionID(cs.getInt(1));
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN. Most Likely culprit: invalid Account Number was entered.");
		}
		return transaction;
	}

	@Override
	public List<Transaction> getTransactionList() throws BusinessException {
		List<Transaction> result = new ArrayList<>();
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "select * from transactions";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt("transaction_id"), rs.getInt("bank_account_to"), rs.getInt("bank_account_from"));
				result.add(t);
			}
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return result;
	}

	@Override
	public boolean isEmployee(String username, String password) throws BusinessException {
		return (username.equals("cody") && password.equals("banks"));
	}

	@Override
	public void updateAccountAmount(Account account, int amount) throws BusinessException {
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "update bank_accounts set balance = ? where account_num = ?";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, amount);
			cs.setLong(2, account.getAccountNumber());
			cs.execute();
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
	}

	@Override
	public Login getLogin(String username, String password) throws BusinessException {
		Login login = null;
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "select username, password, is_active from logins where username = ? and password = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				login = new Login(rs.getString("username"), rs.getString("password"), rs.getInt("is_active"));
			}
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
		return login;
	}

	@Override
	public void updateLoginStatus(Login login, int status) throws BusinessException {
		if (status != 1 && status != 0 && status != -1) {
			throw new BusinessException("Invalid Login status number entered: " + status);
		}
		try (Connection c = PloutosConnection.getConnection()) {
			String sql = "update logins set is_active = ? where username = ?";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, status);
			cs.setString(2, login.getUsername());
			cs.execute();
		} catch (SQLException e) {
			L.error(e.getStackTrace() + " " + e.getMessage());
			throw new BusinessException("Internal error occurred please contact SYSADMIN.");
		}
	}

}
