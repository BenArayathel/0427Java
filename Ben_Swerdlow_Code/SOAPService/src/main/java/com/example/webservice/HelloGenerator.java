package com.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public class HelloGenerator {
	
	@WebMethod
	public String Hello(String name) {
		return "Hello "+name;
	}

}
