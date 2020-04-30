package eg1;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Demo {

	public static void main(String[] args) {
		Set<String> hs = new HashSet<>();
		hs.add("hello");
		hs.add("java");
		hs.add("jee");
		hs.add("jme");
		hs.add("spark");
		hs.add("service");
		hs.add(null);
		hs.add(null);
		hs.add("java");
		hs.add("java");
		System.out.println(hs);
		System.out.println("No duplicates allowed and does not maintain the order of insertion.");
		System.out.println("Only takes one null value.\n");
		
		Set<String> lhs = new LinkedHashSet<>();
		lhs.add("hello");
		lhs.add("java");
		lhs.add("jee");
		lhs.add("jme");
		lhs.add("spark");
		lhs.add("service");
		lhs.add(null);
		lhs.add(null);
		lhs.add("java");
		lhs.add("java");
		System.out.println(lhs);
		System.out.println("No duplicates allowed and does maintain the order of insertion.");
		System.out.println("Only takes one null value.\n");
		
		// Need to supply an a TreeSet with a generic (won't know how to compare two different objects)
		Set<String> ts = new TreeSet<>();// will sort ascending
//		Set<String> ths = new TreeSet<>(Collections.reverseOrder()); // will reverse order, sort descending
		ts.add("hello");
		ts.add("java");
		ts.add("jee");
		ts.add("jme");
		ts.add("spark");
		ts.add("service");
		//ths.add(null);
		//ths.add(null);
		ts.add("java");
		ts.add("java");
		System.out.println(ts);
		System.out.println("No duplicates allowed and puts elements in order via compareTo (and equals?).");
		System.out.println("Cannot take any null values.\n");
		
		System.out.println(ts.size());
		System.out.println(ts.contains("hello"));
		System.out.println(ts.contains("boogie"));
		
		// need to overwrite the equals method (why?) to use custom Objects with Set Interface Objects
		
		// no indices, so how do you iterate? You user iterator notation
		System.out.println("Printing contents in ALL CAPS");
		for (String s : ts) {
			System.out.println(s.toUpperCase());
		}

	}

}
