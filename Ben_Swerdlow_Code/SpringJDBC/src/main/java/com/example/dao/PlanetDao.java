package com.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.mapper.PlanetMapper;
import com.example.model.Planet;

public class PlanetDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	public PlanetDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlanetDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		System.out.println("In data source setter");
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	public void setJdbcTemplate() {
		System.out.println("In jdbc template setter");
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
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
		String sql = "SELECT * FROM planet(id, name) where id=?";
		Planet p = jdbcTemplate.queryForObject(sql, new Object[] {id}, new PlanetMapper());
		return p;
	}
	
	public List<Planet> selectAllPlanets() {
		String sql = "SELECT * FROM planet";
		List<Planet> planetList = jdbcTemplate.query(sql, new PlanetMapper());
		return planetList;
	}
	
}
