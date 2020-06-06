package com.jedi.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jedi.app.model.Jedi;

@Repository 
public interface JediDao extends JpaRepository<Jedi, Integer> {
	public List<Jedi> findJediByRank(String rank);
}
