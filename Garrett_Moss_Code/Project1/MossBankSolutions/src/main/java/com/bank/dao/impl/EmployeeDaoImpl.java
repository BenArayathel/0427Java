package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dbutil.BankOracleConnection;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transactions;

public class EmployeeDaoImpl implements EmployeeDAO{

	final static Logger logger = Logger.getLogger(MainDriver.class);
	@Override
	public Customer approveAccount(String approve, String accountNumber) throws BankException {
			try (Connection connection= BankOracleConnection.getConnection()){
				String sql="UPDATE customer set approved = ?  where accountnumber =?";
				CallableStatement callableStatement = connection.prepareCall(sql);
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(2, accountNumber);
				preparedStatement.setString(1, ("Approved by " + approve));
				int resultSet=preparedStatement.executeUpdate();
				
				if (resultSet < 0) {
					logger.error("Internal error occured please contact SYSADMIN");
				}
				
				} catch (ClassNotFoundException | SQLException e) {
					logger.error("Internal error occured please contact SYSADMIN");
				}
			return null;
		}
	@Override
	public Customer rejectAccount(String reject, String accountNumber) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="UPDATE customer set approved = ?  where accountnumber =?";
			CallableStatement callableStatement = connection.prepareCall(sql);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(2, accountNumber);
			preparedStatement.setString(1, ("Rejected by " + reject));
			int resultSet=preparedStatement.executeUpdate();
			
			if (resultSet < 0) {
				logger.error("Internal error occured please contact SYSADMIN");
			}
			
			} catch (ClassNotFoundException | SQLException e) {
				logger.error("Internal error occured please contact SYSADMIN");
			}
		return null;
	}


	@Override
	public Employee createEmployee(Employee employee) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="{call CREATEEMPLOYEE(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, employee.getUsername());
			callableStatement.setString(3, employee.getPassword());
			
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			
			employee.setEmployeeId(callableStatement.getString(1));
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Internal error occured plase contact SYSADMIN");
		}
		
		return employee;
	}

	@Override
	public Employee updateEmployee(String newPassword, String accountNumber) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="UPDATE customer set password = ? where accountnumber =?";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, newPassword);
			callableStatement.setString(2, accountNumber);
			
			callableStatement.execute();
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Internal error occured plase contact SYSADMIN");
		}
		
		return null;
	}

	@Override
	public String deleteEmployee(String employeeId) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="DELETE from employee where employeeid =?	";
			CallableStatement callableStatement = connection.prepareCall(sql);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, employeeId);
			int resultSet=preparedStatement.executeUpdate();
			
			if (resultSet < 1) {
				logger.error("Internal error occured please contact SYSADMIN");
			}
			
			} catch (ClassNotFoundException | SQLException e) {
				logger.error("Internal error occured please contact SYSADMIN");
			}
		return null;
	}

	@Override
	public Customer viewCustomerAccount(String accountNumber) throws BankException {
		Customer newCustomer=null;
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="SELECT username, accountnumber, accountbalance, approved from customer where accountnumber=?	";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				newCustomer = new Customer();
				newCustomer.setAccountNumber(accountNumber);
				newCustomer.setUsername(resultSet.getString("username"));
				newCustomer.setAccountNumber(resultSet.getString("accountnumber"));;
				newCustomer.setAccountBalance(resultSet.getString("accountbalance"));
				newCustomer.setApproved(resultSet.getString("approved"));
			} else {
				logger.error("Customer Account Number "+ accountNumber+ " is not valid");
			}
			}catch (ClassNotFoundException | SQLException e) {
				logger.error("Error contact Customer Support");
			}
			return newCustomer;
			
		}

	@Override
	public Employee loginVerification(String username) throws BankException {
		Employee account = new Employee();
		try (Connection connection= BankOracleConnection.getConnection()){
				String sql="SELECT * from employee where username=?";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, username);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					account.setEmployeeId(resultSet.getString("employeeId"));
					account.setUsername(resultSet.getString("username"));
					account.setPassword(resultSet.getString("password"));				
				} else {
					 logger.error("Employee Credentials "+ username+ " are not valid");
				}
				}catch (ClassNotFoundException | SQLException e) {
					logger.error("Error contact Customer Support");
				}
				return account;
		}

	@Override
		public List<Transactions> viewTransactionLogs(String choice) throws BankException{
		List<Transactions> accountTransactions = new ArrayList<Transactions>();
		try (Connection connection= BankOracleConnection.getConnection()){
				String sql="SELECT * from transactions";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				ResultSet resultSet=preparedStatement.executeQuery();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(resultSet.next()) {
					accountTransactions.add(new Transactions(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
				}
				
				}catch (ClassNotFoundException | SQLException e) {
					logger.error("Error contact Customer Support");
				}
				return accountTransactions;
		}
	}


