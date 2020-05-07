package Flight;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.Collections;


public class FlightMain {

	public static void main(String[] args) {
		
		//SET
		Set<Flight> FlightList=new TreeSet<>();
		FlightList.add(new Flight(104, "DELTA" ,"Tampa", "Boston", 0.5f, 104f));
		FlightList.add(new Flight(103, "DELTA" ,"Tampa", "New York", 0.6f, 102f));
		FlightList.add(new Flight(102, "JetBlue" ,"Los Angeles", "Miami", 0.7f, 105f));
		FlightList.add(new Flight(101, "Spirit" ,"San Diego", "San Francisco", 0.8f, 101f));
		System.out.println("Printing list of flights in SET ascending with respect to fid.(Comparable)");
		printFlightList(FlightList);
		System.out.println("-------------------------------------------------------------------------------------------------------");
		//LIST
		List<Flight> listOfFlight=new ArrayList<Flight>();  
		listOfFlight.add(new Flight(106, "DELTA" ,"Tampa", "Boston", 0.5f, 104f));
		listOfFlight.add(new Flight(101, "DELTA" ,"Tampa", "New York", 0.6f, 102f));
		listOfFlight.add(new Flight(105, "JetBlue" ,"Los Angeles", "Miami", 0.7f, 105f));
		listOfFlight.add(new Flight(109, "Spirit" ,"San Diego", "San Francisco", 0.8f, 101f));
		System.out.println("Printing list of flights in LIST ascending with respect to ticket price.(Comparator)");
		Collections.sort(listOfFlight, new FlightComparator());
		printListOfFliht(listOfFlight);
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("Printing list of flights in LIST ascending with respect to Rate.(Comparator)");
		Collections.sort(listOfFlight, new FlightComparatorRate());
		printListOfFliht(listOfFlight);
	}

	private static void printFlightList(Set<Flight> FlightList) {
		for(Flight f:FlightList) {
			System.out.println(f);
		}
	}
	private static void printListOfFliht(List<Flight> listOfFlight) {
		for(Flight f:listOfFlight) {
			System.out.println(f);
		}
		
	}
      
	 
}
