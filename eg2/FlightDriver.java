package eg2;

import java.util.Set;
import java.util.TreeSet;

public class FlightDriver {

	public static void main(String[] args) {
		
		Set<Flight> flightList=new TreeSet<>();
		flightList.add(new Flight(12345, "American Airlines", "Expedia", "Chicago, IL", 5.0f, 1000.00));
		flightList.add(new Flight(98765, "Air Canada", "Google Flights", "Dallas, TX", 4.0f, 999.00));
		flightList.add(new Flight(12346, "United Airlines", "Price Line", "San Fransisco, CA", 3.0f, 888.00));
		flightList.add(new Flight(13579, "Delta Airlines", "Google Flights", "Detroit, MI", 2.0f, 777.00));
		flightList.add(new Flight(24680, "Spirit Airlines", "Spirit Airlines", "Denver, CO", 1.0f, 667.00));
		System.out.println("Printing all products");
		printFlights(flightList);

	}
	
	public static void printFlights(Set<Flight> flightList) {
		for(Flight f:flightList) {
			System.out.println(f);
		}

	}

}
