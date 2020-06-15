package com.example;

import org.springframework.stereotype.Component;

@Component("appProxy")
public class MyAppProxy {
	
	public MyAppProxy() {
		// TODO Auto-generated constructor stub
	}
	
	public void randomMethod() {
		
		System.out.println("Inside Random Method");
	}

	
	public void anotherMethod() {
		System.out.println("Inside another method");
		
	}
	
	
	protected int doingJudo(char a, int b) {
		System.out.println("Doing Judo");
		return 0;
	}
	
	public void doingStuffAgain() {
		System.out.println("Doing a bit of JuJitsu");
	}
	
	public String doingDisco(Integer c) {
		System.out.println("Doing disco");
		return "";
	}
}
