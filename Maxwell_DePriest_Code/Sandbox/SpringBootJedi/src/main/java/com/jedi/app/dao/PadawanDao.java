package com.jedi.app.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.jedi.app.model.Padawan;

@Repository 
public interface PadawanDao extends JpaRepository<Padawan, Integer> {
	
}
