package eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Demo1 {

	public static void main(String[] args) {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(120, "java");
	//	System.out.println(hm);
		hm.put(120, null);
		hm.put(null, "jme");
		hm.put(null, null);
		hm.put(777, "array");
		hm.put(779, "array");
		hm.put(977, "array");
		hm.put(477, "Collection");
		System.out.println(hm);
		
		Map<Integer, String> lhm=new LinkedHashMap<>();
		lhm.put(120, "java");
	//	System.out.println(hm);
		lhm.put(120, null);
		lhm.put(null, "jme");
		lhm.put(null, null);
		lhm.put(777, "array");
		lhm.put(779, "array");
		lhm.put(977, "array");
		lhm.put(477, "Collection");
		System.out.println(lhm);
		
		//Map<Integer, String> tm=new TreeMap<>(); ascending order
		Map<Integer, String> tm=new TreeMap<>(Collections.reverseOrder()); //descending
		tm.put(120, "java");
	//	System.out.println(hm);
		tm.put(120, null);
		//tm.put(null, "jme"); // key cannot be null - reason treemap sorts based on key
		//tm.put(null, null);
		tm.put(777, "array");
		tm.put(779, "array");
		tm.put(977, "array");
		tm.put(477, "Collection");
		System.out.println("tm = "+tm);
		
		Map<Integer, List<String>> ht=new Hashtable<>();
		List<String> al=new ArrayList<>();
		al.add("hey");
		al.add("hi");
		al.add("helloooo");
		ht.put(120, al);
	//	System.out.println(hm);
	//	ht.put(120, null);
	//	ht.put(null, "jme");
	//	ht.put(null, null);  no null map
//		ht.put(777, "array");
//		ht.put(779, "array");
//		ht.put(977, "array");
//		ht.put(477, "Collection sajhsajhd q	gsj");
//		ht.put(477, "updated");
		System.out.println(ht);
		
		System.out.println(tm.size());
		System.out.println(tm.get(100));
		System.out.println(tm.get(477));
		tm.remove(477);
		System.out.println(tm);
		
		System.out.println(tm.keySet());
		System.out.println(tm.values());
		
		System.out.println("\nIterating Using keySet()");
		//Extract keys in Set and iterate over, lengthy approach.
		Set<Integer> set=tm.keySet();
		for(Integer i:set) {
			System.out.println("Key = "+i+" Value = "+tm.get(i));
		}
		
		System.out.println("\nIterating using entrySet() and Entry");
		
		//Best Way of iterating
		for(Entry<Integer, String> e:tm.entrySet()) {
			System.out.println("Key -> "+e.getKey()+" Value -> "+e.getValue());
		}
		

	}

}
