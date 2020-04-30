package eg2;

import java.util.Set;
import java.util.TreeSet;

public class ProductMain {

	public static void main(String[] args) {
		
		Set<Product> productList=new TreeSet<>();
		productList.add(new Product(100, "TV", 12.22, 3.3f));
		productList.add(new Product(120, "TV", 11.22, 4.3f));
		productList.add(new Product(101, "TV", 13.22, 2.3f));
		productList.add(new Product(900, "TV", 13.22, 1.3f));
		productList.add(new Product(119, "TV", 12.22, 4.29f));
		productList.add(new Product(104, "TV", 12.22, 1.13f));
		productList.add(new Product(119, "TV", 12.22, 4.29f));
		productList.add(new Product(104, "TV", 12.22, 1.13f));
		System.out.println("Printing all products");
		printProducts(productList);
		


	}
	
	public static void printProducts(Set<Product> productList) {
		for(Product p:productList) {
			System.out.println(p);
		}
	}

}




/*
 * Flight(int fid, String name, String source, String destination, float ratings, double ticketPrice)
 *   
 */
