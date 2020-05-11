package com.friendshipBank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dbutil.OracleConnection;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
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
				bAccount.setAccountType(aType);
				bAccount.setBalance(resultSet.getDouble("BALANCE"));
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBankAccountBalance(String cId, String aType, Double aBalance) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBankAccountStatus(String cId, String aType, String aStatus) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<bankAccount> getAllBankAccounts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
