
package com.bhank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.dao.AccountDAO;
import com.bhank.dbutil.OracleConnection;
import com.bhank.exception.BusinessException;
import com.bhank.model.Account;
import com.bhank.model.Customer;

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
	public List<Account> selectAllAccountsByCustomer(Customer customer) throws BusinessException {
		List<Account> customerAccountsList = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "select * from account where customer_id="+customer.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getString("id"));
				account.setCustomerId(resultSet.getString("customer_id"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setPending(resultSet.getString("pending").equals("y")?true:false);
				
				customerAccountsList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		} 
		return customerAccountsList;
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

	@Override
	public Account approveAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account declineAccount(Account account) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
