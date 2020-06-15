package com.friendshipBank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.dao.transactionLogDAO;
import com.friendshipBank.dbutil.OracleConnection;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;

public class transactionLogDAOImpl implements transactionLogDAO{

	@Override
	public transaction createNewBankTransaction(transaction transaction) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()){
		String sql="{call CREATETRANSACTION2(?,?,?,?,?,?,?)}";
		CallableStatement callableStatement=connection.prepareCall(sql);
		callableStatement.setString(2, transaction.getAccountID());
		callableStatement.setString(3, transaction.getCustomerID());
		callableStatement.setString(4, transaction.getAccountType());
		callableStatement.setDouble(5, transaction.getBalance());
		callableStatement.setDouble(6, transaction.getTransAmount());
		callableStatement.setString(7, transaction.getTransType());
//		callableStatement.setDate(8,transaction.getTransDate());

		callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
		
		callableStatement.execute();
		
		transaction.setAccountID(callableStatement.getString(1));   
		
		} catch (ClassNotFoundException | SQLException e) {
		throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (CREATE TRAN AC)");
		}
	return transaction;
	}

	@Override
	public List<transaction> getAllBankTransaction() throws BusinessException {
		List<transaction> transList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select TRANSACTIONID,ACCOUNTID,CUSTOMERID, ACOUNTTYPE,BALANCE, TRANSAMOUNT, TRANSTYPE, TRANSDATE from TRANSACTIONLOG";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				transaction trans = new transaction();
				
				trans.setTransactionID(resultSet.getString("TRANSACTIONID"));
				trans.setAccountID(resultSet.getString("ACCOUNTID"));
				trans.setCustomerID(resultSet.getString("CUSTOMERID")); 
				trans.setAccountType(resultSet.getString("ACOUNTTYPE"));
				trans.setBalance(resultSet.getDouble("BALANCE"));
				trans.setTransAmount(resultSet.getDouble("TRANSAMOUNT"));
				trans.setTransType(resultSet.getString("TRANSTYPE"));
				trans.setTransDate(resultSet.getDate("TRANSDATE"));
				
				transList.add(trans);	
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO ALL TRAN)");
		}
		return transList;
	}

	@Override
	public List<transaction> getTransactionByAccountID(String aID) throws BusinessException {
		List<transaction> transList = new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select TRANSACTIONID,ACCOUNTID,CUSTOMERID, ACOUNTTYPE,BALANCE, TRANSAMOUNT, TRANSTYPE, TRANSDATE from TRANSACTIONLOG where ACCOUNTID=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, aID);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				transaction trans = new transaction();
				
				trans.setTransactionID(resultSet.getString("TRANSACTIONID"));
				trans.setAccountID(resultSet.getString("ACCOUNTID"));
				trans.setCustomerID(resultSet.getString("CUSTOMERID")); 
				trans.setAccountType(resultSet.getString("ACOUNTTYPE"));
				trans.setBalance(resultSet.getDouble("BALANCE"));
				trans.setTransAmount(resultSet.getDouble("TRANSAMOUNT"));
				trans.setTransType(resultSet.getString("TRANSTYPE"));
				trans.setTransDate(resultSet.getDate("TRANSDATE"));
				
				transList.add(trans);	
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO AID TRAN)");
		}
		return transList;
	}

}
