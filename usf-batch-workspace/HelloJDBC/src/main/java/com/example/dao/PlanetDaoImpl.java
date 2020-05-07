package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Planet;

public class PlanetDaoImpl implements PlanetDao{
	
	/*
	 * JDBC - Java Database Connectivity
	 * 
	 * Important Interfaces;
	 * 		Connection -> Allows us to connect to our DB
	 * 		Statement -> Raw SQL query 
	 * 		PreparedStatements -> Precompiles the SQL string without parameters, once 
	 * 			parameters are added, they are only treated as values, never keywords. 
	 * 		CalableStatement -> same ideas as PreparedStatement but is used for stored procedures. 
	 * 
	 *  Both Prepared and Callable prevent SQL injections
	 *  
	 *  What do we need to connect to our database?
	 *  	1)URL (endpoint:port)
	 *  	2)Username
	 *  	3)Password
	 *  	4) A driver (jar that implements JDBC)
	 */
	
	//"jdbc:oracle:thin:@<endpoint>:1521:orcl"
	// Ben's
	//"jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl"
	private static String url =
			"jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "mybasic";
	private static String password = "34uy34uy";

	@Override
	public void insertPlanet(Planet p) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO planets VALUES(?,?,?,?,?)");
			
			ps.setInt(1, p.getPlanetId());
			ps.setString(2, p.getPlanetName());
			ps.setBoolean(3, p.isHasRings());
			ps.setInt(4, p.getNumberOfMoons());
			ps.setString(5, p.getSlogan());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatePlanet(Planet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Planet> selectAllPlanets() {
		
		//loggy.info("Trying to connect")
		List<Planet> planets = new ArrayList<Planet>();
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				planets.add(
						new Planet(rs.getInt("planet_id"), rs.getString(2),rs.getBoolean(3) , rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//loggy.warn("Not connected",Exception e);
			e.printStackTrace();
		}
		return planets;
	}

	@Override
	public Planet selectPlanetByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlanet(Planet p) {
		// TODO Auto-generated method stub
		
	}

}
