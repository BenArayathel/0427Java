package flight_list_hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightMain {

	public static void main(String[] args) {
		
		List<Flight> flightList=new ArrayList<>();
		flightList.add(new Flight(100, "Alaskan", "SEA", "HOU", 8.4f, 320.42));
		flightList.add(new Flight(302, "Virgin", "CHI", "POR", 8.7f, 409.66));
		flightList.add(new Flight(213, "Southwest", "DAL", "HOU", 4.8f, 98.89));
		flightList.add(new Flight(403, "American", "LA", "DEN", 5.7f, 370.76));
		flightList.add(new Flight(304, "United", "SF", "KC", 6.1f, 260.86));
		flightList.add(new Flight(765, "Hawaiian", "NY", "HON", 7.8f, 729.99));

		System.out.println("Printing all flights");
		printFlights(flightList);
		
		Collections.sort(flightList,(Flight p1,Flight p2)->{
			Integer f1 = p1.getFid();
			Integer f2 = p2.getFid();
			return f2.compareTo(f1);
		});
		System.out.println("\n\nPrinting all flights sorted by fid");
		printFlights(flightList);
		

				Collections.sort(flightList,(Flight p1,Flight p2)->{
					int x = new FlightFidComparator().compare(p1,p2);
					if(x==0) {
					Integer f1 = p1.getFid();
					Integer f2 = p2.getFid();
					x = f2.compareTo(f1);
					}
					return x;
				});
				System.out.println("\n\nPrinting all flights sorted by fid(asc)");
				printFlights(flightList);

	}
	
	public static void printFlights(List<Flight> flightList) {
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
		}
	}

}


