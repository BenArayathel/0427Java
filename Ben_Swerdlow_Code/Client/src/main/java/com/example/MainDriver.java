package com.example;

import com.example.services.HelloGenerator;
import com.example.services.HelloGeneratorService;

// wsimport -keep -p com.example.services http://localhost:8080/SoapTest/ws/hello?wsdl

public class MainDriver {
	
	public static void main (String[] args) {
		HelloGeneratorService hgenService = new HelloGeneratorService();
		
		HelloGenerator hello = hgenService.getHelloGeneratorPort();
		
		String response = hello.hello("Vinay");
	}

}
