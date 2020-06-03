package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dao.PlanetRepo;
import com.example.model.Planet;

@Controller
@RequestMapping("/api")  //SpringMVC/mvcexample/api
@CrossOrigin(origins = "*") //Dealing with CORS issues
public class PlanetController {
	
	@Autowired //LAZY WAY DONT DO THIS, create a setter method or constructor that passes in the value
	private PlanetRepo planetRepo;
	
	
	
	
	public PlanetController() {
		System.out.println("Initialising Constructor!");
		// TODO Auto-generated constructor stub
	}

	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
//	@RequestMapping(value = "/getAllPlanets", method = RequestMethod.GET) //SpringMVC/mvcexample/api/getAllPlanets
	@GetMapping(value = "/getAllPlanets")
	public @ResponseBody List<Planet> getAllPlanets() {
		System.out.println("Inside GetAllPlanets!");
		return planetRepo.selectAll();
	}
	
	@PostMapping(value = "/getPlanetById")
	public @ResponseBody Planet getPlanetById(@RequestParam("id") int num) {
		return planetRepo.selectById(num);
	}
	
	@PostMapping(value = "/getPlanetById2", produces = "application/json", params = {"id"})
	public ResponseEntity<Planet> getPlanetById2(int id){
		return new ResponseEntity<Planet>(planetRepo.selectById(id), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@GetMapping(value = "/getPlanetById3/{id}")
	public @ResponseBody Planet getPlanetByURI(@PathVariable("id") int id) {
		return planetRepo.selectById(id);
	}
	
	@PutMapping(value = "/createPlanet")
	public @ResponseBody String createNewPlanet(@RequestBody Planet incomingPlanet) {
		
		/*
		 * If an incoming JSON doesn't have ALL the necessary planet model fields, then Spring will simply give the misssing 
		 * model fields their default values. ALSO, if the incoming JSON has MORE fields than the Planet model, then it'll simply 
		 * ignore the fields. 
		 */
		
		planetRepo.insert(incomingPlanet);
		
		return "Success";
	}
	
	@DeleteMapping(value = "/deletingPlanet")
	public @ResponseBody String deleteNewPlanet(@RequestBody Planet planet) {
		
		planetRepo.delete(planet);
		
		return "Planet Successfully Destroyed";
	}

}
