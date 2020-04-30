package collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        
        // HashSet stores in unsorted order
        Set<String> hs = new HashSet<>();
        hs.add("hello");
        hs.add("java");
        hs.add("jee");
        hs.add("jme");
        hs.add("spark");
        hs.add("service");
        hs.add("service");  // Won't print, Set doesn't accept duplicates
        hs.add(null);
        hs.add(null);   // Won't print, Set doesn't accept duplicates
        System.out.println(hs);

        // LinkedHashSet is the ordered version of HashSet; stores by order of insertion
        Set<String> lhs = new LinkedHashSet<>();
        lhs.add("service");
        lhs.add("hello");
        lhs.add("jme");
        lhs.add("spark");
        lhs.add("java");
        lhs.add("jee");
        lhs.add("service");
        lhs.add(null);
        lhs.add(null);
        System.out.println(lhs);

        // TreeSet stores in ascending order; does not accept null
        Set<String> ts = new TreeSet<>();
        // Set<String> ts = new TreeSet<>(Collections.reverseOrder());  // reverses order
        ts.add("jme");
        ts.add("spark");
        ts.add("jee");
        ts.add("java");
        ts.add("service");
        ts.add("hello");
        ts.add("service");
        // ts.add(null);   // ERROR, cannot store null
        System.out.println(ts);
        System.out.println(ts.size());  // Returns number of elements
        System.out.println(ts.contains("hey")); // Returns true or false

        // Iterating through Sets
        for (String s : ts) {
            System.out.println(s);
        }

        // Removes elements
        ts.remove("jme");   // Removes jme from Set
        System.out.println(ts);
    }
}