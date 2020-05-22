package com.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.dao.UserDao;
import com.exceptions.BusinessException;
import com.models.User;
import com.utilities.DatabaseConnection;



public class UserDaoImpl implements UserDao {

	final static Logger loggy = Logger.getLogger(User.class);
	public UserDaoImpl() {
		
	}

	@Override
	public User insertUser(User u) throws BusinessException {
		
		try (Connection conn = DatabaseConnection.getConn()) {

			PreparedStatement ps = conn.prepareStatement("call CREATEBANKUSER(?,?,?,?,?,?)");
			ps.setString(1,  "");
			ps.setString(2, u.getName());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPhoneNumber());
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getStatus());
			try {
					ps.executeUpdate();			
				} catch (SQLIntegrityConstraintViolationException e) {
					loggy.debug("An account already exists with that email address");
					throw new BusinessException("An account already exists with that email address");
				}
			
			loggy.debug("Created and stored new user with email " + u.getEmail());
			u = this.selectUserByEmail(u.getEmail());
			conn.close();
			
		} catch (SQLException e) {
			loggy.error("SQLException caught- " + e);
			e.printStackTrace();
		}
		return u;
		
	}

	@Override
	public User updateUser(String userEmail, String columnName, String newAttribute) throws BusinessException{
		User uTwo = new User();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("UPDATE bankuser SET " + columnName + " = '" + newAttribute + "' WHERE email = '"+ userEmail + "'");
			ps.executeUpdate();
			uTwo = this.selectUserByEmail(userEmail);
			loggy.debug(userEmail + " updated");
			
		} catch (SQLException e) {
			loggy.warn("Caught SQLException");
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		
		return uTwo;
		
	}

	@Override
	public User selectUserByEmail(String uEmail) throws BusinessException{
		User u = new User();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE email = ?");
			ps.setString(1, uEmail);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPhoneNumber(rs.getString("phone"));
				u.setPassword(rs.getString("password"));
				u.setStatus(rs.getString("status"));
			}	
			loggy.debug("Retrieved user with email " + u.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.error("Caught SQLException");
			e.printStackTrace();
			throw new BusinessException("Internal Error while updating. Contact SYSADMIN");
			
		}
		return u;
	}
	
	@Override
	public User selectUserByColumnName(String cName, String cValue) throws BusinessException {
		User u = new User();
		try (Connection conn = DatabaseConnection.getConn()) {
		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE " + cName + " = '" + cValue + "'");
			ResultSet rs = ps.executeQuery();
			loggy.debug("Retrieved user with " + cName + " = " + cValue);
			while(rs.next()) {
				
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPhoneNumber(rs.getString("phone"));
				u.setPassword(rs.getString("password"));
				u.setStatus(rs.getString("status"));
			}	
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return u;
	}

	@Override
	public List<User> selectAllUsers() throws BusinessException {
		List<User> uList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser");
			ResultSet rs = ps.executeQuery();
			loggy.debug("Selecting all users");
			while(rs.next()) {
				uList.add(new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("password"), rs.getString("status")));
				
			}	
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return uList;
		
	}
	
	public List<User> selectAllUsersByColumnName(String cName, String cValue) throws BusinessException{
		List<User> filteredUserList = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE " + cName + " = '" + cValue + "'");
			ResultSet rs = ps.executeQuery();
			loggy.debug("Selecting all users based on " + cName + " = " + cValue);
			while(rs.next()) {
				filteredUserList.add(new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("password"), rs.getString("status")));
				
			}	
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return filteredUserList;
		
	}

	@Override
	public int deleteUser(String uEmail) throws BusinessException{
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankuser WHERE email = '" + uEmail + "'");
			
			ps.execute();
			loggy.debug("Deleting user with email "+ uEmail);
		} catch (SQLException e) {
			loggy.error("SQLException- " + e);
			
			e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public void deleteAllUsers() throws BusinessException {
		try (Connection conn = DatabaseConnection.getConn()) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankuser");
			
			ps.execute();
			loggy.debug("Delete all rows");
		} catch (SQLException e) {
			loggy.error("Caught SQLException- " + e);
			e.printStackTrace();
		}
	}


	

}
