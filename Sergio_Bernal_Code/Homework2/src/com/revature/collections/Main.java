package com.revature.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Flight> listF = new ArrayList<Flight>();
		listF.add(new Flight(40,"MIA-MDE","Miami","Medellin",4.2f, 250.0));
		listF.add(new Flight(60,"MIA-NY","Miami","New York",3.2f, 200.0));
		listF.add(new Flight(41,"MDE-MIA","Medellin","Miami",2.1f, 250.0));
		listF.add(new Flight(18,"MIA-VA","Miami","Virginia",4.2f, 150.0));
		listF.add(new Flight(19,"VA-MIA","Virginia","Miami",4.0f, 150.0));
		listF.add(new Flight(40,"MIA-MDE","Miami","Medellin",3.2f, 180.0));
		
		List<Flight> listF2 = listF;
		List<Flight> listF3 = listF;
		
		System.out.println("Printing Flights....");
		printFlights(listF);
		
		System.out.println("\nPrinting Flights By ID....");
		Collections.sort(listF);
		printFlights(listF);
		
		System.out.println("\nSorting Flights By ID and Rating...");
		Collections.sort(listF, (Flight f1, Flight f2) -> {
			Float ff1 = f1.getRating();
			Float ff2 = f1.getRating();
			return ff1.compareTo(ff2);
		});
		printFlights(listF);
		
		System.out.println("\nSorting Flights By Name...");
		Collections.sort(listF2, (Flight f1, Flight f2) -> {
			String ff1 = f1.getName();
			String ff2 = f2.getName();
			return ff1.compareTo(ff2);
		});
		printFlights(listF2);
		
		System.out.println("\nSrting Flights By Source and Destination...");
		Collections.sort(listF3, (Flight f1, Flight f2) -> {
			int res = 0;
			String fs1 = f1.getSource();
			String fs2 = f2.getSource();
			int x = fs1.compareTo(fs2);
			if ( x > 0) {
				String ff1 = f1.getDestination();
				String ff2 = f2.getDestination();
				res = ff1.compareTo(ff2);
			}
			return res;
		});
		printFlights(listF3);

	}

	private static void printFlights(List<Flight> listF) {
		for (Flight f: listF) {
			System.out.println(f);
		}
	}
	

}
