package eg2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductMain {

	public static void main(String[] args) {
		
		List<Product> productList=new ArrayList<>();
		productList.add(new Product(100, "TV", 12.22, 3.3f));
		productList.add(new Product(120, "TV", 11.22, 4.3f));
		productList.add(new Product(101, "TV", 13.22, 2.3f));
		productList.add(new Product(900, "TV", 13.22, 1.3f));
		productList.add(new Product(119, "TV", 12.22, 4.29f));
		productList.add(new Product(104, "TV", 12.22, 1.13f));
		System.out.println("Printing all products");
		printProducts(productList);
		
		Collections.sort(productList);//p1.compareTo(p2)
		System.out.println("\n\nPrinting all products sorted based on id");
		printProducts(productList);
		
		Collections.sort(productList,new ProductCostComparator());//Java 7 way
		System.out.println("\n\nPrinting all products sorted based on cost");
		printProducts(productList);
		
		
		//Java 8 way
		Collections.sort(productList,(Product p1,Product p2)->{
			Float f1=p1.getRating();
			Float f2=p2.getRating();
			return f2.compareTo(f1);
		});
		System.out.println("\n\nPrinting all products sorted based on rating");
		printProducts(productList);
		
		//Java 8 way
				Collections.sort(productList,(Product p1,Product p2)->{
					int x=new ProductCostComparator().compare(p1,p2);
					if(x==0) {
					Float f1=p1.getRating();
					Float f2=p2.getRating();
					x= f2.compareTo(f1);
					}
					return x;
				});
				System.out.println("\n\nPrinting all products sorted based on cost(asc) and then by rating(desc)");
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
 */
