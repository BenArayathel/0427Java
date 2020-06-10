package com.example;

import com.example.services.HelloGenerator;
import com.example.services.HelloGeneratorService;

public class MainDriver {
	
	public static void main(String[] args) {
		
		HelloGeneratorService hgenService = new HelloGeneratorService();
		
		HelloGenerator hello = hgenService.getHelloGeneratorPort();
		
		String response = hello.hello("Vinay");
		
		System.out.println(response);
		
	}

}