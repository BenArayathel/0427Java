package com.jedi.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jedi.app.model.Jedi;
import com.jedi.app.model.Padawan;
import com.jedi.app.service.JediService;
import com.jedi.app.service.PadawanService;

@RestController
public class MainController {
	
	@Autowired
	private JediService jediService;
	
	@Autowired
	private PadawanService padawanService;

	@RequestMapping("/")
	public String index() {
		
//		Jedi temp = jediService.createJedi(new Jedi("Qui-gon Jinn", "knight", "green"));
//		jediService.createJedi(new Jedi("Mace Windu", "master", "purple"));

//		Jedi obiWan = jediService.createJedi(new Jedi("Obi-Wan Kenobi", "knight", "blue"));
//		Padawan anakin = new Padawan("Anakin Skywalker", "blue", obiWan.getId());
//		padawanService.createPadawan(anakin);
		
		Jedi obiWan = jediService.createJedi(new Jedi("Obi-Wan Kenobi", "knight", "blue", true));
		jediService.createJedi(new Jedi("Mace Windu", "master", "purple", false));
		jediService.createJedi(new Jedi("Qui-gon Jinn", "knight", "green", false));
		jediService.createJedi(new Jedi("Yoda", "knight", "green", true));
		padawanService.createPadawan(new Padawan("Anakin Skywalker", "blue", 1));
		padawanService.createPadawan(new Padawan("Luke Skywalker", "green", 4));
		
		return "Hello World!";
	}
	
	

}
