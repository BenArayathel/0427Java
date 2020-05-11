package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Transaction;
import com.company.view.BankApp;

public class TransactionDaoJdbcImpl implements TransactionDao{
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

    ResultSet rs = null;
    PreparedStatement ps = null;

    private static final String INSERT_TRANSACTION_SQL =
            "insert into transaction (account_id, transaction_type, amount, trans_time) " +
                    "values (?, ?, ?, ?)";

    private static final String SELECT_TRANSACTION_SQL =
            "select * from transaction where transaction_id = ?";

    private static final String SELECT_ALL_TRANSACTIONS_SQL =
            "select * from transaction";

    private static final String UPDATE_TRANSACTION_SQL =
            "update transaction set account_id = ?, transaction_type = ?, amount = ?, trans_time = ? " +
                    "where transaction_id = ?";

    private static final String DELETE_TRANSACTION_SQL =
            "delete from transaction where transaction_id = ?";

	@Override
	public Transaction addTransaction(Transaction transaction) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			String returnCols[] = { "transaction_id" };
			
			// PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps = conn.prepareStatement(INSERT_TRANSACTION_SQL, returnCols);
			
			//ps.setInt(1, customer.getCustomerId());
			ps.setString(1, transaction.getAccountId());
			ps.setString(2, transaction.getTransactionType());
			ps.setBigDecimal(3, transaction.getAmount());
			ps.setTimestamp(4, transaction.getTransTime());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs!= null && rs.next()) {
				transaction.setTransactionId(rs.getLong(1));
				BankApp.loggy.info("Successfully added transaction id: "+rs.getLong(1));
				
			}
			return transaction;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	}


	@Override
	public Transaction getTransaction(long id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_TRANSACTION_SQL);
			ps.setLong(1,id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(rs.getLong("transaction_id"));
				transaction.setAccountId(rs.getString("account_id"));
				transaction.setTransactionType(rs.getString("transaction_type"));
				transaction.setAmount(rs.getBigDecimal("amount"));
				transaction.setTransTime(rs.getTimestamp("trans_time"));
				return transaction;
			} else {
				BankApp.loggy.info("Record not found.");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ALL_TRANSACTIONS_SQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(rs.getLong("transaction_id"));
				transaction.setAccountId(rs.getString("account_id"));
				transaction.setTransactionType(rs.getString("transaction_type"));
				transaction.setAmount(rs.getBigDecimal("amount"));
				transaction.setTransTime(rs.getTimestamp("trans_time"));

		        transactions.add(transaction);
			}
			
			transactions.forEach(c -> BankApp.loggy.info(c));

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
		return transactions;
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(UPDATE_TRANSACTION_SQL);
			ps.setString(1, transaction.getAccountId());
			ps.setString(2, transaction.getTransactionType());
			ps.setBigDecimal(3, transaction.getAmount());
			ps.setTimestamp(4, transaction.getTransTime());
			ps.setLong(5,transaction.getTransactionId());
			
			int updatedRows = ps.executeUpdate();
			
			if (updatedRows == 0) {
				BankApp.loggy.info("No records updated.");
			} else {
				BankApp.loggy.info(updatedRows + " rows updated.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}

	@Override
	public void deleteTransaction(long id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(DELETE_TRANSACTION_SQL);
			ps.setLong(1,id);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				BankApp.loggy.info("No records deleted.");
			} else {
				BankApp.loggy.info(deletedRows + " rows deleted.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}


}
