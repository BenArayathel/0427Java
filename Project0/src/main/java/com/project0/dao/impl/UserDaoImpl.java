package com.project0.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project0.dao.UserDAO;
import com.project0.dbutil.OracleConnection;
import com.project0.exception.BusinessException;
import com.project0.model.User;

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

}
