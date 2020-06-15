package com.example.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class DoMath {

	@WebMethod
	public void doMath(int number) {
		int first = 0;
		int second = 1;
		for(int i =1; i <= number; i++) {
			System.out.print(first + "");
			int sum = first + second;
			first = second;
			second = sum;
		}
	}

}
