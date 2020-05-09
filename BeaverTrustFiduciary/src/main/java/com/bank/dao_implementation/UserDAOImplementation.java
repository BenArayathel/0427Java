package com.bank.dao_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao_interface.UserDAOInterface;
import com.bank.models.User;
import com.bank.tools.BankException;
import com.bank.tools.DataConnection;

public class UserDAOImplementation implements UserDAOInterface {
	
	// ADD A NEW USER TO THE DB
	@Override
	public User createUser(User user) throws BankException {
		
		try (Connection conn = DataConnection.getConnection()) {
			
			String sql = "{call create_new_user(?,?,?,?)}";
			CallableStatement cb = conn.prepareCall(sql);
			cb.setString(2, user.getUsername());
			cb.setString(3, user.getPassword());
			//approved will stay at 0, and only get changed by employee later
			cb.setInt(4, 0);
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			//looks like example uses string too
			user.setUser_id(cb.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("Something went wrong here");
		}
		
		return user;
	}

	// LIST OUT ALL THE USERS IN DB
	@Override
	public List<User> listUsers() throws BankException {
		List<User> userList = new ArrayList<User>();
		
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from bank_user");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				userList.add
					(new User(rs.getString("user_id"), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BankException("List User problem");
		}
		
		System.out.println("USERS: " + userList.toString());
		return userList;
	}
	
	
	// LOGGING IN AN EXISTING USER
	@Override
	public boolean loginUser(String username, String password) throws BankException {
		boolean inputVerified;
		String sql = "select * from bank_user where username = ? and password = ?";
		
		try (Connection conn = DataConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
					
			ResultSet rs = ps.executeQuery();
			
			// return a boolean, depending on if it finds the entry
			if (rs.next()) {
				inputVerified = true;
				return inputVerified;

			} else {
				inputVerified = false;
				return inputVerified;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BankException("login failed at dao");
		}
	}
	

}
