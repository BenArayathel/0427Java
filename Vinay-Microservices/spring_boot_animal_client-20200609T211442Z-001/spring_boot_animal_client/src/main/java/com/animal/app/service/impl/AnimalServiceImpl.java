package com.animal.app.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.animal.app.model.Animal;
import com.animal.app.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	private String url="http://localhost:9000/animal/";
	
	private RestTemplate restTemplate=new RestTemplate();
	
	@Override
	public Animal createAnimal(Animal animal) {
		return restTemplate.postForObject(url,animal,Animal.class);
	}

	@Override
	public Animal updateAnimal(Animal animal) {
		restTemplate.put(url, animal);
		return animal;
	}

	@Override
	public Animal getAnimalById(int id) {
		ResponseEntity<Animal> entity=restTemplate.exchange(url+id, HttpMethod.GET,null,Animal.class);
		return entity.getBody();
	}

	@Override
	public void deleteAnimalById(int id) {
		restTemplate.delete(url+id);
		
	}

	@Override
	public List<Animal> getAllAnimals() {
		ResponseEntity<List<Animal>> entity=restTemplate.exchange("http://localhost:9000/animals",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Animal>>() {
				});
		return entity.getBody();
	}

	@Override
	public List<Animal> getAnimalsByAge(int age) {
		return null;
	}

}
