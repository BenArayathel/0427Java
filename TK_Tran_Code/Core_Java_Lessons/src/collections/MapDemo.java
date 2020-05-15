package collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {

	/*
		Map sometimes called Table, Dictionary, Tuple, or Index by Table.
		Data structure that holds data in terms of key/value pairs.
		Why isn't it a Collection root interface? (It's still part of Collections framework, however)
			Bc of its structure..
			Think table with 1 key column and 1 value column.
			Each value is connected to a unique key.
			Collection of keys (key column) called keySet().
			Collection of values (value column) called values().
			Each key/value pair called an entrySet().
		Map is an interface, just like List, Set, and Queue.
			HashMap and LinkedHashMap are classes of Map.
			HashMap does not maintain order of insertion.
			If concerned with order, use LinkedHashMap.
	*/
	public static void main(String[] args) {

		// HashMap stores key/value pairs and doesn't maintain order of insertion
		HashMap<Integer, String> hm = new HashMap<>();
		hm.put(120, "java"); // put() works as insert or update, depends on new or existing values on that key
		hm.put(140, "jse");
		hm.put(null, "jme");
		hm.put(null, "tri"); // Updates null key's value to "tri"
		hm.put(null, null); // Updates null key's value again to null
		hm.put(120, null); // Updates 120 key's value to null
		hm.put(777, "array");
		hm.put(888, "array");
		hm.put(999, "array");
		System.out.println("HashMap: " + hm);

		// LinkedHashMap maintains order of insertion
		LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
		lhm.put(120, "java"); // put() works as insert or update, depends on new or existing values on that key
		lhm.put(140, "jse");
		lhm.put(null, "jme");
		lhm.put(null, "tri");
		lhm.put(null, null);
		lhm.put(120, null);
		lhm.put(777, "array");
		lhm.put(888, "array");
		lhm.put(999, "array");
		lhm.put(999, "array");
		System.out.println("LinkedHashMap: " + lhm);

		// TreeMap maintains order BASED ON KEY (not insertion)
		TreeMap<Integer, String> tm = new TreeMap<>(); // By default, sorts in ASCENDING numerical order
		tm.put(222, "java");
		tm.put(444, "jse");
		// tm.put(null, "jme"); // ERROR, key cannot be null in TreeMap
		// tm.put(null, null);
		tm.put(666, null);
		tm.put(555, "array");
		tm.put(333, "array");
		tm.put(111, "array");
		System.out.println("TreeMap: " + tm);

		// Switch TreeMap to descending order
		TreeMap<Integer, String> tmReversed = new TreeMap<>(Collections.reverseOrder()); // Switches to DESCENDING order
		tmReversed.put(555, "java");
		tmReversed.put(333, "jse");
		// tmReversed.put(null, "jme"); // ERROR, key cannot be null in TreeMap
		// tmReversed.put(null, null);
		tmReversed.put(111, null);
		tmReversed.put(222, "array");
		tmReversed.put(444, "array");
		tmReversed.put(666, "array");
		System.out.println("TreeMap (reversed): " + tmReversed);

		// Hashtable cannot have ANY NULLs and doesn't maintain order of insertion
		Hashtable<Integer, String> ht = new Hashtable<>();
		ht.put(120, "java");
		ht.put(140, "jse");
		// ht.put(null, "jme"); // ERROR, no NULLs allowed
		// ht.put(null, null);
		// ht.put(120, null);
		ht.put(777, "array");
		ht.put(888, "array");
		ht.put(999, "array");
		System.out.println("Hashtable: " + ht);

		// Other useful methods
		System.out.println(tm.size());
		System.out.println(tm.get(100)); // 100 key doesn't exist
		System.out.println(tm.get(222));
		// System.out.println(tm.remove(111)); // Removes key/value pair
		System.out.println(tm.keySet()); // Returns all keysets
		System.out.println(tm.values()); // Returns all values



		// Iterates over Maps (lengthy, using keySet())
		// Extracts keys in Set and iterate.
		Set<Integer> set = tm.keySet();
		for (Integer i : set) {
			System.out.println("Key = " + i + " Value: " + tm.get(i));
		}

		// // Iterates over Maps (improvised, using entrySet() and Entry)
		// for (Entry<Integer, String> e : tm.entrySet()) {
		// 	System.out.println("Key -> " + e.getKey() + " Value: " + e.getValue());
		// }
	}
}