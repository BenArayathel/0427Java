package com.bhank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bhank.dao.AccountDAO;
import com.bhank.dbutil.OracleConnection;
import com.bhank.exception.BusinessException;
import com.bhank.model.Account;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account createAccount(Account account) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATEACCOUNT(?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, account.getId());
			callableStatement.setDouble(3, account.getBalance());
			callableStatement.setString(4, "y");

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			account.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		return account;
	}

	@Override
	public Account deposit(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account postMoneyTransfer(Account account, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account acceptMoneyTransfer(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountByNameAndPassword(String name, String Password) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccountById(String id) throws BusinessException {
		Account account = new Account();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "Select id,customer_id,balance,pending from account where id= \'"+id+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account.setId(resultSet.getString("id"));
				account.setCustomerId(resultSet.getString("customer_id"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setPending(resultSet.getString("pending").equals("y")?true:false);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		return account;
	}

	@Override
	public void deleteAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
