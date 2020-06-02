package com.pzero.v1.persistence.dao.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.dbutil.SingletonDBConnection;
import com.pzero.v1.persistence.models.User;

public class AuthDAOImplementation implements AuthenticationDAO {
	
	final static Logger loggy = Logger.getLogger(AuthDAOImplementation.class);
	
	@Override
	public User authentication(String email, String pwd) throws BusinessException {
		loggy.setLevel(Level.INFO);
		
		User user = null;
		String query = "SELECT EMAIL, USER_TYPE, hp.ID ,hp.NAME, hp.LAST_NAME, hp.PHONE_NUMBER, hp.CITY " + 
						"FROM HB_USER hu INNER JOIN HB_PERSON hp ON hu.PERSON_ID = hp.ID " + 
						"WHERE email = ? AND PASSWORD = ?";
		try (Connection conn = SingletonDBConnection.getConnection()){
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setString(1, email);
			sql.setString(2, pwd);
			ResultSet res = sql.executeQuery();
			if(res.next()) {
				user = new User();
				user.setEmail(res.getString(1));
				user.setUserType(res.getString(2));
				user.setPersonId(res.getString(3));
				user.setName(res.getString(4));
				user.setLastName(res.getString(5));
				user.setPhoneNumber(res.getString(6));
				user.setCityd(res.getString(7));
			}else {
				throw new BusinessException("We can't find that email -> "+ email+" and password. Try again.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			loggy.info("Fatal Error please Contact SYSADMIN.");
			loggy.error(e.getMessage());
		}
		return user;
	}
	
//	@Override
//	public User authentication(String email, String pwd) throws BusinessException {
//		loggy.setLevel(Level.INFO);
//		
//		User user = null;
//		String query = "SELECT id, email, password, person_id, user_type FROM HB_USER hu WHERE email = ? AND password = ?";
//		try (Connection conn = SingletonDBConnection.getConnection()){
//			PreparedStatement sql = conn.prepareStatement(query);
//			sql.setString(1, email);
//			sql.setString(2, pwd);
//			ResultSet res = sql.executeQuery();
//			if(res.next()) {
//				user = new User();
//				user.setId(res.getString(1));
//				user.setEmail(res.getString(2));
//				user.setPassword(res.getString(3));
//				user.setPersonId(res.getString(4));
//				user.setUserType(res.getString(5));
//			}else {
//				throw new BusinessException("We can't find that email -> "+ email+" and password. Try again.");
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			loggy.info("Fatal Error please Contact SYSADMIN.");
//			loggy.error(e.getMessage());
//		}
//		return user;
//	}

}
