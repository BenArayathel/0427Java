package com.example.serialization;

import java.io.Serializable;

public class Parrot implements Serializable{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6203215895028377344L; //For documentation
	/*
	 * For an object to be serializabe, it has to implement the Serializable interface
	 * 
	 * Serializable interface is a marker interface. 
	 */
	
	private transient String name; //I don't want this anywhere, only I should know the Parrot's name!!! (transient)
	private String color;
	
	public Parrot() {
		// TODO Auto-generated constructor stub
	}
	
	public Parrot(String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}
	

	@Override
	public String toString() {
		return "Parrot [name=" + name + ", color=" + color + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void speak() {
		System.out.println("Look at me, I'm talking");
	}
	public void fly() {
		System.out.println("Look at me, I'm flying");
	}
	
	

}
