package eg2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FlightMain {
	
	public static void main(String[] args) {

		// Flight(int fid, String name, String source, String destination, float
		// ratings, double ticketPrice)
		Set<Flight> f_tsList = new TreeSet<>();

		f_tsList.add(new Flight(1, "Alpha", "charter", "MSP", 	5.2f, 222.32));
		f_tsList.add(new Flight(2, "Bb", "private", "Yonkers", 4.2f, 333.32));
		f_tsList.add(new Flight(3, "Cappa", "public", "ATL", 		3.2f, 444.32));
		f_tsList.add(new Flight(4, "Delta", "protected", "CIA", 	2.2f, 500.32));
		f_tsList.add(new Flight(5, "Bc", "default", "IAH", 	8.2f, 500.32));
		f_tsList.add(new Flight(6, "Hi-Air", "statos", "LAX", 	7.2f, 323.32));
		f_tsList.add(new Flight(7, "JavaAir", "collection", "LAX", 6.2f, 323.32));
		f_tsList.add(new Flight(8, "Angular", "observable", "LAX", 5.2f, 777.77));

		System.out.println("Printing Flights");
		System.out.println("Total Flights: " + f_tsList.size());
		System.out.println("---------------");
		//printFlightList(flightList);
		
		List<Flight> flightList = new ArrayList<Flight>(f_tsList);
		
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Double d1 = f1.getTicketPrice();
			Double d2 = f2.getTicketPrice();
			return d1.compareTo(d2);
		});
		printFlightList(flightList);
		
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			Float fL1 = f1.getRatings();
			Float fL2 = f2.getRatings();
			return fL1.compareTo(fL2);
		});
		System.out.println("\n");
		printFlightList(flightList);
		
		/**
		 * Sort by Name, then, Price...
		 */
		Collections.sort(flightList, (Flight f1, Flight f2)->{
			String s1 = f1.getName();
			String s2 = f2.getName();
			
//			Add a = (x, y, z) -> {
//				return x + y + z;
//			};
			return s1.compareTo(s2);
		});
		System.out.println("\n hmmm... by Name ??");
		printFlightList(flightList);
	}
	


	private static void printFlightList(List<Flight> flightList) {
		
		for(Flight f : flightList) {
			
			System.out.println(f);
		}
		
	}



}
