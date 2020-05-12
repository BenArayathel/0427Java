package com.bank.dao.impl.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.bank.dbutil.BankOracleConnection;
import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.model.Employee;

public class EmployeeDaoImplTest {

	@Test
	
	public void approveAccount() throws BankException {
			String approve = "approved";
			String accountNumber = "1000000000";
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
		}
	@Test
	public void rejectAccount() throws BankException {
		String reject = "rejected";
		String accountNumber = "10000000000";
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
	}
}
