package hw0430.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainDriver {
	
	public static void main(String[] args) {
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight(3, "American Airlines", "LA", "Denver", 4.5f, 149.99));
		flights.add(new Flight(2, "American Airlines", "San Diego", "Portland", 4.35f, 139.99));
		flights.add(new Flight(1, "American Airlines", "Long Beach", "Florida", 4.8f, 189.99));
		flights.add(new Flight(5, "American Airlines", "Miami", "Cancun", 4.6f, 169.99));
		flights.add(new Flight(4, "American Airlines", "Compton", "Chicago", 4.7f, 119.99));
		System.out.println("Printing flights in order of insertion...\n" + flights);
		
		Collections.sort(flights); // only works with List<E> interface
		System.out.println("\nPrinting flights in ascending order of thier 'fid'...\n" + flights);
		
	}

}
