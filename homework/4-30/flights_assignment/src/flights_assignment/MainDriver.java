package flights_assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainDriver {

	public static void main(String[] args) {
		
		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight(7, "Delta", "San Francisco", "Denver", 3.1f, 634.36));
		flightList.add(new Flight(3, "SouthWest", "London", "New York", 4.5f, 455.44));
		flightList.add(new Flight(4, "United", "Sacramento", "Chicago", 3.4f, 243.53));
		flightList.add(new Flight(5, "Blue", "Harrisburgh", "Denver", 2.4f, 353.63));
		flightList.add(new Flight(11, "Delta", "Boston", "Portland", 1.6f, 534.35));
		flightList.add(new Flight(6, "Alaska", "Austin", "Little Rock", 2.4f, 235.23));
		flightList.add(new Flight(1, "Delta", "New York", "Portland", 3.4f, 535.66));
		flightList.add(new Flight(2, "United", "San Fransisco", "Boston", 2.4f, 234.55));
		flightList.add(new Flight(10, "Delta", "Jersey City", "Pittsburgh", 2.7f, 323.42));
		flightList.add(new Flight(8, "United", "London", "Chicago", 2.5f, 634.63));
		flightList.add(new Flight(9, "SouthWest", "Pittsburgh", "Portland", 3.7f, 745.45));

		System.out.println(("All flights in no particular order: ").toUpperCase());
		printFlights(flightList);
		System.out.println("-----------------------------------------------------------------------------------------");

		
		Collections.sort(flightList, Collections.reverseOrder());
		System.out.println(("\n\nprinting all flights in order by id").toUpperCase());
		printFlights(flightList);
		System.out.println("-----------------------------------------------------------------------------------------");

		
		Collections.sort(flightList,(Flight f1,Flight f2)->{
			Float float1=f1.getRatings();
			Float float2=f2.getRatings();
			return f2.compareTo(f1);
		});
		System.out.println(("\n\nPrinting all flights sorted based on rating").toUpperCase());
		printFlights(flightList);
		System.out.println("-----------------------------------------------------------------------------------------");
		
		
		Collections.sort(flightList, (Flight p1, Flight p2)->{
			int x = new FlightDestinationComparator().compare(p1, p2);
			if (x == 0) {
				Double tPrice1 = p1.getTicketPrice();
				Double tPrice2 = p2.getTicketPrice();
				x = p2.compareTo(p1);
			}
			return x;
		});
		System.out.println("\n\nPrinting all flights sorted by destination, then ticket price");
		printFlights(flightList);
		System.out.println("-----------------------------------------------------------------------------------------");
		
		
		
	}

	public static void printFlights(List<Flight> flightList) {
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
		}
	}
	
}



/*
 * 
 * User Stories:
 * 1. create flight object with
 *  int fid, String name, String source, String destination, float ratings, double ticketPrice
 * 2. fill out object with OOP best practices, getters and setters, explicit constructors
 * 3. make some type of list of the objects in the main driver
 * 4. print them out to the console organized by id with comparable
 * 5. print them out organized by multiple states with comporator
 *
 * */
 