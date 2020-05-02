package com.examples.singleton;

public class Ben {
	
	SingletonCar myCar = SingletonCar.getCar();
	
	public void changeColor() {
		myCar.setColor("green");
	}

}
