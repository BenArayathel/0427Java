package com.bank.dao_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class UserDAOImplementation implements UserDAOInterface {

	@Override
	public User createUser(User user) throws BankException {
		
		try (Connection conn = DataConnection.getConnection()) {
			
			String sql = "{call create_new_user(?,?,?,?)}";
			CallableStatement cb = conn.prepareCall(sql);
			//for now i'm writing in variables, later i'll use setters/getters
			cb.setString(2, "monkey");
			cb.setString(3, "business");
			cb.setInt(4, 0);
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			//i'm trying int, it might need to be string
			user.setUser_id(cb.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("Something went wrong here");
		}
		
		return user;
	}
	
//	@Override
//	public Trainee createTrainee(Trainee trainee) throws BusinessException {
//		
//		try(Connection connection=OracleConnection.getConnection()){
//			String sql="{call CREATETRAINEE(?,?,?,?,?,?)}";
//			CallableStatement callableStatement=connection.prepareCall(sql);
//			callableStatement.setString(2, trainee.getName());
//			callableStatement.setLong(3, trainee.getContact());
//			callableStatement.setString(4, trainee.getEmail());
//			callableStatement.setDate(5, new java.sql.Date(trainee.getDob().getTime()));
//			callableStatement.setString(6, trainee.getCity());
//			
//			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
//			
//			callableStatement.execute();
//			
//			trainee.setId(callableStatement.getString(1));
//			
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			throw new BusinessException("Internal error occured please contact SYSADMIN");
//		}
//		
//		return trainee;
//	}

}
