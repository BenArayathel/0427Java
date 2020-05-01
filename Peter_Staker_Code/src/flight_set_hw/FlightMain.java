package flight_set_hw;

import java.util.Set;
import java.util.TreeSet;

public class FlightMain {
	
	public static void main(String[] args) {
		
		Set<Flight> flightList = new TreeSet<>();
		flightList.add(new Flight(100, "Alaskan", "SEA", "HOU", 8.4f, 320.42));
		flightList.add(new Flight(302, "Virgin", "CHI", "POR", 8.7f, 409.66));
		flightList.add(new Flight(213, "Southwest", "DAL", "HOU", 4.8f, 98.89));
		flightList.add(new Flight(403, "American", "LA", "DEN", 5.7f, 370.76));
		flightList.add(new Flight(304, "United", "SF", "KC", 6.1f, 260.86));
		flightList.add(new Flight(765, "Hawaiian", "NY", "HON", 7.8f, 729.99));
		System.out.println("Printing all flights");
		printFlights(flightList);
	}
	public static void printFlights(Set<Flight> flightList) {
		for(Flight f:flightList) {
			System.out.println(f);
		}
	}

}
