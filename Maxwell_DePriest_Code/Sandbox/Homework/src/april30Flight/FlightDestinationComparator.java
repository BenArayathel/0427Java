package april30Flight;

import java.util.Comparator;

public class FlightDestinationComparator implements Comparator<Flight> {
	
	public FlightDestinationComparator() {
		
	}
	@Override
	public int compare(Flight o1, Flight o2) {
		String d1=o1.getDestination();
		String d2=o2.getDestination();
		return d1.compareTo(d2);
	}
}


