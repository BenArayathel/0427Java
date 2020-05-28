
package com.bhank.webapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.webapp.dao.AccountDAO;
import com.bhank.webapp.dbutil.OracleConnection;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.model.Transaction;

public class AccountDAOImpl implements AccountDAO {

	TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
	
	@Override
	public Account createAccount(Account account) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATEACCOUNT(?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, account.getCustomerId());
			callableStatement.setDouble(3, account.getBalance());
			callableStatement.setString(4, "y");
			callableStatement.setString(5, "n");

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			account.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to created account in database");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully created account in database");
		return account;
	}

	@Override
	public Account deposit(Account account, double amount) throws BusinessException {
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "update account set balance=\'"+(account.getBalance()+amount)+"\' where id=\'"+account.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate();
			createTransaction(account.getCustomerId(), account.getCustomerId(), amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			Main.logger.error("Account DAO failed to update account \""+account.getId()+"\" with deposit of \""+amount+"\" in database");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		
		Main.logger.info("Account DAO successfully updated account \""+account.getId()+"\" with deposit of \""+amount+"\" in database");
		return account;
	}

	@Override
	public Account withdraw(Account account, double amount) throws BusinessException {
		Account a = new Account();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "update account set balance=\'"+(account.getBalance()-amount)+"\' where id=\'"+account.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate(sql);
			createTransaction(account.getCustomerId(), account.getCustomerId(), amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			Main.logger.error("Account DAO failed to update account \""+account.getId()+"\" with withdrawal of \""+amount+"\" in database");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully updated account \""+account.getId()+"\" with withdrawal of \""+amount+"\" in database");
		return account;
	}

	@Override
	public Transaction postMoneyTransfer(Account fromAccount, Account toAccount, double amount) throws BusinessException {
		createTransaction(fromAccount.getCustomerId(), toAccount.getCustomerId(), amount);
		return null;
	}

	@Override
	public Transaction acceptMoneyTransfer(String transactionId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccountsByCustomer(String customerId) throws BusinessException {
		List<Account> customerAccountsList = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "select * from account where customer_id=\'"+customerId+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getString("id"));
				account.setCustomerId(resultSet.getString("customer_id"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setPending(resultSet.getString("pending").equals("y")?true:false);
				account.setApproved(resultSet.getString("approved").equals("y")?true:false);
				
				customerAccountsList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to select all accounts by customer \""+customerId+"\"");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully selected all accounts by customer \""+customerId+"\"");
		return customerAccountsList;
	}

	@Override
	public Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountById(String accountId) throws BusinessException {
		Account account = new Account();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,customer_id,balance,pending,approved from account where id= \'"+accountId+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account.setId(resultSet.getString("id"));
				account.setCustomerId(resultSet.getString("customer_id"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setPending(resultSet.getString("pending").equals("y")?true:false);
				account.setApproved(resultSet.getString("approved").equals("y")?true:false);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to select account by account id \""+accountId+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully selected account by account id \""+accountId+"\"");
		return account;
	}

	@Override
	public void deleteAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account approveAccount(Account account) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "update account set approved=\'y\' ,pending=\'n\' where id=\'"+account.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to approve account by account id \""+account.getId()+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully approved account by account id \""+account.getId()+"\"");
		return account;
	}

	@Override
	public Account declineAccount(Account account) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "update account set approved=\'n\' ,pending=\'n\' where id=\'"+account.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to decline account by account id \""+account.getId()+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully decline account by account id \""+account.getId()+"\"");
		return account;
	}

	public List<Account> selectAllPendingAccounts() throws BusinessException {
		List<Account> pendingAccountsList = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "select * from account where pending=\'y\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getString("id"));
				account.setCustomerId(resultSet.getString("customer_id"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setPending(resultSet.getString("pending").equals("y")?true:false);
				account.setApproved(resultSet.getString("approved").equals("y")?true:false);
				
				if(account.isApproved()) {
					continue;
				} else {
				pendingAccountsList.add(account);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to select all pending accounts");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully selected all pending accounts");
		return pendingAccountsList;
	}
	
	private void createTransaction(String customerFrom,  String customerTo, double amount) throws BusinessException {
		try(Connection connection = OracleConnection.getConnection()) {
		String sql = "{call CREATETRANSACTION(?,?,?,?,?,?,?)}";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.setString(2, customerFrom);
		callableStatement.setString(3, customerTo);
		callableStatement.setDouble(4, amount);
		callableStatement.setString(5, "y");
		callableStatement.setString(6, "y");
		callableStatement.setString(7, "n");

		callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

		callableStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
	}

}
