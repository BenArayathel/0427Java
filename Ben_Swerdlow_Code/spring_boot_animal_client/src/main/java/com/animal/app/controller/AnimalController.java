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

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AnimalController {
	
	private AnimalService animalServ;
	
	@Autowired
	public AnimalController(AnimalService animalServ/*, PersonService personServ*/) {
		this.animalServ = animalServ;
//		this.personServ = personServ;
	}

	@PostMapping("/animal")
	public Animal createAnimal(@RequestBody Animal a) {
		// TODO Auto-generated method stub
		return animalServ.createAnimal(a);
	}

	@PutMapping("/animal")
	public Animal updateAnimal(@RequestBody Animal a) {
		return animalServ.updateAnimal(a);
	}
	
	@PutMapping("/animal/belongsTo/{ownerId}")
	public Animal updateanimal(@RequestBody Animal a, @PathVariable("ownerId") Integer ownerId) {
//		if (a.getOwner()==null && personServ.isPerson(ownerId)) {
//			Person p = personServ.getPersonById(ownerId);
//			a.setOwner(p);
////			List<Animal> pAnimalList= p.getPets();
////			pAnimalList.add(a);
////			p.setPets(pAnimalList);
////			personServ.updatePerson(p);
//			return animalServ.updateAnimal(a);
//		} else {
//			return null;
//		}
		return animalServ.updateAnimal(a);
	}
	
	@GetMapping("/animal/{id}")
	public Animal getAnimalById(@PathVariable("id") int id) {
		return animalServ.getAnimalById(id);
	}

	@DeleteMapping("/animal/{id}")
	public void deleteAnimalById(@PathVariable int id) {
		animalServ.deleteAnimalById(id);
	}

	@GetMapping("/animals")
	public List<Animal> getAllAnimals() {
		return animalServ.getAllAnimals();
	}

	@GetMapping("/animal/age/{age}")
	public List<Animal> getAnimalsByAge(@PathVariable("age") int age) {
		return animalServ.getAnimalsByAge(age);
	}
}
