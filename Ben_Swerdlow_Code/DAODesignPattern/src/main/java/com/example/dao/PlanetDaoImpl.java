package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Planet;

public class PlanetDaoImpl implements PlanetDao {
	
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
	 * 
	 * 
	 * This implementing class has the actual implementations of the DAO interface's methods 
	 * 
	 */
	
//	private static String url = 
//			"jdbc:oracle:thin:@<endpoint>:<portNumber>:orcl";
	private static final String URL = /*System.getenv("TRAINING_DB_URL");*/
			"jdbc:oracle:thin:@myfirstorcl.cyw8bxzbivob.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String USERNAME = "puser";
	private static final String PASSWORD = "p4ssw0rd";

	@Override
	public void insertPlanet(Planet p) {
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
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
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM planets");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				planets.add(
						/*Columns can be accessed by name or by index (not 0 indexed, i.e. column[2] gets the 2nd column )*/
//						new Planet(rs.getInt("planet_id"), rs.getString(2),
//						rs.getString(3), rs.getBoolean(4), rs.getInt(4)));
			}
		} catch (SQLException e) {
			//prevent bad stuff
			//loggy.warn("Not connected: ", e);
			e.printStackTrace();
		}
		return null;
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
