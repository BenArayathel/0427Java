package com.jedi.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jedi.app.model.Jedi;
import com.jedi.app.service.JediService;

@RestController
public class MainController {
	
	@Autowired
	private JediService jediService;

	@RequestMapping("/")
	public String index() {
		jediService.createJediRoster();
//		ArrayList<Jedi> jediList = new ArrayList<Jedi> ();
//		jediList.add(new Jedi(1, "Yoda", "master", "green"));
//		jediList.add(new Jedi(2, "Mace Windu", "master", "purple"));
//		jediList.add(new Jedi(3, "Obi-Wan Kenobi", "knight", "blue"));
//		jediList.add(new Jedi(4, "Quigon Jinn", "knight", "green"));
//		System.out.println(jediList);
//		
//		jediService.createJediTableInfo(jediList);
		return "Hello World!";
	}
	
	

}
