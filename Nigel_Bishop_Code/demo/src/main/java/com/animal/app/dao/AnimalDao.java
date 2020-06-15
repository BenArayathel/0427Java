package com.animal.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animal.app.model.Animal;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Integer>{

	public List<Animal> findByAge(int age);
}
