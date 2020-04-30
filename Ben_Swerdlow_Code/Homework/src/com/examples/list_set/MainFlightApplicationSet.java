package com.examples.list_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainFlightApplicationSet {

	public static void main(String[] args) {
		// We're going to start with duplicates and use hashSet to get rid of them
		// However, we're not able to get rid of the null entry
		Set<Flight> flightHashSet = new HashSet<>();
		flightHashSet.add(new Flight(3047, "LAS-ATL", "Las-Vegas", "Atlanta", 4.0f, 1000.0));
		flightHashSet.add(new Flight(8765, "DTW-ATL", "Detroit", "Atlanta", 5.0f, 100.0));
		flightHashSet.add(new Flight(3333, "MCO-DTW", "Orlando", "Detroit", 3.0f, 300.0));
		flightHashSet.add(new Flight(4812, "ATL-DTW", "Atlanta", "Detroit", 3.0f, 200.0));
		flightHashSet.add(new Flight(9034, "ATL-MCO", "Atlanta", "Orlando", 3.5f, 300.0));
		flightHashSet.add(new Flight(3845, "DTW-MCO", "Detroit", "Atlanta", 2.8f, 100.0));
		flightHashSet.add(new Flight(9786, "ATL-LAS", "Atlanta", "Las-Vegas", 4.0f, 130.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(null);
		System.out.println("Unsorted List");
		printFlights(flightHashSet);
		// Now we're going to start the same way, but get rid of duplicates and null values
		// and sort by fid simultaneously via TreeSet
		Set<Flight> flightTreeSet = new TreeSet<>();
		flightTreeSet.add(new Flight(3047, "LAS-ATL", "Las-Vegas", "Atlanta", 4.0f, 1000.0));
		flightTreeSet.add(new Flight(8765, "DTW-ATL", "Detroit", "Atlanta", 5.0f, 100.0));
		flightTreeSet.add(new Flight(3333, "MCO-DTW", "Orlando", "Detroit", 3.0f, 300.0));
		flightTreeSet.add(new Flight(4812, "ATL-DTW", "Atlanta", "Detroit", 3.0f, 200.0));
		flightTreeSet.add(new Flight(9034, "ATL-MCO", "Atlanta", "Orlando", 3.5f, 300.0));
		flightTreeSet.add(new Flight(3845, "DTW-MCO", "Detroit", "Atlanta", 2.8f, 100.0));
		flightTreeSet.add(new Flight(9786, "ATL-LAS", "Atlanta", "Las-Vegas", 4.0f, 130.0));
		flightTreeSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(new Flight(2349, "MCO-ATL", "Orlando", "Atlanta", 4.0f, 200.0));
		flightHashSet.add(null);
		System.out.println("\nSorted List by fid");
		printFlights(flightTreeSet);
		// We cannot do alternate sortings via TreeSet because, from TreeSet's perspective,
		// there's only 1 order for things to be in: fid order. Collections.sort does not
		// take a TreeSet for this reason (and it doesn't take a Set Interface Object either
		// because Sets other than TreeSet don't have a concept of order).
		List<Flight> flightSetToList = new ArrayList<Flight>(flightTreeSet);

		System.out.println("\nSorted List by cost ascending");
		Collections.sort(flightSetToList, (Flight f1, Flight f2)->{
			Double tpf1 = f1.getTicketPrice();
			Double tpf2 = f2.getTicketPrice();
			return tpf1.compareTo(tpf2);
		});
		printFlights(flightSetToList);

		System.out.println("\nSorted List by rating descending");
		Collections.sort(flightSetToList, (Flight f1, Flight f2)->{
			Float rf1 = f1.getRating();
			Float rf2 = f2.getRating();
			return rf2.compareTo(rf1);
		});
		printFlights(flightSetToList);

		System.out.println("\nSorted List cost ascending, then rating descending");
		Collections.sort(flightSetToList, (Flight f1, Flight f2)->{
			Double tpf1 = f1.getTicketPrice();
			Double tpf2 = f2.getTicketPrice();
			int x = tpf1.compareTo(tpf2);
			if (x==0) {
				Float rf1 = f1.getRating();
				Float rf2 = f2.getRating();
				x = rf2.compareTo(rf1);
			}
			return x;
		});
		printFlights(flightSetToList);
		
		System.out.println("\nSorted List rating descending, then cost ascending");
		Collections.sort(flightSetToList, (Flight f1, Flight f2)->{
			Float rf1 = f1.getRating();
			Float rf2 = f2.getRating();
			int x = rf2.compareTo(rf1);
			if (x==0) {
				Double tpf1 = f1.getTicketPrice();
				Double tpf2 = f2.getTicketPrice();
				x = tpf1.compareTo(tpf2);
			}
			return x;
		});
		printFlights(flightSetToList);

	}

	public static void printFlights(Set<Flight> flightList) {
		for (Flight f : flightList) {
			System.out.println(f);
		}
	}
	
	public static void printFlights(List<Flight> flightList) {
		for (Flight f : flightList) {
			System.out.println(f);
		}
	}

}
