package com.revature.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MainSet {

	public static void main(String[] args) {
		
		Set<Flight> setF = new HashSet<>();
		setF.add(new Flight(40,"MIA-MDE","Miami","Medellin",4.2f, 250.0));
		setF.add(new Flight(60,"MIA-NY","Miami","New York",3.2f, 200.0));
		setF.add(new Flight(41,"MDE-MIA","Medellin","Miami",2.1f, 250.0));
		setF.add(new Flight(18,"MIA-VA","Miami","Virginia",4.2f, 150.0));
		setF.add(new Flight(19,"VA-MIA","Virginia","Miami",4.0f, 150.0));
		setF.add(new Flight(60,"MIA-NY","Miami","New York",3.2f, 200.0));
		setF.add(new Flight(41,"MDE-MIA","Medellin","Miami",2.1f, 250.0));

		System.out.println("Printing Flights in a HashSet Collection....");
		printFlights(setF);
		
		Set<Flight> treeF = new TreeSet<>();
		treeF.add(new Flight(40,"MIA-MDE","Miami","Medellin",4.2f, 250.0));
		treeF.add(new Flight(60,"MIA-NY","Miami","New York",3.2f, 200.0));
		treeF.add(new Flight(41,"MDE-MIA","Medellin","Miami",2.1f, 250.0));
		treeF.add(new Flight(18,"MIA-VA","Miami","Virginia",4.2f, 150.0));
		treeF.add(new Flight(19,"VA-MIA","Virginia","Miami",4.0f, 150.0));
		treeF.add(new Flight(41,"MDE-MIA","Medellin","Miami",2.1f, 250.0));
		treeF.add(new Flight(18,"MIA-VA","Miami","Virginia",4.2f, 150.0));
		
		System.out.println("\nPrinting Flights in a TreeSet Collection....");
		printFlights(treeF);
				
	}

	private static void printFlights(Set<Flight> setF) {
		for (Flight f: setF) {
			System.out.println(f);
		}
	}

}
