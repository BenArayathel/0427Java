package com.examples.factory;

public class Kiwi implements Fruit{
	
	public boolean hasFur;
	
	public Kiwi(boolean hasFur) {
		this.hasFur = hasFur;
	}

	@Override
	public boolean isTasty() {
		// TODO Auto-generated method stub
		
		System.out.println("Kiwi is tasty!!!");
		return true;
	}

}
