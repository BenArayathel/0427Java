package com.application.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.application.bank.dao.UserDao;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import com.application.bank.secrets.SecretStuff;

public class UserDaoImpl implements UserDao {
	private static String myAws = SecretStuff.getAwsKey();
	public static final String URL =
			"jdbc:oracle:thin:@database-1." + myAws + ".us-east-2.rds.amazonaws.com:1521:orcl";
	public static final String USERNAME = SecretStuff.getAwsUserName();
	public static final String PASSWORD = SecretStuff.getAwsPassword();
	final static Logger loggy = Logger.getLogger(User.class);
	
	public UserDaoImpl() {
		
	}

	@Override
	public User insertUser(User u) throws BusinessException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String sql = "INSERT INTO bankuser VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement("call CREATEBANKUSER(?,?,?,?,?,?)");
		
			
			ps.setString(1,  "");
			ps.setString(2, u.getName());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPhoneNumber());
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getStatus());
			loggy.info("Creating new user\n");
			ps.executeUpdate();
			loggy.info("Inserting new User\n");
			loggy.info("Returning newly created user");
			u = this.selectUserByEmail(u.getEmail());
			
		} catch (SQLException e) {
			loggy.warn("CREATEBANKUSER SQLException caught- " + e);
			e.printStackTrace();
		}
		
		return u;
		
	}

	@Override
	public User updateUser(String userEmail, String columnName, String newAttribute) throws BusinessException{
		User uTwo = new User();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("UPDATE bankuser SET " + columnName + " = '" + newAttribute + "' WHERE email = '"+ userEmail + "'");
			//ps.setString(0, userEmail);
			//ps.setString(1, newAttribute);
			//ps.setString(2, userEmail);
			ps.executeUpdate();
			uTwo = this.selectUserByEmail(userEmail);
			
		} catch (SQLException e) {
			loggy.warn("Caught SQLException");
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		
		return uTwo;
		
	}

	@Override
	public User selectUserByEmail(String uEmail) throws BusinessException{
		User u = new User();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			loggy.warn("Caught SQLException");
			e.printStackTrace();
			throw new BusinessException("Internal Error while updating. Contact SYSADMIN");
			
		}
		return u;
	}
	
	@Override
	public User selectUserByColumnName(String cName, String cValue) throws BusinessException {
		User u = new User();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser WHERE " + cName + " = '" + cValue + "'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPhoneNumber(rs.getString("phone"));
				u.setPassword(rs.getString("password"));
				u.setStatus(rs.getString("status"));
			}	
		} catch (SQLException e) {
			loggy.warn("Caught SQLException- " + e);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		return u;
	}

	@Override
	public List<User> selectAllUsers() throws BusinessException {
		List<User> uList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			User u = new User();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM bankuser");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				uList.add(new User(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("password"), rs.getString("status")));
				
			}	
		} catch (SQLException e) {
			loggy.warn("Caught SQLException- " + e);
			throw new BusinessException("Internal Error. Contact SYSADMIN");
		}
		for (User uCount: uList) {
			System.out.println(uCount);
		}
		return uList;
		
	}

	@Override
	public void deleteUser(String uEmail) throws BusinessException{
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankuser WHERE email = '" + uEmail + "'");
			
			ps.execute();
			loggy.info("Record deleted");
			loggy.error("Oh god, it's gone. Oh, the humanity!");
		} catch (SQLException e) {
			loggy.error("SQLException- " + e);
			
			e.printStackTrace();
		}
		
	}
	
	public void deleteAllUsers() throws BusinessException {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM bankuser");
			
			ps.execute();
			loggy.info("Delete all rows");
		} catch (SQLException e) {
			loggy.warn("Caught SQLException- " + e);
			e.printStackTrace();
		}
	}


	

}
