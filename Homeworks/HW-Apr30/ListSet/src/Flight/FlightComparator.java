package Flight;
import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {


	public int compare(Flight o1, Flight o2) {
		Float d1=o1.getTicketPrice();
		Float d2=o2.getTicketPrice();
		return d1.compareTo(d2);
	}
}
