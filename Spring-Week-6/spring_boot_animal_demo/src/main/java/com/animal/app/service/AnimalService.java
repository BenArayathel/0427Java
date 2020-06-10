package com.animal.app.service;

import com.animal.app.model.Animal;

import java.util.List;

public interface AnimalService {

	public Animal createAnimal(Animal animal);

	public Animal updateAnimal(Animal animal);

	public Animal getAnimalById(int id);

	public void deleteAnimalById(int id);

	public List<Animal> getAllAnimals();

	public List<Animal> getAnimalsByAge(int age);
}
