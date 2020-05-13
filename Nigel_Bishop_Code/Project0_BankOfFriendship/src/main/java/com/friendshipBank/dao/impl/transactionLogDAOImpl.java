package com.friendshipBank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
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
		String sql="{call CREATETRANSACTION(?,?,?,?,?,?,?)}";
		CallableStatement callableStatement=connection.prepareCall(sql);
		callableStatement.setString(2, transaction.getAccountID());
		callableStatement.setString(3, transaction.getAccountType());
		callableStatement.setDouble(4, transaction.getBalance());
		callableStatement.setDouble(5, transaction.getWithdrawl());
		callableStatement.setDouble(6, transaction.getDeposit());
		callableStatement.setDouble(7, transaction.getTransfer());

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
			String sql="Select TRANSACTIONID,ACCOUNTID,ACOUNTTYPE,BALANCE, WITHDRAWL, DEPOSIT, TRANSFER from LOGTRANSACTION";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {	
				transaction transaction = new transaction();
				
				transaction.setTransactionID(resultSet.getString("TRANSACTIONID"));
				transaction.setAccountID(resultSet.getString("ACCOUNTID"));
				transaction.setAccountType(resultSet.getString("ACOUNTTYPE"));
				transaction.setBalance(resultSet.getDouble("BALANCE"));
				transaction.setWithdrawl(resultSet.getDouble("WITHDRAWL"));
				transaction.setDeposit(resultSet.getDouble("DEPOSIT"));
				transaction.setTransfer(resultSet.getDouble("TRANSFER"));
				transList.add(transaction);	
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("SYSTEM: AN INTERNAL ERROR HAS OCCURED, PLEASE CONTACT YOUR SYSTEM ADMINISTRATOR (INFO ALL TRAN)");
		}
		
		return transList;
	}

}
