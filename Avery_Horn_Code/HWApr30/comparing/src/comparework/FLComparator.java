package comparework;

import java.util.Comparator;
public class FLComparator implements Comparator<Flight>{

	@Override
	public int compare(Flight o1, Flight o2) {
		Float d1 = o1.getRatings();
		Float d2 = o2.getRatings();
		return d1.compareTo(d2);
	}

}
