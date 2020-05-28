package com.bhank.webapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bhank.webapp.dao.TransactionDAO;
import com.bhank.webapp.dbutil.OracleConnection;
import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.main.Main;
import com.bhank.webapp.model.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public Transaction createTransaction(Transaction transaction) throws BusinessException {

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
	public List<Transaction> selectTransactionsByAccount(String accountId) throws BusinessException {
		List<Transaction> transactions = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "select * from transaction where from_account_id=\'"+accountId+"\'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getString("id"));
				transaction.setFromAccountId(resultSet.getString("from_account_id"));
				transaction.setToAccountId(resultSet.getString("to_account_id"));
				transaction.setDeposit(resultSet.getString("isdeposit").equals("y")?true:false);
				transaction.setAccepted(resultSet.getString("isapproved").equals("y")?true:false);
				
				transactions.add(transaction);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to select all transaction");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully selected all transactions");
		return transactions;
	}

	@Override
	public List<Transaction> selectAllTransactions() throws BusinessException {
		List<Transaction> transactions = new ArrayList<>();
		try(Connection connection = OracleConnection.getConnection()) {
			String sql = "select * from transaction";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getString("id"));
				transaction.setFromAccountId(resultSet.getString("from_account_id"));
				transaction.setToAccountId(resultSet.getString("to_account_id"));
				transaction.setDeposit(resultSet.getString("isdeposit").equals("y")?true:false);
				transaction.setAccepted(resultSet.getString("isapproved").equals("y")?true:false);
				
				transactions.add(transaction);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Main.logger.error("Account DAO failed to select all transaction");
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		Main.logger.info("Account DAO successfully selected all transactions");
		return transactions;
	}
}
