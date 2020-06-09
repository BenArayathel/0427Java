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
	
	private String url = "http://localhost:9000/animal/";
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public Animal createAnimal(Animal a) {
		// TODO Auto-generated method stub
		// This isn't the hibernate save, it's JpaRepo save, 
		// which is the same as saveOrUpdate method from Hibernate
//		return animalDao.save(a);
		return restTemplate.postForObject(url, a, Animal.class);
	}

	@Override
	public Animal updateAnimal(Animal a) {
		// TODO Auto-generated method stub
		// This isn't the hibernate save, it's JpaRepo save, 
		// which is the same as saveOrUpdate method from Hibernate
//		return animalDao.save(a);
		restTemplate.put(url, a);
		return a;
	}

	@Override
	public Animal getAnimalById(int id) {
		// TODO Auto-generated method stub
		// returns Optional<Object> and use .get() to get the Object
//		return animalDao.findById(id).get();
		ResponseEntity<Animal> entity = restTemplate.exchange(url+id, HttpMethod.GET, null, Animal.class);
		return entity.getBody();
	}

	@Override
	public void deleteAnimalById(int id) {
		// TODO Auto-generated method stub
//		animalDao.deleteById(id);
		restTemplate.delete(url+id);
	}

	@Override
	public List<Animal> getAllAnimals() {
		// TODO Auto-generated method stub
//		return animalDao.findAll();
		ResponseEntity<List<Animal>> entity = restTemplate.exchange(
				"http://localhost:9000/animals", HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Animal>>() {});
		return entity.getBody();
	}

	@Override
	public List<Animal> getAnimalsByAge(int age) {
		// TODO Auto-generated method stub
//		return animalDao.findByAge(age);
		ResponseEntity<List<Animal>> entity = restTemplate.exchange(url+"age/"+age, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Animal>>() {});
		return entity.getBody();
	}

}
