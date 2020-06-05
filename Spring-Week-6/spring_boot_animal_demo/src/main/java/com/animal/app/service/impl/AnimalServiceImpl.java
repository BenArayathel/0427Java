package com.animal.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.app.dao.AnimalDAO;
import com.animal.app.model.Animal;
import com.animal.app.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDAO animalDAO;
	
	@Override
	public Animal createAnimal(Animal animal) {
		return animalDAO.save(animal);
	}

	@Override
	public Animal updateAnimal(Animal animal) {
		
		return animalDAO.save(animal);
	}

	@Override
	public Animal getAnimalById(int id) {
		return animalDAO.findById(id).get();
	}

	@Override
	public void deleteAnimalById(int id) {
		animalDAO.deleteById(id);
		
	}

	@Override
	public List<Animal> getAllAnimals() {
		return animalDAO.findAll();
	}

	@Override
	public List<Animal> getAnimalsByAge(int age) {
		return animalDAO.findByAge(age);
	}

}
