package com.bhank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bhank.dao.TransactionDAO;
import com.bhank.dbutil.OracleConnection;
import com.bhank.exception.BusinessException;
import com.bhank.main.Main;
import com.bhank.model.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public Transaction postTransaction(Transaction transaction) throws BusinessException {

		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call CREATETRANSACTION(?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(2, transaction.getFromAccountId());
			callableStatement.setString(3, transaction.getToAccountId());
			callableStatement.setString(4, transaction.isDeposit() ? "t" : "f");
			callableStatement.setString(5, "y");
			callableStatement.setString(6, "n");

			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

			callableStatement.execute();
			transaction.setId(callableStatement.getString(1));

		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to created transaction in database");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully created transaction in database");
		return transaction;
	}

	@Override
	public Transaction acceptTransaction(Transaction transaction) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "update transaction set isaccepted='t', ispending='n' where id= \'"+transaction.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate(sql);
			
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to approve transaction of transaction id \""+transaction.getId()+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Transaction DAO successfully approved transaction of transaction id \""+transaction.getId()+"\"");
		return transaction;
	}

	@Override
	public Transaction declineTransaction(Transaction transaction) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "update transaction set isaccepted='n', ispending='n' where id= \'"+transaction.getId()+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			int resultSet = preparedStatement.executeUpdate(sql);
			
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to decline transaction of transaction id \""+transaction.getId()+"\"");
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		Main.logger.info("Transaction DAO successfully declined transaction of transaction id \""+transaction.getId()+"\"");
		return transaction;
	}

	@Override
	public Transaction selectTransactionsByAccount(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction selectAllTransactions() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
