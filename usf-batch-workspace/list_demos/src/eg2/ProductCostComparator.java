package eg2;

import java.util.Comparator;

public class ProductCostComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		Double d1=o1.getCost();
		Double d2=o2.getCost();
		return d1.compareTo(d2);
	}

}
