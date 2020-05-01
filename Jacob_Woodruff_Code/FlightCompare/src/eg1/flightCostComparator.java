package eg1;

import java.util.Comparator;

public class flightCostComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight f1, Flight f2) {
		Double d1=f1.getTicketPrice();
		Double d2=f2.getTicketPrice();
		
		return d1.compareTo(d2);
	}
	
	
	

}