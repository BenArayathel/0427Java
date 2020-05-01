package eg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class demo1 {
	
	public static void main(String[] args) {
		
		// not preserving order of insertion same as HashSet
		Map<Integer, String> hm = new HashMap<>();
		hm.put(120, "java");
		//System.out.println(hm);
		hm.put(120, "jse");
		hm.put(121, "jse");
		hm.put(122, "jse");
		hm.put(null, "tryThis");
		hm.put(null, null);
		System.out.println(hm);
		System.out.println();
		
		// MAINTAINS ORDER OF INSERTION !!
		Map<Integer, String> l_hm = new LinkedHashMap<>();
		l_hm.put(120, "java");
		//System.out.println(l_hm);
		l_hm.put(119, "jse");
		l_hm.put(120, "array");
		l_hm.put(121, "array");
		l_hm.put(122, "array");
		l_hm.put(null, "tryThis");
		l_hm.put(null, null);
		System.out.println(l_hm);
		System.out.println();

		// CANNOT HAVE NULL
		Map<Integer, String> lhm2 = new LinkedHashMap<>();
		lhm2.put(120, "java");
		//System.out.println(tm);
		lhm2.put(120, "array");
		lhm2.put(121, "array");
		lhm2.put(122, "array");
		//l_hm.put(null, "tryThis");
		//l_hm.put(null, null);
		System.out.println(lhm2);
		System.out.println();
		
		Map<Integer, String> tm = new TreeMap<>(Collections.reverseOrder());
		tm.put(120, "java");
		//System.out.println(tm);
		tm.put(120, "array");
		tm.put(121, "array");
		tm.put(122, "array");
		//l_hm.put(null, "tryThis");
		//l_hm.put(null, null);
		System.out.println(tm);
		System.out.println();
		
		Map<Integer, String> ht = new Hashtable<>();
		ht.put(120, "java");
		//System.out.println(tm);
		ht.put(120, "array");
		ht.put(121, "array");
		ht.put(122, "array");
		//l_hm.put(null, "tryThis");
		//l_hm.put(null, null);
		System.out.println(ht);
		System.out.println();
		
		Map<Integer, List<String>> withS = new Hashtable<>();
		//withS.put("java");
		List<String> al = new ArrayList<>();
		//System.out.println(tm);
		al.add("array1");
		al.add("array2");
		al.add("array3");
		withS.put(6, al);
		//l_hm.put(null, "tryThis");
		//l_hm.put(null, null);
		System.out.println(al);
		System.out.println();
		
		
		
//		for(Entry<Interger, String> e:tm.entrySet()) {
//			System.out.println("Key -> " + e.getKey() +);
//		}
		
		

	}

}




































