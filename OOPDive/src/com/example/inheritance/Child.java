package com.example.inheritance;

import com.example.experiment.OtherParent;

public class Child extends Parent{
	
	public Child() {
		// TODO Auto-generated constructor stub
		
		System.out.println("Inside Child Cosntructor!!!");
	}
	
	String childName = "Batman";
	
	public void childMethod() {
		System.out.println("Inside child method!!!");
	}

}
