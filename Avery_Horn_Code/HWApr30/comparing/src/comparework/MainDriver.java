package comparework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainDriver {

	public static void main(String[] args) {
		
		List<Flight> flightPlan = new ArrayList<>();
		flightPlan.add(new Flight(84, "Jetblue", "Tokyo", "Port Au Prince", 1.07f, 299.28));
		flightPlan.add(new Flight(92, "Air Trump", "Washington DC", "Hell", 8.8f, 1000.0));
		flightPlan.add(new Flight(4, "CIAIR", "Geneva", "Langley", 3.42222f, 599.61));
		flightPlan.add(new Flight(76, "GPhoenix", "G Base", "North Pole", 5.3f, 177.88));
		flightPlan.add(new Flight(33, "Air Force 1", "Pentagon", "Obama's House", 5.3f, 301.55));
		
		Set<Flight> flightPlan2 = new TreeSet<>();
		flightPlan2.add(new Flight(84, "Jetblue", "Tokyo", "Port Au Prince", 1.07f, 299.28));
		flightPlan2.add(new Flight(92, "Air Trump", "Washington DC", "Hell", 8.8f, 1000.0));
		flightPlan2.add(new Flight(4, "CIAIR", "Geneva", "Langley", 3.42222f, 599.61));
		flightPlan2.add(new Flight(76, "GPhoenix", "G Base", "North Pole", 5.3f, 177.88));
		flightPlan2.add(new Flight(33, "Air Force 1", "Pentagon", "Obama's House", 5.3f, 301.55));
		
		Collections.sort(flightPlan); //compare to
		printPlan(flightPlan);
		System.out.println();
		
		Collections.sort(flightPlan, new FLComparator()); //Java 7 Comparator
		printPlan(flightPlan);
		System.out.println();
		
		Collections.sort(flightPlan, (Flight c1, Flight c2)->{
			int x = new FLComparator().compare(c1, c2);
			if (x == 0)
			{
				Double d1=c1.getTicketPrice();
				Double d2=c2.getTicketPrice();
				x= d1.compareTo(d2);
			}
			return x;
		});
		printPlan(flightPlan);
		System.out.println();
		printPlan(flightPlan2);
	}
	
	
public static void printPlan(List<Flight> flightPlan)
	{
		for (int i = 0; i < flightPlan.size(); i++) {
			System.out.println(flightPlan.get(i));
	}
	}
public static void printPlan(Set<Flight> flightPlan)
		{
			for (Flight f:flightPlan)
			{
				System.out.println(f);
			}
		}
}

