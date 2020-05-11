package com.hackbank.persistence.dao.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hackbank.business.exceptions.BusinessException;
import com.hackbank.persistence.dbutil.SingletonDBConnection;

public class AuthDAOImplementation implements AuthenticationDAO {
	
	final static Logger loggy = Logger.getLogger(AuthDAOImplementation.class);
	
	@Override
	public String authentication(String email, String pwd) throws BusinessException {
		loggy.setLevel(Level.INFO);
		
		String userType = null;
		String query = "SELECT user_type FROM HB_USER hu WHERE email = ? AND PASSWORD = ?";
		try (Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setString(1, email);
			sql.setString(2, pwd);
			ResultSet res = sql.executeQuery();
			if(res.next()) {
				userType = res.getString(1);
			}else {
				throw new BusinessException("We can't find that email -> "+ email+" and password. Try again.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.info("Fatal Error please Contact SYSADMIN.");
			loggy.error(e.getMessage());
		}
		return userType;
	}

}
