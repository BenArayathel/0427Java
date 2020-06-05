package com.animal.app.service;

import java.util.List;

import com.animal.app.model.Animal;

public interface AnimalService {
	
	public Animal createAnimal(Animal a);
	public Animal updateAnimal(Animal a);
	public Animal getAnimalById(int id);
	public void deleteAnimalById(int id);
	public List<Animal> getAllAnimals();
	
	public List<Animal> getAnimalsByAge(int age);

}
