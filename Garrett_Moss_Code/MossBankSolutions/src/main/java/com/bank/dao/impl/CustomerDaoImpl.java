package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.CustomerDAO;
import com.bank.dbutil.BankOracleConnection;
import com.bank.exception.BankException;
import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.SavingsAccount;

import oracle.jdbc.driver.OracleConnection;

public class CustomerDaoImpl implements CustomerDAO {

	@Override
	public Customer createCustomerAccount(Customer createAccount) throws BankException {

		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="{call CREATECUSTOMER_PROCEDURE(?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, createAccount.getUsername());
			callableStatement.setString(2, createAccount.getPassword());
			callableStatement.setString(3, createAccount.getName());
			callableStatement.setDate(4, new java.sql.Date(createAccount.getBirthdate().getTime()));
			callableStatement.setString(5, createAccount.getAddress());
			callableStatement.setLong(6, createAccount.getCheckingAccountNumber());
			callableStatement.setLong(7, createAccount.getSavingsAccountNumber());
			
			callableStatement.execute();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BankException("Internal error occured plase contact SYSADMIN");
		}
		
		return createAccount;
	}

	@Override
	public CheckingAccount createCheckingAccount(CheckingAccount createChecking) throws BankException {
		
		try (Connection connection= BankOracleConnection.getConnection()){
		String sql="{call CREATECHECKING_PROCEDURE(?,?,?)}";
		CallableStatement callableStatement = connection.prepareCall(sql);
		callableStatement.setString(2, createChecking.getName());
		callableStatement.setInt(3, createChecking.getAccountBalance());
		
		callableStatement.registerOutParameter(1, java.sql.Types.LONGNVARCHAR);
		
		callableStatement.execute();
		
		createChecking.setCheckingAccountNumber(callableStatement.getInt(1));
		
		
		} catch (ClassNotFoundException | SQLException e) {
			throw new BankException("Internal error occured please contact SYSADMIN");
		}
		return createChecking;
	}


	public Customer viewBalance(int accountNumber) throws BankException {
		Customer newCustomer=null;
		try (Connection connection= BankOracleConnection.getConnection()){
		String sql="Select accountbalance from savingsaccount where accountnumber=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet=preparedStatement.executeQuery();
		if(resultSet.next()) {
			newCustomer= new Customer();
			newCustomer.setCheckingAccountNumber(accountNumber);
			
		}else if(resultSet.next()) {
			newCustomer= new Customer();
			newCustomer.setSavingsAccountNumber(accountNumber);
		
		}else {
			throw new BankException("Account Number "+ accountNumber + "is not valid");
		}
		
		} catch (ClassNotFoundException | SQLException e) {
			throw new BankException("Internal Error contact SYSADMIN");
		}
		return newCustomer;
	}

	public Customer withdraw(int withdraw) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer deposit(int deposit) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer moneyTransfer(String accountNumber, int transferAmount) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer acceptTransfer(Boolean acceptance) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(String username) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount createSavingsAccount(SavingsAccount createSavings) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="{call CREATESAVINGS_PROCEDURE(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, createSavings.getName());
			callableStatement.setInt(3, createSavings.getAccountBalance());
			
			callableStatement.registerOutParameter(1, java.sql.Types.LONGNVARCHAR);
			
			callableStatement.execute();
			
			createSavings.setSavingsAccountNumber(callableStatement.getInt(1));
			
			
			} catch (ClassNotFoundException | SQLException e) {
				throw new BankException("Internal error occured please contact SYSADMIN");
			}
			return createSavings;
		}


}
