package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Registration;
import com.company.view.BankApp;

public class RegistrationDaoJdbcImpl implements RegistrationDao {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "bank_test";
	private static final String PASSWORD = "password";

    ResultSet rs = null;
    PreparedStatement ps = null;

    private static final String INSERT_REGISTRATION_SQL =
            "insert into registration (customer_id, login_name, login_password) " +
                    "values (?, ?, ?)";

    private static final String SELECT_REGISTRATION_SQL =
            "select * from registration where registration_id = ?";

    private static final String SELECT_ALL_REGISTRATIONS_SQL =
            "select * from registration";

    private static final String UPDATE_REGISTRATION_SQL =
            "update registration set customer_id = ?, login_name = ?, login_password " +
                    "where registration_id = ?";

    private static final String DELETE_REGISTRATION_SQL =
            "delete from registration where registration_id = ?";
    

    // Service Layer Mandatory Queries
    private static final String UPDATE_REGISTRATION_BY_CUSTOMER_ID_SQL =
            "update registration set login_name = ?, login_password " +
                    "where customer_id = ?";

    private static final String DELETE_REGISTRATION_BY_CUSTOMER_ID_SQL =
            "delete from registration where customer_id = ?";
    
    private static final String SELECT_CUSTOMER_ID_BY_LOGIN_NAME_AND_PASSWORD_SQL =
            "select * from registration where login_name = ? and login_password =?";


    
	@Override
	public Registration addRegistration(Registration registration) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			String returnCols[] = { "registration_id" };
			
			// PreparedStatement ps = conn.prepareStatement(INSERT_REGISTRATION_SQL, Statement.RETURN_GENERATED_KEYS);
			ps = conn.prepareStatement(INSERT_REGISTRATION_SQL, returnCols);
			
			//ps.setInt(1, registration.getRegistrationId());
			ps.setInt(1, registration.getCustomerId());
			ps.setString(2, registration.getLoginName());
			ps.setString(3, registration.getLoginPassword());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs!= null && rs.next()) {
				registration.setRegistrationId(rs.getLong(1));
				BankApp.loggy.info("Successfully added record id: "+rs.getInt(1));
				
			}
			return registration;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	}

	@Override
	public Registration getRegistration(int id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_REGISTRATION_SQL);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Registration registration = new Registration();
				registration.setRegistrationId(rs.getLong("registration_id"));
		        registration.setCustomerId(rs.getInt("customer_id"));
		        registration.setLoginName(rs.getString("login_name"));
		        registration.setLoginPassword(rs.getString("login_password"));
				return registration;
			} else {
				BankApp.loggy.info("Record not found.");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return null;
	
	}
	
	@Override
	public List<Registration> getAllRegistrations() {

		List<Registration> registrations = new ArrayList<Registration>();
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_ALL_REGISTRATIONS_SQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Registration registration = new Registration();
				registration.setRegistrationId(rs.getLong("registration_id"));
		        registration.setCustomerId(rs.getInt("customer_id"));
		        registration.setLoginName(rs.getString("login_name"));
		        registration.setLoginPassword(rs.getString("login_password"));

		        registrations.add(registration);
			}
			
			registrations.forEach(c -> BankApp.loggy.info(c));

		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
		
		return registrations;
	}

	@Override
	public void updateRegistration(Registration registration) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(UPDATE_REGISTRATION_SQL);
			ps.setInt(1, registration.getCustomerId());
			ps.setString(2, registration.getLoginName());
			ps.setString(3, registration.getLoginPassword());
			ps.setLong(4,registration.getRegistrationId());
			
			int updatedRows = ps.executeUpdate();
			
			if (updatedRows == 0) {
				BankApp.loggy.info("No records updated.");
			} else {
				BankApp.loggy.info(updatedRows + " rows updated.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}

	}

	@Override
	public void deleteRegistration(int id) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(DELETE_REGISTRATION_SQL);
			ps.setInt(1,id);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				BankApp.loggy.info("No records deleted.");
			} else {
				BankApp.loggy.info(deletedRows + " rows deleted.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
	}

	@Override
	public void updateRegistrationByCustomerId(Registration registration) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(UPDATE_REGISTRATION_BY_CUSTOMER_ID_SQL);
			ps.setString(1, registration.getLoginName());
			ps.setString(2, registration.getLoginPassword());
			ps.setInt(3,registration.getCustomerId());
			
			int updatedRows = ps.executeUpdate();
			
			if (updatedRows == 0) {
				BankApp.loggy.info("No records updated.");
			} else {
				BankApp.loggy.info(updatedRows + " rows updated.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}

	@Override
	public void deleteRegistrationByCustomerId(int customerId) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(DELETE_REGISTRATION_BY_CUSTOMER_ID_SQL);
			ps.setInt(1,customerId);
			
			int deletedRows = ps.executeUpdate();
			
			if (deletedRows == 0) {
				BankApp.loggy.info("No records deleted.");
			} else {
				BankApp.loggy.info(deletedRows + " rows deleted.");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		
	}

	@Override
	public int getCustomerIdByLoginName(String loginName, String password) {
		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			
			ps = conn.prepareStatement(SELECT_CUSTOMER_ID_BY_LOGIN_NAME_AND_PASSWORD_SQL);
			ps.setString(1,loginName);
			ps.setString(2,password);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Registration registration = new Registration();
				registration.setRegistrationId(rs.getLong("registration_id"));
		        registration.setCustomerId(rs.getInt("customer_id"));
		        registration.setLoginName(rs.getString("login_name"));
		        registration.setLoginPassword(rs.getString("login_password"));
		        
		        // return only the customer ID
				return registration.getCustomerId();
			} else {
				BankApp.loggy.info("Record not found.");
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch(Exception ex){}
		}
		return 0;
	
	}

}
