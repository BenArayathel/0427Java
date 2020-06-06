package com.jedi.app.controller;

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

import com.jedi.app.service.JediService;
import com.jedi.app.model.Jedi;

@CrossOrigin
@RestController
public class JediController {

	@Autowired
	private JediService jediService;
	
	@GetMapping("/jedi/{id}")
	public Jedi getJediById(@PathVariable("id") int id) {
		return jediService.getJediById(id);
	}
	
	@GetMapping("/jedi/all")
	public List<Jedi> getAllJedi() {
		return jediService.getAllJedi();
	}
	
	@GetMapping("/jedi/rank/{rank}")
	public List<Jedi> getJediByRank(@PathVariable("rank") String rank) {
		return jediService.getAllJediByRank(rank);
	}
	
	@PostMapping("/jedi") 
	public Jedi createJedi(@RequestBody Jedi jedi) {
		return jediService.createJedi(jedi);
	}
	
	@PutMapping("/jedi")
	public Jedi updateJedi(@RequestBody Jedi jedi) {
		return jediService.updateJedi(jedi);
	}
	
	@DeleteMapping("/delete/jedi/{id}")
	public void deleteJediById(@PathVariable("id") int id) {
		jediService.deleteJediById(id);
	}
}
