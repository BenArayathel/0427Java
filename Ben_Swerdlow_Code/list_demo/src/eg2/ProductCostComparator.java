package eg2;

import java.util.Comparator;

public class ProductCostComparator implements Comparator<Product> {

	// This is the long way from Java 7
	@Override
	public int compare(Product p1, Product p2) {
		// Will sort costs ascending
		// if want descending, reverse the order of p1 and p2 in compare body
		Double cp1 = p1.getCost();
		Double cp2 = p2.getCost();
		return cp1.compareTo(cp2);
	}

}
