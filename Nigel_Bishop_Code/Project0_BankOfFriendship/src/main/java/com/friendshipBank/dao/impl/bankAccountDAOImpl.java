package com.friendshipBank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dbutil.OracleConnection;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.userAccess;

public class bankAccountDAOImpl implements bankAccountDAO{

	@Override
	public bankAccount createNewBankAccount(bankAccount bankAccount) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
		String sql="{call CREATEACCOUNT(?,?,?,?,?)}";
		CallableStatement callableStatement=connection.prepareCall(sql);
		callableStatement.setString(2, bankAccount.getCustomerID());
		callableStatement.setString(3, bankAccount.getAccountType());
//		callableStatement.setLong(2, bankAccount.getBalance());
		callableStatement.setDouble(4, bankAccount.getBalance());
		callableStatement.setString(5, bankAccount.getAccountStatus());

		callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
		
		callableStatement.execute();
		
		bankAccount.setAccountID(callableStatement.getString(1));   
		
		} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("SYSTEM: An internal ERROR has occured, please contact SYSADMIN");
		}
	return bankAccount;
	}
	
	@Override
	public bankAccount getAccountInfo(String cId, String aType) throws BusinessException {
		bankAccount bAccount=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select ACCOUNTID,CUSTOMERID,ACOUNTTYPE,BALANCE,ACCOUNTSTATUS from ACCOUNT where CUSTOMERID=? and ACOUNTTYPE=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, cId);
			preparedStatement.setString(2, aType);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				bAccount = new bankAccount();
				bAccount.setAccountID(resultSet.getString("USERID"));
				bAccount.setCustomerID(cId);
				bAccount.setAccountType(resultSet.getString("ACOUNTTYPE"));
				bAccount.setBalance(resultSet.getLong("BALANCE"));
				bAccount.setAccountStatus(resultSet.getString("ACCOUNTSTATUS"));
			}else {
				throw new BusinessException("SYSTEM: CustomerID or Account Type entered is incorrect");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: An internal ERROR has occured, please contact SYSADMIN");
		}
		return bAccount;
	}

	@Override
	public void deleteBankAccount(String cId, String aType) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="DELETE from ACCOUNT where CUSTOMERID=? and ACOUNTTYPE=?";				
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, cId);
			preparedStatement.setString(1, aType);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
	}

	@Override
	public void updateBankAccountBalance(String cId, String aType, Double aBalance) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="UPDATE ACCOUNT SET BALANCE=? where CUSTOMERID=? and ACOUNTTYPE=?";			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setDouble(1, aBalance);
			preparedStatement.setString(2, cId);
			preparedStatement.setString(3, aType);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
	}

	@Override
	public void updateBankAccountStatus(String cId, String aType, String aStatus) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
			String sql="UPDATE ACCOUNT SET ACCOUNTSTATUS=? where CUSTOMERID=? and ACOUNTTYPE=?";			
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, aStatus);
			preparedStatement.setString(2, cId);
			preparedStatement.setString(3, aType);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
	}

	@Override
	public List<bankAccount> getAllBankAccounts() throws BusinessException {
		List<bankAccount> bankAccountList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select ACCOUNTID,CUSTOMERID,ACOUNTTYPE,BALANCE,ACCOUNTSTATUS from ACCOUNT";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				bankAccount bAccount = new bankAccount();
				bAccount.setAccountID(resultSet.getString("USERID"));
				bAccount.setCustomerID(resultSet.getString("CUSTOMERID"));
				bAccount.setAccountType(resultSet.getString("ACOUNTTYPE"));
				bAccount.setBalance(resultSet.getLong("BALANCE"));
				
				bAccount.setAccountStatus(resultSet.getString("ACCOUNTSTATUS"));
//				bAccount.setBalance(resultSet.);
				bankAccountList.add(bAccount);	
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return bankAccountList;
	}
	
//	@Override
//	public List<customer> getAllCustomers() throws BusinessException {
//		List<customer> customerList = new ArrayList<>();
//		try(Connection connection=OracleConnection.getConnection()){
//			String sql="Select CUSTOMERID,LASTNAME,FIRSTNAME,EMAIL,CONTACT,DOB,STREET,CITY,STATE from CUSTOMER";
//			PreparedStatement preparedStatement=connection.prepareStatement(sql);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			while(resultSet.next()) {			
//				customer cust=new customer();
//				cust.setCustomerID(resultSet.getString("CUSTOMERID"));
//				cust.setLastName(resultSet.getString("LASTNAME"));
//				cust.setFirstName(resultSet.getString("FIRSTNAME"));
//				cust.setEmailAddress(resultSet.getString("EMAIL"));
//				cust.setContactNumber(resultSet.getLong("CONTACT"));
//				cust.setDob(resultSet.getDate("DOB"));
//				cust.setStreet(resultSet.getString("STREET"));
//				cust.setCity(resultSet.getString("CITY"));
//				cust.setState(resultSet.getString("STATE"));	
//				customerList.add(cust);
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			throw new BusinessException("Internal Error contact SYSADMIN");
//		}
//		
//		return customerList;
//	}
//	
	
	

}
