package com.animal.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.app.dao.AnimalDao;
import com.animal.app.model.Animal;
import com.animal.app.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	// Eh, just don't do this in practice. Autowire the constructor
	@Autowired
	private AnimalDao animalDao;
	
	@Override
	public Animal createAnimal(Animal a) {
		// TODO Auto-generated method stub
		// This isn't the hibernate save, it's JpaRepo save, 
		// which is the same as saveOrUpdate method from Hibernate
		return animalDao.save(a);
	}

	@Override
	public Animal updateAnimal(Animal a) {
		// TODO Auto-generated method stub
		// This isn't the hibernate save, it's JpaRepo save, 
		// which is the same as saveOrUpdate method from Hibernate
		return animalDao.save(a);
	}

	@Override
	public Animal getAnimalById(int id) {
		// TODO Auto-generated method stub
		// returns Optional<Object> and use .get() to get the Object
		return animalDao.findById(id).get();
	}

	@Override
	public void deleteAnimalById(int id) {
		// TODO Auto-generated method stub
		animalDao.deleteById(id);
	}

	@Override
	public List<Animal> getAllAnimals() {
		// TODO Auto-generated method stub
		return animalDao.findAll();
	}

	@Override
	public List<Animal> getAnimalsByAge(int age) {
		// TODO Auto-generated method stub
		return animalDao.findByAge(age);
	}

}
