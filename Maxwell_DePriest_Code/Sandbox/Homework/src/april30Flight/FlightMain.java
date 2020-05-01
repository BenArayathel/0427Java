package april30Flight;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class FlightMain {

	public FlightMain() {
		
	}

	public static void main(String[] args) {
		treeSetExample();
		sortExample();
	}
	
	
	public static void treeSetExample() {
		Set<Flight> flightList = new TreeSet<>();
		flightList.add(new Flight(879, "Allegiant", "Houston", "Austin", 3.7, 105.85));
		flightList.add(new Flight(845, "Southwest", "Tampa", "Columbia", 2.5, 500.34));
		flightList.add(new Flight(119, "Delta", "Seattle", "Dallas", 4.0, 705.85));
		flightList.add(new Flight(839, "Delta", "San Antonio", "Boston", 3.5, 345.75));
		flightList.add(new Flight(739, "US Air", "Green Bay", "Minneapolis", 1.4, 250.55));
		
		printFlights(flightList);
	}
	
	public static void printFlights(Set<Flight> flightList) {
		for(Flight flight : flightList) {
			System.out.println(flight);
		}
		
	}
	
	public static void sortExample() {
		List<Flight> flightList = new ArrayList<Flight>();
		flightList.add(new Flight(879, "Allegiant", "Houston", "Austin", 3.7, 105.85));
		flightList.add(new Flight(845, "Southwest", "Tampa", "Columbia", 2.5, 500.34));
		flightList.add(new Flight(119, "Delta", "Seattle", "Dallas", 4.0, 705.85));
		flightList.add(new Flight(839, "Delta", "San Antonio", "Boston", 3.5, 345.75));
		flightList.add(new Flight(739, "US Air", "Green Bay", "Minneapolis", 1.4, 250.55));
		
		List<Flight> flightList2 = flightList;
		List<Flight> flightList3 = flightList;
		
		System.out.println("Printing Flights");
		printFlights(flightList);
		
		System.out.println("\nPrinting Flights by ID");
		Collections.sort(flightList);
		printFlights(flightList);
		
		System.out.println("\nSorting Flights by ID and Rating");
		Collections.sort(flightList, (Flight f1, Flight f2) -> {
			Double ff1 = f1.getRatings();
			Double ff2 = f2.getRatings();
			return ff1.compareTo(ff2);
		});
		printFlights(flightList);
		
		System.out.println("\nSorting Flights by Name");
		Collections.sort(flightList2, (Flight f1, Flight f2) -> {
			String ff1 = f1.getName();
			String ff2 = f2.getName();
			return ff1.compareTo(ff2);
		});
		
		printFlights(flightList2);
		
		System.out.println("\nSorting Flights by Source and Destination");
		Collections.sort(flightList3, (Flight f1, Flight f2) -> {
			int res = 0;
			String fs1 = f1.getSource();
			String fs2 = f2.getSource();
			int x = fs1.compareTo(fs2);
			
			if (x>0) {
				String ff1 = f1.getDestination();
				String ff2 = f2.getDestination();
				res = ff1.compareTo(ff2);
			}
		});

		
		
		}
	
	public static void printFlights(List<Flight> flight) {
		for(Flight f : flight) {
			System.out.println(f);
		}
	
	
	}
	
	

}
