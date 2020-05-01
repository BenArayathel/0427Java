package eg2;

import java.util.Set;
import java.util.TreeSet;

public class FlightComparableMain {

	public static void main(String[] args) {

		// Flight(int fid, String name, String source, String destination, float
		// ratings, double ticketPrice)
		Set<FlightComparable> flightList = new TreeSet<>();

		flightList.add(new FlightComparable(1, "Alpha", "charter", "MSP", 	5.2f, 500.32));
		flightList.add(new FlightComparable(2, "Beta", "private", "Yonkers", 4.2f, 500.32));
		flightList.add(new FlightComparable(3, "Cappa", "public", "ATL", 		3.2f, 500.32));
		flightList.add(new FlightComparable(4, "Delta", "protected", "CIA", 	2.2f, 500.32));
		flightList.add(new FlightComparable(5, "Gamma", "default", "IAH", 	8.2f, 500.32));
		flightList.add(new FlightComparable(6, "Hi-Air", "statos", "LAX", 	7.2f, 500.32));
		flightList.add(new FlightComparable(7, "Java Air", "collection", "LAX", 6.2f, 500.32));
		flightList.add(new FlightComparable(8, "Angular", "observable", "LAX", 5.2f, 500.32));

		System.out.println("Printing Flights");
		System.out.println("Total Flights: " + flightList.size());
		System.out.println("---------------");
		printFlights(flightList);
	}

	public static void printFlights(Set<FlightComparable> flightList) {

		for (FlightComparable f : flightList) {

			System.out.println(f);
		}

	}

}
