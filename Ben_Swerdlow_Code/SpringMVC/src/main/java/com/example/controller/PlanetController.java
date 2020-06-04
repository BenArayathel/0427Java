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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dao.PlanetRepo;
import com.example.model.Planet;

@Controller
// /SpringMVC/mvcexample/api to get to this servlet
@RequestMapping("/api")
@CrossOrigin(origins="*")// Dealing with CORS issues
public class PlanetController {
	
	/*
	 * Right now we're having the controller directly talk with PlanetRepo. DON'T DO THIS IN PRACTICE! Make a service layer between them.
	 */
	
	// OMG DON'T DO THIS IN PRACTICE. AUTOWIRING FIELDS IS BAD!!!! Create a setter method or constructor that passes in the value
	@Autowired
	private PlanetRepo planetRepo;
	
	public PlanetController() {
		super();
		System.out.println("Initializing constructor!");
		// TODO Auto-generated constructor stub
	}

	public PlanetController(PlanetRepo planetRepo) {
		super();
		this.planetRepo = planetRepo;
	}

	// Response status will now return "I am a teapot"
	@ResponseStatus(value=HttpStatus.I_AM_A_TEAPOT)
	// /SpringMVC/mvcexample/api/getAllPlanets with a GET request
	@RequestMapping(value="/getAllPlanets", method=RequestMethod.GET)// equivalent to @GetMapping(value="/getAllPlanets")
	// @ResponseBody tells Spring that it needs to add the returned thing to the body of our HTTP response
	public @ResponseBody List<Planet> getAllPlanets() {
		System.out.println("Inside get all planets!");
		return planetRepo.selectAll();
	}
	
	// Request method is automatically set to Post
	@PostMapping(value="/getPlanetById")
	// @RequestParam("id") gets a request parameter named id that will be cast to int
	public @ResponseBody Planet getPlanetById(@RequestParam("id") int num) {
		return planetRepo.selectById(num);
	}
	
	// specifies that the request will produce a json (the default) and the request has a parameter called id
	@PostMapping(value="/getPlanetById2", produces="application/json", params= {"id"})
	public ResponseEntity<Planet> getPlanetById2(int id) {
		return new ResponseEntity<Planet>(planetRepo.selectById(id), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@GetMapping(value="/getPlanetById3/{id}")
	public @ResponseBody Planet getPlanetByURI(@PathVariable("id") int id) { 
		return planetRepo.selectById(id);
	}
	
	@PutMapping(value="/createPlanet")
	public @ResponseBody String createNewPlanet(@RequestBody Planet incomingPlanet) {
		
		/*
		 * If an incoming JSON doesn't have ALL the necessary planet model fields, then Spring will simply give the missing
		 * model fields their default values automatically. ALSO, if the incoming JSON has MORE fields than the Planet model,
		 * then it'll simply ignore those fields. 
		 */
		planetRepo.insert(incomingPlanet);
		return "Success";
	}
	
	@DeleteMapping(value="/deletingPlanet")
	public @ResponseBody String deleteNewPlanet(@RequestBody Planet planet) {
		planetRepo.delete(planet);
		return "Planet successfully destroyed";
	}

}
