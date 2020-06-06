package com.jedi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jedi.app.model.Jedi;
import com.jedi.app.service.JediService;

@SpringBootApplication
public class SpringBootJediApplication {
	
	private JediService jediService;

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootJediApplication.class, args);
	}

}
