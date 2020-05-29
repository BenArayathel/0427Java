package com.project1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project1.dao.UserDAO;
import com.project1.dbutil.OracleConnection;
import com.project1.exception.BusinessException;
import com.project1.model.User;

public class UserDaoImpl implements UserDAO {

	public void createUser(User user) throws BusinessException {
		
		try(Connection connection=OracleConnection.getConnection()){
			String sql="INSERT INTO users VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setBoolean(3, user.isEmployee());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public User getUser(String userName, String password) throws BusinessException {
		User u = null;
		
		try(Connection connection=OracleConnection.getConnection()) {
			String sql = "SELECT user_name, password, is_employee FROM users WHERE user_name = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				u = new User();
				u.setUserName(userName);
				u.setPassword(password);
				u.setEmployee(resultSet.getBoolean(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Database error - please contact administrator");
		}
		return u;
	}
	
	public User getUserByName(String userName) throws BusinessException {
		User u = null;
		
		try(Connection connection=OracleConnection.getConnection()) {
			String sql = "SELECT user_name, password, is_employee FROM users WHERE user_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				u = new User();
				u.setUserName(userName);
				u.setPassword(resultSet.getString(2));
				u.setEmployee(resultSet.getBoolean(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Database error - please contact administrator");
		}
		
		return u;
	}
	
	public User makeUserEmployee(String userName) throws BusinessException {
		User u = null;
		
		u = this.getUserByName(userName);
		if (u == null) {
			throw new BusinessException("User does not exist");
		} else if (u.isEmployee()) {
			throw new BusinessException("User is already an approved employee");
		} else {
			try(Connection connection=OracleConnection.getConnection()) {
				String sql = "UPDATE users SET is_employee = 1 WHERE user_name = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userName);
				preparedStatement.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("Database error - please contact administrator");
			}
		}
		
		return u;
	}
	
}
