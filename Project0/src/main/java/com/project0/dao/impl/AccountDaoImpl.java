package com.project0.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project0.dao.AccountDAO;
import com.project0.dbutil.OracleConnection;
import com.project0.exception.BusinessException;
import com.project0.model.Account;

public class AccountDaoImpl implements AccountDAO {

	@Override
	public Account createAccount(Account account) throws BusinessException {
		try(Connection connection = OracleConnection.getConnection()) {
			String sql="{call CREATEACCOUNT(?, ?, ?, ?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setString(2, account.getUserName());
			cs.setDouble(3, account.getBalance());
			cs.setBoolean(4, account.isApproved());
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			
			account.setAccountId(cs.getInt(1));
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		
		return account;
	}

	@Override
	public Account getAccountById(int accountId) throws BusinessException {
		Account a = null;
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "SELECT account_id, user_name, balance, approved FROM accounts WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				a = new Account();
				a.setAccountId(accountId);
				a.setUserName(resultSet.getString(2));
				a.setBalance(resultSet.getDouble(3));
				a.setApproved(resultSet.getBoolean(4));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		
		return a;
	}

	@Override
	public Account withdraw(int accountId, double amount) throws BusinessException {
		Account a = getAccountById(accountId);
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, (a.getBalance() - amount));
			ps.setInt(2, accountId);
			ps.executeUpdate();
			a.setBalance(a.getBalance() - amount);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		
		return a;
	}

	@Override
	public Account deposit(int accountId, double amount) throws BusinessException {
		Account a = getAccountById(accountId);
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, (a.getBalance() + amount));
			ps.setInt(2, accountId);
			ps.executeUpdate();
			a.setBalance(a.getBalance() + amount);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		
		return a;
	}

	@Override
	public Account approveAccount(Account account) throws BusinessException {
		Account a = null;
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "UPDATE accounts SET approved = 1 WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, account.getAccountId());
			ps.executeUpdate();
			a = getAccountById(account.getAccountId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return a;
	}

	@Override
	public void rejectAccount(Account account) throws BusinessException {
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "DELETE FROM accounts WHERE account_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, account.getAccountId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Account> getAccountsByUserName(String userName) throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "SELECT account_id, user_name, balance, approved FROM accounts WHERE user_name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				Account a = new Account();
				a.setAccountId(resultSet.getInt(1));
				a.setUserName(resultSet.getString(2));
				a.setBalance(resultSet.getDouble(3));
				a.setApproved(resultSet.getBoolean(4));
				accountList.add(a);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return accountList;
	}

	@Override
	public List<Account> getPendingAccounts() throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "SELECT account_id, user_name, balance, approved FROM accounts WHERE approved = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, false);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				Account a = new Account();
				a.setAccountId(resultSet.getInt(1));
				a.setUserName(resultSet.getString(2));
				a.setBalance(resultSet.getDouble(3));
				a.setApproved(resultSet.getBoolean(4));
				accountList.add(a);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return accountList;
	}

}
