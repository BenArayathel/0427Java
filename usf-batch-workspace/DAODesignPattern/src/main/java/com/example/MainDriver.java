package com.example;

import com.example.service.PlanetService;
import com.example.service.PlanetServiceImpl;

public class MainDriver {
	
	//Presentation layer - only call methods from the service layer
	public static void introduction() { 
		System.out.println("WELCOME\n");
		System.out.println("HOW DO YOU WANT TO MANAGE YOUR PLANETS!?");
		
		System.out.println("ARE YOU MORTAL OR NOT?");
		
//		int role = 1;
		
//		if(choice == 1) {
//			mortalOptions();
//		}else if(choice == 2) {
//			immortalOptions();
//		}
	}
	
	public static void options(int choice) {
		PlanetService ps = new PlanetServiceImpl();
		if(choice == 1) {
			System.out.println("You've selected create solarsystem!!!");
			ps.createSolarSystem();
			
		}else if (choice == 2) {
			System.out.println("You monster!!!");
			ps.destroyALLPlanets();
		}
	}
	
	public static void main(String[] args) {
		
		introduction();
		
		options(2);
		
	}

}
