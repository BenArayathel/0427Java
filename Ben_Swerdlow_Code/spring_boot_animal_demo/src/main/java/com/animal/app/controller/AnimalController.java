package com.animal.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animal.app.model.Animal;
import com.animal.app.service.AnimalService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class AnimalController {

	// *sigh* It's... ok... in a controller. But just autowire the constructor to be consistent.
	@Autowired
	private AnimalService service;

	@PostMapping("/animal")
	public Animal createAnimal(@RequestBody Animal a) {
		// TODO Auto-generated method stub
		return service.createAnimal(a);
	}

	@PutMapping("/animal")
	public Animal updateAnimal(@RequestBody Animal a) {
		return service.updateAnimal(a);
	}
	
	@GetMapping("/animal/{id}")
	public Animal getAnimalById(@PathVariable("id") int id) {
		return service.getAnimalById(id);
	}

	@DeleteMapping("/animal/{id}")
	public void deleteAnimalById(@PathVariable int id) {
		service.deleteAnimalById(id);
	}

	@GetMapping("/animals")
	public List<Animal> getAllAnimals() {
		return service.getAllAnimals();
	}

	@GetMapping("/animal/age/{age}")
	public List<Animal> getAnimalsByAge(@PathVariable("age") int age) {
		return service.getAnimalsByAge(age);
	}
}
