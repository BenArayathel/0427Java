package com.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.example.webservice.model.Person;

@WebService
@SOAPBinding(style=Style.RPC)
public class HelloGenerator {
	
	@WebMethod
	public String hello(String name) {
		return "Hello "+name;
	}
	
	@WebMethod
	public Person HelloPerson(Person person) {
		return person;
	}
	
	@WebMethod
	public boolean greeted() {
		return true;
	}

}
