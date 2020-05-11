package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Planet;

public class PlanetDaoImplementation implements PlanetDao {
	
	/*
	 * JDBC - Java Database Connectivity
	 * 
	 * Allows you to communicate with our database :D
	 * 
	 * important interfaces
	 * 		Connection -0> Allows us to connect to our DB
	 * 		Statement -> Raw SQL queries (will throw SQLInjection errors; don't use these)
	 * 		PreparedStatement -> Precompiles the SQL string without params, once params are added, they are only treated as values,
	 * 								NEVER keywords (prevents SQL Injection)
	 * 		CallableStatement -> Same ideas as PreparedStatement, but is used for stored procedures
	 * 
	 * 
	 * Both prepared and callable prevent SQL injections :)
	 * 
	 * What do we need to connect to our database?
	 * 		URL
	 * 		Username
	 * 		Password
	 * 		A driver (jar that implements JDBC)
	 */
	
//	private static String url = 
//			"jdbc:oracle:thin:@<endpoint>:<portNumber>:orcl";
	private static String url = 
			"jdbc:oracle:thin:@myfirstorcl.cyw8bxzbivob.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "admin";
	private static String password = "e85c6^a*s%TuWRg&";

	@Override
	public void insertPlanet(Planet p) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO planets VALUES(?,?,?,?,?)");
			// This prevents us from accidentally passing in "Jonny; DROP TABLE;"
			ps.setInt(1, p.getPlanetId());//will replace the first ? in values with planetID
			ps.setString(2, p.getPlanetName());//
			ps.setBoolean(3, p.isHasRings());//automatically converts boolean to 0 or 1 in SQL
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updatePlanet(Planet p) {
		
	}

	@Override
	public List<Planet> selectAllPlanets() {
		List<Planet> planets = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets");
			
			ResultSet rs = ps.executeQuery();
//			int count = 0;
			while (rs.next()) {
				//System.out.println(rs.getInt("PLANET_ID")+rs.getString("PLANET_NAME")+rs.getString("SLOGAN")+rs.getBoolean("HAS_RINGS")+rs.getInt("NUMBER_OF_MOONS"));
				planets.add(
						/*Columns can be accessed by name or by index (not 0 indexed, i.e. column[2] gets the 2nd column )*/
						new Planet(rs.getInt("PLANET_ID"), rs.getString("PLANET_NAME"),
						rs.getString("SLOGAN"), rs.getBoolean("HAS_RINGS"), rs.getInt("NUMBER_OF_MOONS")));
//				System.out.println(planets.get(count));
//				count++;
			}
		} catch (SQLException e) {
			//prevent bad stuff
			//loggy.warn("Not connected: ", e);
			e.printStackTrace();
		}
		return planets;
	}

	@Override
	public Planet selectPlanetByName(String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planet selectPlanetByID(String planetID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlanet() {
		// TODO Auto-generated method stub
		
	}

}
