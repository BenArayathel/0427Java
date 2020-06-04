package com.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.mapper.PlanetMapper;
import com.example.model.Planet;

public class PlanetDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public String toString() {
		return "PlanetDao [dataSource=" + dataSource + ", jdbcTemplate=" + jdbcTemplate + "]";
	}
	
	public void insertPlanet(Planet p) {
		String sql = "INSERT INTO planet(id,name) values(?,?)";
		// Passing in the question marks with the jdbcTemplate. Cool!
		jdbcTemplate.update(sql, p.getPlanetId(), p.getPlanetName());
	}
	
	public Planet selectPlanetById(String id) {
		String sql = "SELECT * FROM planet(id, name) WHERE id=?";
		Planet p = jdbcTemplate.queryForObject(sql, new Object[] {id}, new PlanetMapper());
		return p;
	}
	
	public List<Planet> selectAllPlanets() {
		String sql = "SELECT * FROM planet";
		List<Planet> planetList = jdbcTemplate.query(sql, new PlanetMapper());
		return planetList;
	}
	
}
