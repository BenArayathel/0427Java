package eg1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eg2.Product;
import eg2.ProductCostComparator;



public class FlightMain {
	
	public static void main(String[] args) {
		
		
		List<Flight> flightList = new ArrayList<>();
		flightList.add(new Flight(101, "Delta", "Phoenix", "India", 1000));
		flightList.add(new Flight(112, "Southwest", "Miami", "Washington DC", 400));
		flightList.add(new Flight(93, "United", "Los Angeles", "Dallas", 400));
		flightList.add(new Flight(104, "Delta", "Chicago", "New York", 350));
		flightList.add(new Flight(236, "Korean", "Phoenix", "Korea", 1200));
		System.out.println("Printing all flights: ");
		printFlights(flightList);
		
		Collections.sort(flightList);
		System.out.println("\n\nPrinting all flights sorted based on fid");
		printFlights(flightList);
		
		Collections.sort(flightList,new flightCostComparator());
		System.out.println("\n\nPrinting all flights sorted based on ticket price");
		printFlights(flightList);
		
		
		Collections.sort(flightList,(Flight p1, Flight p2)->{
			int x=new flightCostComparator().compare(p1,p2);
			if(x==0) {
			Integer f1 = p1.getFid();
			Integer f2 = p2.getFid();
			x= f2.compareTo(f1);
			}
			return x;
		});
		System.out.println("\n\nPrinting all products sorted based on cost, then by id");
		printFlights(flightList);
		
	}
	
	public static void printFlights(List<Flight> flightList) {
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
			
		}
	}
	
	

}
