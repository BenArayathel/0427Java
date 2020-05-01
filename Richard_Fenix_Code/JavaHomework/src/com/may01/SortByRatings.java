package com.may01;

import java.util.Comparator;

public class SortByRatings implements Comparator<Flight>{

	@Override
	public int compare(Flight o1, Flight o2) {

		if (o1.getRatings() == o2.getRatings()) {
			return 0;
		}
		// switch if first data is greater than second data
		if (o1.getRatings() > o2.getRatings()) {
			return 1;
		}
		if (o1.getRatings() < o2.getRatings()) {
			return -1;
		}

		return 0;

	}
	
	
	

}
