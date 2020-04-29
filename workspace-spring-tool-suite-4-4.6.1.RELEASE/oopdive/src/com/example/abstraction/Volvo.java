package com.example.abstraction;

public class Volvo implements Car, Truck {

	@Override
	public void startEngine() {
		System.out.println("Volvo starting engine");
		
	}

	@Override
	public void drive() {
		System.out.println("Volvo driving");
		
	}

	@Override
	public void liftHeavyStuff() {
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
