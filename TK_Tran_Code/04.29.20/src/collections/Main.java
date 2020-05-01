package collections;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        
        // ArrayList holding Product objects
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product(120, "TV", 11.99, 8.3f));
        productList.add(new Product(160, "TV", 16.99, 2.1f));
        productList.add(new Product(110, "TV", 13.99, 5.3f));
        productList.add(new Product(170, "TV", 17.99, 9.2f));
        productList.add(new Product(130, "TV", 12.99, 5.7f));

        // Prints products normally
        System.out.println("Printing products normally: ");
        printProducts(productList);
        System.out.println();

        // Prints products sorted by ID
        System.out.println("Printing products sorted by ID: ");
        Collections.sort(productList);
        printProducts(productList);
        System.out.println();

        // Java 8 method; prints product sorted by rating
        System.out.println("Printing products sorted by rating: ");
        Collections.sort(productList, (Product p1, Product p2) -> {
            Float f1 = p1.getRating();
            Float f2=p2.getRating();

            return f2.compareTo(f1);
		});
		printProducts(productList);
        System.out.println();
    }

    // Iterates through ArrayList and prints each Object on new lines
    public static void printProducts(ArrayList<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i));
        }
    }
    // Can print Products normally like System.out.println(productList); but it's ugly
}

/* 
    Functional Interfaces:
        Comparable
            int compareTo(Object o)
            Collections.sort uses Comparable internally to compare two Objects.
            All 8 Wrapper classes and String class implements Comparable.
        Comparator
            int compare(Object o1, Object o2)
    
    Homework:
        1. Collections: Flight(int fid, String name, String source, String destination, float rating, double price).
        2. Make your own object and serialize/deserialize it.
        3. Serialize one of the data structure interface.
*/