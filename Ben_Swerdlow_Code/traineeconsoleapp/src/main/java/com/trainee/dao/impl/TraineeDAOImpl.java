package com.trainee.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trainee.dao.TraineeDAO;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;

public class TraineeDAOImpl implements TraineeDAO {
	
	/*
	 * For database connection, you should make a singleton class so only 1 database connection is made
	 */

	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {

		try(Connection connection = OracleConnection.getConnection()){
			
			String sqlCall = "{call createtrainee(?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sqlCall);
			
			
			// Tell SQL Procedure that there should be the following input parameters
			callableStatement.setString(2, trainee.getName());
			callableStatement.setLong(3, trainee.getContact());
			callableStatement.setString(4, trainee.getEmail());
			// Need to convert Date to appropriate SQL date
			System.out.println(trainee.getDob());
			System.out.println(new java.sql.Date(trainee.getDob().getTime()));
			callableStatement.setDate(5, new java.sql.Date(trainee.getDob().getTime()));
			callableStatement.setString(6, trainee.getCity());
			
			// Tell SQL Procedure that there should be an out parameter first of type VARCHAR
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			callableStatement.execute();
			System.out.println(callableStatement.getString(1));
			trainee.setId(callableStatement.getString(1));
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			throw new BusinessException("Internal error occurred. Please contact SYSADMIN");
		}
		return null;
	}

	@Override
	public Trainee getTraineeById(String id) throws BusinessException {
		Trainee trainee = null;
		
		try (Connection connection = OracleConnection.getConnection()){
			String sql = "SELECT id, name, contact, dob, email, city FROM trainee WHERE id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				trainee = new Trainee();
				trainee.setId(resultSet.getString("id"));
				trainee.setName(resultSet.getString("name"));
				trainee.setCity(resultSet.getString("city"));
				trainee.setDob(resultSet.getDate("dob"));
				trainee.setContact(resultSet.getLong("contact"));
				trainee.setEmail(resultSet.getString("email"));
			} else {
				throw new BusinessException("Trainee Id "+id+" doesn't exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println(e.getMessage());
			throw new BusinessException("Internal Error. Please contact SYSADMIN.");
		}
		return trainee;
	}

	@Override
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTrainee(String id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trainee> getTraineesByCity(String city) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override 
	public List<Trainee> getAllTrainees() throws BusinessException {
		List<Trainee> traineeList = new ArrayList<>();
		
		try (Connection connection = OracleConnection.getConnection()){
			String sql = "SELECT id, name, contact, dob, email, city FROM trainee";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				Trainee t = new Trainee();
				t.setId(resultSet.getString("id"));
				t.setName(resultSet.getString("name"));
				t.setCity(resultSet.getString("city"));
				t.setDob(resultSet.getDate("dob"));
				t.setContact(resultSet.getLong("contact"));
				t.setEmail(resultSet.getString("email"));
				traineeList.add(t);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error. Please contact SYSADMIN.");
		}
		return traineeList;
	}

}
