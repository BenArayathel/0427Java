package com.april30;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight o1, Flight o2) {
		return Double.compare(o1.getTicketPrice(), o2.getTicketPrice());
	}

}
