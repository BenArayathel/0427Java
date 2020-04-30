package eg1;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Demo {

	public static void main(String[] args) {
		Set<String> hs=new HashSet<>();
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
		
		Set<String> lhs=new LinkedHashSet<>();
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
		
		//Set<String> ts=new TreeSet<>(Collections.reverseOrder()); for desc
		Set<String> ts=new TreeSet<>(); // for asc
		ts.add("hello");
		ts.add("java");
		ts.add("jee");
		ts.add("jme");
		ts.add("spark");
		ts.add("service");
		//ts.add(null);
		//ts.add(null);
		ts.add("java");
		ts.add("java");
		//ts.add(1222);
		System.out.println(ts);
		System.out.println(ts.size());
		System.out.println(ts.contains("hey"));
		System.out.println(ts.contains("jee"));
		
		for(String s:ts) {
			System.out.println(s.toUpperCase());
		}
		ts.remove("jme");
		System.out.println(ts);

	}

}
