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
			//for now i'm writing in variables, later i'll use setters/getters
			cb.setString(2, user.getUsername());
			cb.setString(3, user.getPassword());
			//approved will stay at 0, and only get changed by employee later
			cb.setInt(4, 0);
			
			cb.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cb.execute();
			
			//i'm trying int, it might need to be string
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
		
		//WHEN I GET BACK:
		//continue referencing planets to see logic to print out list of all users, call from main,
		//list them all out
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
		
		System.out.println("THE LIST USER METHOD IS WORKING");
		System.out.println(userList.toString() + " this is the user!!");
		return userList;
	}
	
//	@Override
//	public List<Planet> selectAllPlanets() {
//		
//		//loggy.info("Trying to connect")
//		List<Planet> planets = new ArrayList<Planet>();
//		try(Connection conn = DriverManager.getConnection(url, username, password)){
//			
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets");
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				planets.add(
//						new Planet(rs.getInt("planet_id"), rs.getString(2),rs.getBoolean(3) , rs.getInt(4), rs.getString(5)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//loggy.warn("Not connected",Exception e);
//			e.printStackTrace();
//		}
//		return planets;
//	}
	

}
