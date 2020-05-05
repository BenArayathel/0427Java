package com.may01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightMain {
	
	public static void main(String[] args) {
		List<Flight> flightList = new ArrayList<>();
		
		Flight flight1 = new Flight(3, "United", "ATL", "PHL", 7.0F, 100.0);
		Flight flight2 = new Flight(2, "Delta", "ATL", "PHL", 7.5F, 120.0);
		Flight flight3 = new Flight(4, "American", "ATL", "PHL", 5.5F, 90.0);
		Flight flight4 = new Flight(5, "Southwest", "ATL", "PHL", 8.5F, 79.0);
		Flight flight5 = new Flight(1, "Jet", "ATL", "PHL", 6.5F, 70.0);
		
		flightList.add(flight1);
		flightList.add(flight2);
		flightList.add(flight3);
		flightList.add(flight4);
		flightList.add(flight5);
		
	    //Unordered list
	    System.out.println("Unsorted List");
	    System.out.println(flightList);

	    //Sorted by ratings
	    Collections.sort(flightList, new SortByRatings());
	    System.out.println(flightList);

	    
	    

	}

}
