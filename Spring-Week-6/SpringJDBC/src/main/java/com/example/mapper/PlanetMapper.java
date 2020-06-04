package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Planet;

public class PlanetMapper implements RowMapper<Planet>{
	
	//This class will map our sql results (resultset) to our Model object, in this case Planet

	@Override
	public Planet mapRow(ResultSet rs, int rowNum) throws SQLException {

		Planet planet = new Planet();
		planet.setPlanetId(rs.getInt("id")); //You can either reference the column row or the name of the sql column
		planet.setPlanetName(rs.getString("name")); 
		
		return planet;
	}
	
	

}
