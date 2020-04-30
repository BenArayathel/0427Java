package eg2;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ProductMain {

	public static void main(String[] args) {
		
		Set<Product> productList = new HashSet<>();
		productList.add(new Product(100, "TV", 12.22, 3.3f));
		productList.add(new Product(120, "TV", 11.22, 4.3f));
		productList.add(new Product(101, "TV", 13.22, 2.3f));
		productList.add(new Product(100, "TV", 14.22, 1.3f));
		productList.add(new Product(119, "TV", 12.22, 4.29f));
		productList.add(new Product(104, "TV", 12.22, 1.13f));
		System.out.println("Unsorted products, but no duplicates because we overwrote equals "
				+"to tell the compiler how to tell if the products are equal to each other");
		printProducts(productList);
		
		Set<Product> productTree = new TreeSet<>();
		productTree.add(new Product(100, "TV", 12.22, 3.3f));
		productTree.add(new Product(120, "TV", 11.22, 4.3f));
		productTree.add(new Product(101, "TV", 13.22, 2.3f));
		productTree.add(new Product(100, "TV", 14.22, 1.3f));
		productTree.add(new Product(119, "TV", 12.22, 4.29f));
		productTree.add(new Product(104, "TV", 12.22, 1.13f));
		System.out.println("\nSorted products with no duplicates because we overwrote equals "
				+"to tell the compiler how to tell if the products are equal to each other"
				+"and we told it how to compare with compareTo");
		printProducts(productTree);
	}
	
	public static void printProducts(Set<Product> productSet) {
		for (Product p : productSet) {
			System.out.println(p);
		}
	}

}

/*
 * Flight (int fid, String name, String source, String destination, float ratings, 
 * 
 * default sorting fid, then customize as you want
 * 
 * also find out about difference between LinkedList and PriorityQueue, the two classes implementing the Queue Interface
 * 
 * NOW USE Set Interface instead of List interface
 * 
 * Sidenote: when you are interacting with a new datastructure or making your own, figure out CRUD first (Create Read Update Delete)
 * 
 * Additional assignment: 
 * 		make your own object other than flight and serialize it and deserialize it
 * 		serialize one of the data structure interfaces (Set, List, Queue) to be comfortable with storing multiple objects
 */
