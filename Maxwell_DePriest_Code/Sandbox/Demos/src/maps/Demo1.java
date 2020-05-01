package maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Demo1 {

	public Demo1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(120, "Java");
		hm.put(123, "JSE");
		hm.put(null, "JME");
		hm.put(null, "TRY"); // Only 1 key can be null
		hm.put(456, "Name");
		hm.put(452, "Name");
		hm.put(454, "Paul");
		System.out.println(hm);
		System.out.println();
		
		Map<Integer, String> lhm=new LinkedHashMap<>();
		// Map<Integer, List<String>> nestedListMap = new LinkedHashMap<>();  //Can use more collections/maps as value
		lhm.put(120, "Java");
		lhm.put(125, "JSE");
		lhm.put(null, "JME");
		lhm.put(null, "TRY"); // Only 1 key can be null
		lhm.put(456, "Name");
		lhm.put(452, "Name");
		lhm.put(454, "Paul");
		System.out.println(lhm);
		System.out.println();
		
		Map<Integer, String> tm=new TreeMap<>(); // Default sort by ID is ascending
		Map<Integer, String> tmD = new TreeMap<>(Collections.reverseOrder()); // Sorted in descending order
		tm.put(120, "Java");
		tm.put(120, "JSE");
		tm.put(45, "JME");
		//tm.put(null, "TRY"); // Key cannot be null
		tm.put(660, null);
		tm.put(456, "Name");
		tm.put(666, "The Beast");
		tm.put(454, "Paul");
		System.out.println(tm);
		System.out.println();
		
		Map<Integer, String> hT=new Hashtable<>(); //Maintains order of insertion
		hT.put(120, "Java");
		hT.put(125, "JSE");
		
		//hT.put(null, "TRY"); // No null map
		hT.put(456, "Name");
		hT.put(452, "Name");
		hT.put(454, "Paul");
		System.out.println(hT);
		hT.remove(452); // Removes key/value pair
		
		System.out.println(hT.keySet());
		System.out.println(hT.values());
		
		System.out.println("\nUsing a keySet()");
		Set<Integer> set = tm.keySet();
		for (Integer i:set) {
			System.out.println("Key =" + i + "  value- " + tm.get(i));
		}
		
		System.out.println("\n Iterating using entrySet() and Entry");
		
		// Best Practice
		for(Entry<Integer, String> e:tm.entrySet()) {
			System.out.println("Key -> " + e.getKey() + " Value -> " + e.getValue());
		}	

	}

}
