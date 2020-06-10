package com.example.abstraction;

public class MainDriver {
	
	public static void main(String[] args) {
		
		/*
		 * What is abstraction?
		 * 		Hiding implementation to show functionality. 
		 * 
		 * How does Java implement? 
		 * 		Interfaces -- pure form of abstraction
		 * 		Abstract Classes -- partial abstraction
		 */
		
		/*
		 * Class inheritance has to be linear
		 */
		
		Car c = new Volvo();
//		System.out.println(c.pi);
		c.drive();
//		c.startEngine();
//		
//		Car j = new Jaguar();
//		System.out.println(j.pi);
//		j.drive();
//		j.startEngine();
//		
////		Truck t = new Jaguar(); Can't use Jaguar because it does implement it
//		Truck t = new Volvo();
//		t.drive();
//		
		/*
		 * Example:
		 * 			If product owner wanted a product, they will have no idea how to implement it.
		 * 			As a developer, I'll create an interface to make sure I know what functionality I'll need. 
		 * 			
		 */
		
		//Abstract Classes
		
//		Prototype p = new Ford();
//		p.brake();
		
		/*
		 * EXTRA:
		 * 		Abstract, static and final cannot be used together.
		 * 		Static and final is fine though. 
		 */
	}

}
