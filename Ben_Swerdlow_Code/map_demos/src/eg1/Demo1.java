package eg1;

import java.security.KeyStore.Entry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.parser.Entity;

public class Demo1 {

	public static void main(String[] args) {
		// Best practice to have Interface as the type of the reference variable
		// unless you need functionality from the child class (rare)
		Map<Integer, String> hm = new HashMap<>();
		// put works as both insert and update
		// (if key exists, update; else, insert)
		hm.put(120, "java");
		System.out.println(hm);
		hm.put(120, "jse");
		hm.put(null, "jme");
		hm.put(null, "tri");
		hm.put(null, null);
		hm.put(777, "array");
		hm.put(779, "array");
		hm.put(977, "array");
		hm.put(477, "Collection");
		System.out.println(hm);
		
		Map<Integer, String> lhm = new LinkedHashMap<>();
		lhm.put(120, "java");
		System.out.println(lhm);
		lhm.put(120, "jse");
		lhm.put(null, "jme");
		lhm.put(null, "tri");
		lhm.put(null, null);
		lhm.put(777, "array");
		lhm.put(779, "array");
		lhm.put(977, "array");
		lhm.put(477, "Collection");
		System.out.println(lhm);
		
		Map<Integer, String> tm = new TreeMap<>();//ascending order, default
		Map<Integer, String> rtm = new TreeMap<>(Collections.reverseOrder());//descending order
		tm.put(120, "java");
		System.out.println(tm);
		lhm.put(120, "jse");
		// can't have null key, can have null value
		// because TreeMap sorts based on key, and can't compare null
//		tm.put(null, "jme");
//		tm.put(null, "tri");
//		tm.put(null, null);
		tm.put(777, "array");
		tm.put(779, "array");
		tm.put(977, "array");
		tm.put(477, "Collection");
		tm.put(0, null);
		System.out.println(tm);
		
		for (int key : tm.keySet()) {
			rtm.put(key, tm.get(key));
		}
		System.out.println(rtm);
		
		Map<Integer, String> ht = new Hashtable<>();
		ht.put(120, "java");
		System.out.println(ht);
		// No entries can be null in a Hashtable
//		ht.put(120, "null");
//		ht.put(null, "jme");
//		ht.put(null, "tri");
//		ht.put(null, null);
		ht.put(0, "yo");
		ht.put((int)1e6, "you");
		ht.put(777, "array");
		ht.put(779, "array");
		ht.put(977, "array");
		ht.put(477, "Collection");
		System.out.println(ht);
		
		// Can have any object as value, including another map, list, or set! 
		Map<Integer, Map<Integer,String>>  hmiis = new HashMap<>();
		hmiis.put(0, hm);
		System.out.println(hmiis);
		
		System.out.println("KV pairs in TreeMap tm");
		System.out.println(tm.size());
		
		System.out.println(tm.get(111111));
		System.out.println(tm.get(777));
		
		// keyset returns a Set
		System.out.println(tm.keySet());
		System.out.println(tm.values());
		
		Set<Integer> set = tm.keySet();
		
		System.out.println("\nTedious way to iterate through map using keySet()");
		for (Integer i : set) {
			System.out.println("Key = "+i+" Value = "+tm.get(i));
		}
		
		System.out.println("\nIterating using entrySet() and Entry");
		for (java.util.Map.Entry<Integer, String> e : tm.entrySet()) {
			System.out.println("Key = "+e.getKey()+" Value = "+e.getValue());
		}

	}

}
