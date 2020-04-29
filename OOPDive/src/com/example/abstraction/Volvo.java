package com.example.abstraction;

public class Volvo implements Car, Truck{

	@Override
	public void startEngine() {
		System.out.println("Volve starting engine");
		
	}

//	@Override		This is just overiding both methods
//	public void drive() {
//		System.out.println("Volve driving");
//		
//	}

	@Override
	public void liftHeavyStuff(String item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		Truck.super.drive(); //This allows us to resolve the diamond problem by referencing a particular interface
	}

}
