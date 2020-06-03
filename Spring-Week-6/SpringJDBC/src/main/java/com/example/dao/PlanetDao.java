package com.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.mapper.PlanetMapper;
import com.example.model.Planet;


public class PlanetDao {
	
	//Datasource will specify the necessary information for our connection
	private DataSource dataSource;
	
	//JdbcTemplate will be used to pass through queries into our DB
	private JdbcTemplate jdbcTemplate;

	//Offer a setter method for Spring to inject our dependencies 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	
	public void insertPlanet(Planet p) {
		String sql = "INSERT INTO planet(id,name) values(?,?)";
		jdbcTemplate.update(sql, p.getPlanetId(), p.getPlanetName());
		
	}
	
	public void selectPlanetById(int id) {
		String sql = "SELECT * FROM planet(id,name) WHERE id = ?";
		Planet p = jdbcTemplate.queryForObject(sql, new Object[] {id}, new PlanetMapper());
		
	}
	
	public List<Planet> selectAllPlanets() {
		String sql = "SELECT * FROM planet";
		List<Planet> planetList = jdbcTemplate.query(sql, new PlanetMapper());
		return planetList;
	}
	
	

}
