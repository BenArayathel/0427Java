package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api")
@CrossOrigin(origins = "*") //Dealing with CORS issues
public class PlanetController {
	
//	@Autowired //DON'T DO THIS, this is Field Injection 
	private PlanetRepo planetRepo;

	//CONSTRUCTORS
	public PlanetController() {
		System.out.println("Planet controller being initialised"); //Component scan is happening, it is aware
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired //Constructor injection
	public PlanetController(PlanetRepo planetRepo) {
		this.planetRepo = planetRepo;
	}
	
	
	public void setPlanetRepo(PlanetRepo planetRepo) {
		this.planetRepo = planetRepo;
	}


	//METHODS
	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
	@RequestMapping(value = "/getAllPlanets", method = RequestMethod.GET)
	public @ResponseBody List<Planet> getAllPlanets() {
		System.out.println("Inside here!");
		return planetRepo.selectAll();
	}
	
	@PostMapping(value = "/getPlanetById") //You can directly use the @GetMappings or @PostMappings
	public @ResponseBody Planet getPlanetById(@RequestParam("id") int num) {
		return planetRepo.selectById(num);
	}
	
	//Produces attribute specifes what you'll be "producing" and sending back 
	//Consumes specifies what data format the user is giving back to you
	@PostMapping(value ="/getPlanetById2", produces = "application/json", params = {"id"})
	public ResponseEntity<Planet> getPlanetById2(int id){
		return new ResponseEntity<Planet>(planetRepo.selectById(id), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@PostMapping(value = "/getPlanetByUri/{id}")
	public @ResponseBody Planet getPlanetByUri(@PathVariable("id") int id) {
		return planetRepo.selectById(id);
	}
	
	@PutMapping(value = "/createPlanet")
	public @ResponseBody String createNewPlanet(@RequestBody Planet incomingPlanet) {
		/*
		 * If an incoming JSON doesn't have ALL the necessary food model fields, then Spring will simply give the missing
		 * Planet model fields their default values. ALSO, if the incoming JSON has MORE fields than the Planet model, then 
		 * it'll simply ignore those fields.
		 */
		planetRepo.insert(incomingPlanet);
		return "Success";
	}
	
	
	

}
