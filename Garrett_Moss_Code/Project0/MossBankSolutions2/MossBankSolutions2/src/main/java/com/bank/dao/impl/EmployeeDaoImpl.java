package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dbutil.BankOracleConnection;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;

public class EmployeeDaoImpl implements EmployeeDAO{

	final static Logger logger = Logger.getLogger(MainDriver.class);
	
	public Customer approveAccount(String approve, String accountNumber) throws BankException {
			try (Connection connection= BankOracleConnection.getConnection()){
				String sql="UPDATE customer set approved = ?  where accountnumber =?";
				CallableStatement callableStatement = connection.prepareCall(sql);
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(2, accountNumber);
				preparedStatement.setString(1, approve);
				int resultSet=preparedStatement.executeUpdate();
				
				if (resultSet < 0) {
					throw new BankException("Internal error occured please contact SYSADMIN");
				}
				
				} catch (ClassNotFoundException | SQLException e) {
					throw new BankException("Internal error occured please contact SYSADMIN");
				}
			return null;
		}

	public Customer rejectAccount(String reject, String accountNumber) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="UPDATE customer set approved = ?  where accountnumber =?";
			CallableStatement callableStatement = connection.prepareCall(sql);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(2, accountNumber);
			preparedStatement.setString(1, reject);
			int resultSet=preparedStatement.executeUpdate();
			
			if (resultSet < 0) {
				throw new BankException("Internal error occured please contact SYSADMIN");
			}
			
			} catch (ClassNotFoundException | SQLException e) {
				throw new BankException("Internal error occured please contact SYSADMIN");
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
			throw new BankException("Internal error occured plase contact SYSADMIN");
		}
		
		return employee;
	}

	@Override
	public Employee updateEmployee(String newAddress) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee deleteEmployee(String employeeid) throws BankException {
		try (Connection connection= BankOracleConnection.getConnection()){
			String sql="DELETE from employee where employeeid =?	";
			CallableStatement callableStatement = connection.prepareCall(sql);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, employeeid);
			int resultSet=preparedStatement.executeUpdate();
			
			if (resultSet < 1) {
				throw new BankException("Internal error occured please contact SYSADMIN");
			}
			
			} catch (ClassNotFoundException | SQLException e) {
				throw new BankException("Internal error occured please contact SYSADMIN");
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
				throw new BankException ("Customer Account Number "+ accountNumber+ " is not valid");
			}
			}catch (ClassNotFoundException | SQLException e) {
				throw new BankException("Error contact Customer Support");
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
					throw new BankException("Error contact Customer Support");
				}
				return account;
		}
	}


