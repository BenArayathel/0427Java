package com.animal.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animal.app.model.Animal;

@Repository
// sets all basic CRUD operations through JpaRepository!
public interface AnimalDao extends JpaRepository<Animal, Integer> {
	
	// by extending JpaRepository, it understands findBy___ means find by the first argument!
	// you don't even have to write anything!
	public List<Animal> findByName(String name);
	public List<Animal> findByAge(int age);

}
