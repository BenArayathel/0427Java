package com.trainee.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trainee.dao.TraineeDAO;
import com.trainee.dbutil.OracleConnection;
import com.trainee.exception.BusinessException;
import com.trainee.model.Trainee;

public class TraineeDaoImpl implements TraineeDAO{

	@Override
	public Trainee createTrainee(Trainee trainee) throws BusinessException {
		
		try(Connection connection=OracleConnection.getConnection()){
			String sql="{call CREATETRAINEE(?,?,?,?,?,?)}";
			CallableStatement callableStatement=connection.prepareCall(sql);
			callableStatement.setString(2, trainee.getName());
			callableStatement.setLong(3, trainee.getContact());
			callableStatement.setString(4, trainee.getEmail());
			callableStatement.setDate(5, new java.sql.Date(trainee.getDob().getTime()));
			callableStatement.setString(6, trainee.getCity());
			
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			callableStatement.execute();
			
			trainee.setId(callableStatement.getString(1));
			
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		
		return trainee;
	}

	@Override
	public Trainee getTraineeById(String id) throws BusinessException {
		Trainee t=null;
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select name,contact,dob,email,city from trainee where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				t=new Trainee();
				t.setId(id);
				t.setName(resultSet.getString("name"));
				t.setCity(resultSet.getString("city"));
				t.setDob(resultSet.getDate("dob"));
				t.setContact(resultSet.getLong("contact"));
				t.setEmail(resultSet.getString("email"));
			
			}else {
				throw new BusinessException("Trainee Id "+id+" doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return t;
	}

	@Override
	public Trainee updateTraineeContact(String id, long newContact) throws BusinessException {
		Trainee t = null;
		
		try(Connection connection=OracleConnection.getConnection()) {
			String sql="UPDATE trainee SET contact = ? WHERE id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, newContact);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			t = getTraineeById(id);
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
		
		return t;
	}

	@Override
	public void deleteTrainee(String id) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection()) {
			String sql="DELETE FROM trainee WHERE id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured please contact SYSADMIN");
		}
	}

	@Override
	public List<Trainee> getTraineesByCity(String city) throws BusinessException {
		List<Trainee> traineeList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select id,name,contact,dob,email,city from trainee WHERE city = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, city);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Trainee t=new Trainee();
				t.setId(resultSet.getString("id"));
				t.setName(resultSet.getString("name"));
				t.setCity(resultSet.getString("city"));
				t.setDob(resultSet.getDate("dob"));
				t.setContact(resultSet.getLong("contact"));
				t.setEmail(resultSet.getString("email"));
				traineeList.add(t);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return traineeList;
	}

	@Override
	public List<Trainee> getAllTrainees() throws BusinessException {
		List<Trainee> traineeList=new ArrayList<>();
		try(Connection connection=OracleConnection.getConnection()){
			String sql="Select id,name,contact,dob,email,city from trainee";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Trainee t=new Trainee();
				t.setId(resultSet.getString("id"));
				t.setName(resultSet.getString("name"));
				t.setCity(resultSet.getString("city"));
				t.setDob(resultSet.getDate("dob"));
				t.setContact(resultSet.getLong("contact"));
				t.setEmail(resultSet.getString("email"));
				traineeList.add(t);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact SYSADMIN");
		}
		
		return traineeList;
	}
	
	

}
