package com.jedi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jedi.app.model.Padawan;
import com.jedi.app.service.PadawanService;

@RestController
public class PadawanController {

	@Autowired
	PadawanService padawanService;
	
	@GetMapping("/padawan/{id}")
	public Padawan getPadawanById(@PathVariable("id") int id) {
		return padawanService.getPadawanById(id);
	}
	
	@GetMapping("/padawan/all")
	public List<Padawan> getAllPadawans() {
		return padawanService.getAllPadawans();
	}
	
	@PostMapping("/padawan") 
	public Padawan createPadawan(@RequestBody Padawan padawan) {
		return padawanService.createPadawan(padawan);
	}
	
	@PutMapping("/padawan")
	public Padawan updatePadawan(@RequestBody Padawan padawan) {
		return padawanService.updatePadawan(padawan);
	}
	
	@DeleteMapping("/delete/padawan/{id}")
	public void deletePadawanById(@PathVariable("id") int id) {
		padawanService.deletePadawanById(id);
	}

}
