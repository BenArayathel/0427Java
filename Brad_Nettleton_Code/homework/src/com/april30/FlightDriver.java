package com.april30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDriver {

	public static void main(String[] args) {
		List<Flight> flights = new ArrayList<>();
		//int fid, String name, String source, String destination, float ratings, double ticketPrice
		flights.add(new Flight(5, "Bravo", "Denver, CO", "Tallahassee, FL", 3.5f, 100.00));
		flights.add(new Flight(21, "AirForce2", "Little Rock, AK", "New York City, New York", 4.0f, 140.00));
		flights.add(new Flight(9, "CatDog", "San Fransico, CA", "Las Vegas, NV", 2.2f, 50.00));
		flights.add(new Flight(15, "BigRiver", "Vicksburg, MS", "Clearwater, FL", 5f, 85.54));
		
		System.out.println("Initial order");
		printFlights(flights);
		
		Collections.sort(flights);
		System.out.println("After Collections.sort()");
		printFlights(flights);
		
		Collections.sort(flights, new FlightPriceComparator());
		System.out.println("After FlightPriceComparator sort");
		printFlights(flights);
		
		Collections.sort(flights, (Flight f1, Flight f2) -> {
			Float r1 = f1.getRating();
			Float r2 = f2.getRating();
			return r1.compareTo(r2);
		});
		System.out.println("After rating sort");
		printFlights(flights);
	}
	
	public static void printFlights(List<Flight> flights) {
		for(Flight flight:flights) {
			System.out.println(flight);
			}
	}

}
