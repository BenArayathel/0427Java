package com.animal.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.app.dao.AnimalDao;
import com.animal.app.model.Animal;
import com.animal.app.service.AnimalService;



@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDao animalDao;
	
	@Override
	public Animal createAnimal(Animal animal) {		
		return animalDao.save(animal);
	}

	@Override
	public Animal updateAnimal(Animal animal) {
		return animalDao.save(animal);
	}

	@Override
	public Animal getAnimalById(int id) {
		return animalDao.findById(id).get();
	}

	@Override
	public void deleteAnimalById(int id) {
		animalDao.deleteById(id);
	}

	@Override
	public List<Animal> getAllAnimals() {
		return animalDao.findAll();

	}

	@Override
	public List<Animal> getAnimalsByAge(int age) {
		return animalDao.findByAge(age);
	}

}
