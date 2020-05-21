package bank.transaction.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.utilities.DAOUtilites;
import exception.validations.Validation;
import log.Log;
import user.cust.account.controller.UserOptionsDirectory;
import user.cust.account.models.User;

public class TransactionDaoImpl implements TransactionDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean createTransaction(User user, String trans) {

		Validation v = new Validation();

		try {

			conn = DAOUtilites.getConnection();

			// ps = conn.prepareStatement("INSERT INTO b_user(user_id, username, password,
			// email, contact) values(1, ?, ?, ?, ?)");

			// stored procedure: "b_user_pr"
			String sql = "{call trans_pr(?,?,?)}";
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, user.getUser_id());
			callableStatement.setString(3, trans);

		

				

					if (callableStatement.executeUpdate() != 0) {
						Log.logger("Transaction recorded...");
						//System.exit(0);
						return true;
					} else {
						Log.logger("Sorry something went wrong at the database");
						return false;
					}

				

			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();

		}
		return false;
	}
	
	
	@Override
	public boolean viewAllTransactions() {
		

		try {
			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM transaction");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Log.logger("" 
						+ rs.getString("trans_id")
						+ "\t" + rs.getString("user_id")
						+ "\t" + rs.getString("trans_data"));
			}
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		return false;
	}
	
	
	@Override
	public boolean viewCustTransactions(User user) {
		
		try{

			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM transaction WHERE user_id=?");
			ps.setString(1, user.getUser_id());
			
			rs = ps.executeQuery();
			
			
			while (rs.next()) {			

				Log.logger("" 
						+ rs.getString("trans_id")
						+ "\t" + rs.getString("user_id")
						+ "\t" + rs.getString("trans_data"));
			} 
			return true;
			
				
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		
		return false;
	}
	
	

	private void closeResources() {

		try {
			if (rs != null && !rs.isClosed()) {
				// System.out.println("Closed result set...");
				rs.close();
			}
		} catch (Exception e) {
			// System.out.println("Could not close result set !");
			e.printStackTrace();
		}

		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			// System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (conn != null) {
				conn.close();
				// System.out.println("Closing down connection...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}






}
