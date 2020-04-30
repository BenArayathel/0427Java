package com.example.serialization;

import java.io.Serializable;

public class Parrot implements Serializable {
	/*
	 * For an object to be serializable, it has to implement the serializable interface (java.io.Serializable)
	 * 
	 * Serializable is a marker interface 
	 */
	
	/**
	 * This is Javadoc documentation! Auto-generating documentation is great!
	 * The serialVersionUID is for documentation generation purposes.
	 */
	private static final long serialVersionUID = 6203215895028377344L;
	
	// Transient tells the compiler I don't want to store this anywhere outside the JVM
	// It will only be available while the program is running, even if you serialize the object
	private transient String name;
	private String color;
	
	public void fly() {
		System.out.println("Look at me, I'm flying!");
	}
	
	public void speak() {
		System.out.println("Look at me, I'm talking!");
	}

	// It's generally good practice to make a no-args constructor
	// some frameworks require it or prefer to set things with setters
	// not with constructors
	public Parrot() {
		super();
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

}
