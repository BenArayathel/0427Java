package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.Planet;

public class PlanetMapper implements RowMapper<Planet> {

	@Override
	public Planet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Planet planet = new Planet();
		planet.setPlanetId(rs.getInt("id"));
		planet.setPlanetName(rs.getString("name"));
		return null;
	}

}
