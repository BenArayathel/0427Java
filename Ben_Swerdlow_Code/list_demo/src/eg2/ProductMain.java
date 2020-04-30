package eg2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductMain {

	public static void main(String[] args) {
		
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(100, "TV", 12.22, 3.3f));
		productList.add(new Product(120, "TV", 11.22, 4.3f));
		productList.add(new Product(101, "TV", 13.22, 2.3f));
		productList.add(new Product(900, "TV", 14.22, 1.3f));
		productList.add(new Product(119, "TV", 12.22, 4.29f));
		productList.add(new Product(104, "TV", 12.22, 1.13f));
		System.out.println("Unsorted products");
		printProducts(productList);
		
		System.out.println("\nSorted based on id");
		Collections.sort(productList);
		printProducts(productList);
		
		System.out.println("\nSorted based on id in reverse");
		Collections.sort(productList, Collections.reverseOrder());
		printProducts(productList);
		
		// This is the Java 7 way. Avoid using this because you have to make a new class. See below.
		System.out.println("\nSorted based on cost");
		Collections.sort(productList, new ProductCostComparator());
		printProducts(productList);

		// This is the Java 8 way! Use this format! Don't have to make a new class!
		System.out.println("\nSorted based on rating");
		Collections.sort(productList, (Product p1, Product p2)->{
			// Sort based on ratings descending
			Float f1 = p1.getRating();
			Float f2 = p2.getRating();
			return f2.compareTo(f1);
		});
		printProducts(productList);
		
		System.out.println("\nSorted based on cost ascending, then rating descending");
		Collections.sort(productList, (Product p1, Product p2)->{
			// This will sort based on cost first
			int x = new ProductCostComparator().compare(p1, p2);
			// If two costs are the same, the following code will be completed
			// this is because compareTo does a subtraction, so the same will be 0
			if (x==0) {
				Float f1 = p1.getRating();
				Float f2 = p2.getRating();
				x = f2.compareTo(f1);
			}
			return x;
		});
		printProducts(productList);
		
		System.out.println("\nSorted based on cost ascending, then rating descending, then id ascending");
		Collections.sort(productList, (Product p1, Product p2)->{
			// This will sort based on cost first
			int x = new ProductCostComparator().compare(p1, p2);
			// If two costs are the same, the following code will be completed
			// this is because compareTo does a subtraction, so the same will be 0
			if (x==0) {
				Float f1 = p1.getRating();
				Float f2 = p2.getRating();
				x = f2.compareTo(f1);
				if (x==0) {
					x = p1.compareTo(p2);
				}
			}
			return x;
		});
		printProducts(productList);
		
	}
	
	public static void printProducts(List<Product> productList) {
		for (int i = 0; i < productList.size(); i++) {
			System.out.println(productList.get(i));
		}
	}

}

/*
 * Flight(int fid, String name, String source, String destination, float ratings, double ticketPrice)
 * 
 * default sorting fid, then customize as you want
 * 
 * also find out about difference between LinkedList and PriorityQueue, the two classes implementing the Queue Interface
 * 
 * Sidenote: when you are interacting with a new datastructure or making your own, figure out CRUD first (Create Read Update Delete)
 */
