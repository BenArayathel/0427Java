package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dao.CarsRepo;
import com.example.model.Cars;


@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*") //Dealing with CORS issues
public class carsController {
	
	
	private CarsRepo carsrepo;
	
	@Autowired
	public carsController(CarsRepo carsrepo) {
		this.carsrepo = carsrepo;
	System.out.println("Cars controller being initialised");
	}
	
	//METHODS
	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
	@RequestMapping(value = "/getAllCars", method = RequestMethod.GET)
	public @ResponseBody List<Cars> selectAllCars() {
		System.out.println("Inside here!");
//		return planetRepo.selectAll();
		return carsrepo.selectAllCars();
	}
	
	@PostMapping(value ="/selectCarById", produces = "application/json", params = {"id"})
	public ResponseEntity<Cars> selectCarById(int id){
		return new ResponseEntity<Cars>(carsrepo.selectCarById(id), HttpStatus.I_AM_A_TEAPOT);
	}

}
