package april30flight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ProjectMain {
	public static void main(String[] args) {
		/*
		 * Tasks:
		 * -Create Flight Class
		 * -Apply Comparable
		 * -Apply Comparator
		 * 		-Play around with List ordering - vector, arraylist, linkedlist
		 * 			-3-layered ordering
		 * 				-name, -ratings, -price
		 * Play around with Sets
		 * 		-HashSet
		 * 		-LinkedHashSet
		 * 		-TreeSet
		 * -See how compareTo interacts with TreeSet
		 * 		-Make a second Flight Class with a different compareTo method
		 * 		-Run TreeSet on both Flight Classes to see the difference
		 */
		
		// int fid, String name, String source, String destination, float ratings, double ticketPrice 
		
		List<Flight> fl = init(); // Creates a List w/ 12 Flight Objects
		
		// illustrate beginning state
		System.out.println("Input Order");
		printer(fl);
		
		// show that comparable implementation works
		System.out.println("compareTo Order");
		Collections.sort(fl);
		printer(fl);
		
		// utilize multi-layered comparator
		Collections.sort(fl, (Flight f1, Flight f2) -> {
			String name1 = f1.getName();
			String name2 = f2.getName();
			int c1= name1.compareTo(name2); // name in alphabetical order
			if (c1 == 0) { // same name, look at ratings
				Float ratings1 = f1.getRatings();
				Float ratings2 = f2.getRatings();
				int c2 = ratings2.compareTo(ratings1); // best rating first
				if (c2 == 0) { // same ratings, look at prices
					Double price1 = f1.getTicketPrice();
					Double price2 = f2.getTicketPrice();
					int c3 = price1.compareTo(price2); // lowest price first
					return c3;
				}
				return c2;
			}
			return c1;
		});
		System.out.println("Comparator Order - Name asc. -> Rating desc. -> price asc.");
		printer(fl);
		
		// Messing around with sets
		
		Set<Flight> fhs  = new HashSet<>(fl);
		Set<Flight> flhs = new LinkedHashSet<>(fl);
		Set<Flight> fts  = new TreeSet<>(fl);
		
		System.out.println("HashSet, no order");
		printer(fhs);
		System.out.println("LinkedHashSet, entry order");
		printer(flhs);
		System.out.println("TreeSet, comparable order");
		printer(fts);
		
		
		Set<Flight2> fts2 = init2();
		System.out.println("TreeSet, different compareTo method, order by ratings");
		printer2(fts2);
		
	}
	
	public static List<Flight> init() {
		Flight tsf1 = new Flight(1254, "TrueSky", "Los Angeles", "Houston", 2.56f, 185.99);
		Flight tsf2 = new Flight(2715, "TrueSky", "Los Angeles", "Houston", 2.56f, 155.99);
		Flight tsf3 = new Flight(3698, "TrueSky", "Los Angeles", "Houston", 2.41f, 125.99);
		Flight tsf4 = new Flight(7114, "TrueSky", "Los Angeles", "Houston", 2.56f, 105.99);
		Flight awf1 = new Flight(9165, "AirWaze", "Los Angeles", "Houston", 1.41f, 825.99);
		Flight awf2 = new Flight(1732, "AirWaze", "Los Angeles", "Houston", 1.65f, 161.99);
		Flight awf3 = new Flight(2615, "AirWaze", "Los Angeles", "Houston", 1.14f, 1654.99);
		Flight awf4 = new Flight(8172, "AirWaze", "Los Angeles", "Houston", 1.01f, 99999.99);
		Flight pgf1 = new Flight(5472, "Pegasus", "Los Angeles", "Houston", 4.41f, 6143.99);
		Flight pgf2 = new Flight(9781, "Pegasus", "Los Angeles", "Houston", 3.71f, 16459.99);
		Flight pgf3 = new Flight(1652, "Pegasus", "Los Angeles", "Houston", 4.41f, 2320.99);
		Flight pgf4 = new Flight(4892, "Pegasus", "Los Angeles", "Houston", 4.71f, 5430.99);
		
		List<Flight> fl = new ArrayList<>();
		
		fl.add(tsf1);
		fl.add(tsf2);
		fl.add(tsf3);
		fl.add(tsf4);
		fl.add(awf1);
		fl.add(awf2);
		fl.add(awf3);
		fl.add(awf4);
		fl.add(pgf1);
		fl.add(pgf2);
		fl.add(pgf3);
		fl.add(pgf4);
		
		return fl;
	}
	
	public static Set<Flight2> init2() {
		Flight2 tsf1 = new Flight2(1254, "TrueSky", "Los Angeles", "Houston", 2.56f, 185.99);
		Flight2 tsf2 = new Flight2(2715, "TrueSky", "Los Angeles", "Houston", 2.56f, 155.99);
		Flight2 tsf3 = new Flight2(3698, "TrueSky", "Los Angeles", "Houston", 2.41f, 125.99);
		Flight2 tsf4 = new Flight2(7114, "TrueSky", "Los Angeles", "Houston", 2.56f, 105.99);
		Flight2 awf1 = new Flight2(9165, "AirWaze", "Los Angeles", "Houston", 1.41f, 825.99);
		Flight2 awf2 = new Flight2(1732, "AirWaze", "Los Angeles", "Houston", 1.65f, 161.99);
		Flight2 awf3 = new Flight2(2615, "AirWaze", "Los Angeles", "Houston", 1.14f, 1654.99);
		Flight2 awf4 = new Flight2(8172, "AirWaze", "Los Angeles", "Houston", 1.01f, 99999.99);
		Flight2 pgf1 = new Flight2(5472, "Pegasus", "Los Angeles", "Houston", 4.41f, 6143.99);
		Flight2 pgf2 = new Flight2(9781, "Pegasus", "Los Angeles", "Houston", 3.71f, 16459.99);
		Flight2 pgf3 = new Flight2(1652, "Pegasus", "Los Angeles", "Houston", 4.41f, 2320.99);
		Flight2 pgf4 = new Flight2(4892, "Pegasus", "Los Angeles", "Houston", 4.71f, 5430.99);
		
		Set<Flight2> fl = new TreeSet<>();
		
		fl.add(tsf1);
		fl.add(tsf2);
		fl.add(tsf3);
		fl.add(tsf4);
		fl.add(awf1);
		fl.add(awf2);
		fl.add(awf3);
		fl.add(awf4);
		fl.add(pgf1);
		fl.add(pgf2);
		fl.add(pgf3);
		fl.add(pgf4);
		
		return fl;
	}
	
	public static void printer(List<Flight> fl) {
		for (Flight f : fl) {
			System.out.println(f);
		}
	}
	
	public static void printer(Set<Flight> s) {
		for (Flight f:s) {
			System.out.println(f);
		}
	}
	
	public static void printer2(Set<Flight2> s) {
		for (Flight2 f:s) {
			System.out.println(f);
		}
	}
}
