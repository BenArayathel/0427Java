package Flights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightMain {

	public static void main(String[] args) {
		
		List<Flight> flightList=new ArrayList<>();
		flightList.add(new Flight(330,"BW1010","JFK","MIA",5.4f,110.49));
		flightList.add(new Flight(110,"JB6565","BWI","MIA",1.5f,280.49));
		flightList.add(new Flight(200,"AA1120","JFK","BWI",2.4f,467.29));
		flightList.add(new Flight(143,"SP1010","OLD","NYA",3.7f,250.52));
		flightList.add(new Flight(857,"SW8710","JFK","MIA",5.6f,228.89));
		flightList.add(new Flight(458,"BW2310","WAS","MIA",5.9f,217.53));
		flightList.add(new Flight(123,"BW8645","JFK","MIA",4.4f,250.52));
		flightList.add(new Flight(565,"JB6431","NYA","BWI",4.2f,467.29));
		flightList.add(new Flight(899,"BW6345","JFK","BWI",5.1f,250.52));
		flightList.add(new Flight(254,"BW1031","JFK","MIA",2.1f,110.49));
		
		System.out.println("Print all Flights in Flight List");   
		printFlights(flightList);				  // UNSORTED Print of all Flights 
		
		
		//Print all Flights SORTED in ASCENDING ORDER by Flight ID
		Collections.sort(flightList);             //Sort uses comparable - so it will call the override i1.compareTo(i2) - Ascending Order 
		System.out.println("\nPrint all Flights SORTED in ASCENDING ORDER by Flight ID");
		printFlights(flightList);

		
		//Print all Flights SORTED in DESCENDING ORDER by Rating
		Collections.sort(flightList,(Flight fl1,Flight fl2)->{ // using sort overload to compare two(2) flight object
			Float fr1=fl1.getRating();                         // wrapping float to Float for first object for compare
			Float fr2=fl2.getRating();                         // wrapping float to Float again for second object for compare
			return fr2.compareTo(fr1);                         // comparing flightRating2 to flightRating1 (Descending order) - this will place the highest rating at the TOP
		});
		System.out.println("\nPrint all Flights SORTED in DESCENDING ORDER by Rating");
		printFlights(flightList);
		
		
		//Print all Flights SORTED in ASCENDING ORDER by Ticket Price
		Collections.sort(flightList,(Flight fl1,Flight fl2)->{ // using sort overload to compare two(2) flight object
			Double d1=fl1.getTicketPrice();                    // wrapping double to Double for first object for compare
			Double d2=fl2.getTicketPrice();                    // wrapping double to Double again for second object for compare
			return d1.compareTo(d2);                           // comparing ticketPrice1 to ticketPrice2 (Ascending order) - this will place the cheapest flight at the TOP
		});
		System.out.println("\nPrint all Flights SORTED in ASCENDING ORDER by Ticket Price");
		printFlights(flightList);
		
		
		//Print all Flights SORTED in ASCENDING ORDER by Ticket Price, then DESCENDING ORDER by Rating
		Collections.sort(flightList,(Flight fl1,Flight fl2)->{ // using sort overload to compare two(2) flight object
			Double d1=fl1.getTicketPrice();                    // wrapping double to Double for first object for compare
			Double d2=fl2.getTicketPrice();					   // wrapping double to Double again for second object for compare
			Float f1=fl1.getRating();                          // wrapping float to Float for first object for compare
			Float f2=fl2.getRating();  						   // wrapping double to Double again for second object for compare
			int flPriRate = d1.compareTo(d2);                  // comparing ticketPrice1 to ticketPrice2 (Asc order) - cheapest flight at the TOP -> pass value to int variable "flPriRate" 
			if(flPriRate==0) {                                 // if two flights have the same price 
				flPriRate= f2.compareTo(f1);                   // comparing flightRating2 to flightRating1 (Desc order) -  highest rating at the TOP -> pass value to int variable "flPriRate"
			}
			
			return flPriRate;                                 // return value for flights sorted by ticket price (Asc) and then flight rating (desc)
		});
		System.out.println("\nPrint all Flights SORTED in ASCENDING ORDER by Ticket Price, then DESCENDING ORDER by Rating");
		printFlights(flightList);	
	}

	
	public static void printFlights(List<Flight> flightList) {
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
		}
	}
	
	
}
