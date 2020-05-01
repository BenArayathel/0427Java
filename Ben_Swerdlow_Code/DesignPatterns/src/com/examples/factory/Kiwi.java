package com.examples.factory;

public class Kiwi implements Fruit {
	
	public String name;
	public String calories;
	public String color;
	public boolean hasFur;
	
	public Kiwi(boolean hasFur) {
		this.hasFur = hasFur;
	}

	@Override
	public boolean isTasty() {
		// TODO Auto-generated method stub
		System.out.println("Kiwi is tasty");
		return true;
	}

	@Override
	public String toString() {
		return "Kiwi [name=" + name + ", calories=" + calories + ", color=" + color + ", hasFur=" + hasFur + "]";
	}

}
