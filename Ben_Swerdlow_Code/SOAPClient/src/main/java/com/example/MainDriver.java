package com.example;

import com.example.model.Person;
import com.example.services.HelloGenerator;
import com.example.services.HelloGeneratorService;

// wsimport -keep -p com.example.services http://localhost:<port>/path/to?wsdl

public class MainDriver {
	
	public static void main (String[] args) {
		HelloGeneratorService hgenService = new HelloGeneratorService();
		
		HelloGenerator hello = hgenService.getHelloGeneratorPort();
		
		String response = hello.hello("Vinay");
		
		System.out.println(response);
		
		Person input = new Person("Ben", "Swerdlow", 27, "Swerd's the word!");
		
		Person output = hello.helloPerson(input);
		
		System.out.println(output);
		
		System.out.println("Person has been greeted: "+hello.greeted());
	}

}
