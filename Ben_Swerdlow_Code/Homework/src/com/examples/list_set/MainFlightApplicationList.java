package com.examples.list_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFlightApplication {

	public static void main(String[] args) {

		List<Flight> flightList = new ArrayList<>();
		flightList.add(new Flight(3047, "LAS-ATL", "Las-Vegas", "Atlanta", 4.0f, 1000.0));
		flightList.add(new Flight(8765, "DTW-ATL", "Detroit", "Atlanta", 5.0f, 100.0));
		flightList.add(new Flight(3333, "MCO-DTW", "Orlando", "Detroit", 3.0f, 300.0));
		flightList.add(new Flight(4812, "ATL-DTW", "Atlanta", "Detroit", 3.0f, 200.0));
		flightList.add(new Flight(9034, "ATL-MCO", "Atlanta", "Orlando", 3.5f, 300.0));
		flightList.add(new Flight(3845, "DTW-MCO", "Detroit", "Atlanta", 2.8f, 100.0));
		flightList.add(new Flight(9786, "ATL-LAS", "Atlanta", "Las-Vegas", 4.0f, 130.0));
		flightList.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		System.out.println("Unsorted List");
		printFlights(flightList);
		
		System.out.println("\nSorted List by fid");
		Collections.sort(flightList);
		printFlights(flightList);
		
		System.out.println("\nSorted List by cost ascending");
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Double tpf1 = f1.getTicketPrice();
			Double tpf2 = f2.getTicketPrice();
			return tpf1.compareTo(tpf2);
		});
		printFlights(flightList);
		
		System.out.println("\nSorted List by rating descending");
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Float rf1 = f1.getRating();
			Float rf2 = f2.getRating();
			return rf2.compareTo(rf1);
		});
		printFlights(flightList);
		
		System.out.println("\nSorted List cost ascending, then rating descending");
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Double tpf1 = f1.getTicketPrice();
			Double tpf2 = f2.getTicketPrice();
			int x = tpf1.compareTo(tpf2);
			if (x==0) {
				Float rf1 = f1.getRating();
				Float rf2 = f2.getRating();
				x = rf2.compareTo(rf1);
			}
			return x;
		});
		printFlights(flightList);
		
		System.out.println("\nSorted List rating descending, then cost ascending");
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Float rf1 = f1.getRating();
			Float rf2 = f2.getRating();
			int x = rf2.compareTo(rf1);
			if (x==0) {
				Double tpf1 = f1.getTicketPrice();
				Double tpf2 = f2.getTicketPrice();
				x = tpf1.compareTo(tpf2);
			}
			return x;
		});
		printFlights(flightList);

	}
	
	public static void printFlights(List<Flight> flightList) {
		for (Flight f : flightList) {
			System.out.println(f);
		}
	}

}
