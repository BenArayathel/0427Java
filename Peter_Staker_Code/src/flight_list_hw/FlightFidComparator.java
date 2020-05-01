package flight_list_hw;

import java.util.Comparator;

public class FlightFidComparator implements Comparator<Flight>{

		@Override
		public int compare(Flight o1, Flight o2) {
			Integer d1 = o1.getFid();
			Integer d2 = o2.getFid();
			return d1.compareTo(d2);
		}
		
}
	