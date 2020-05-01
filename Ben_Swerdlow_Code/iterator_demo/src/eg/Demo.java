package eg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Demo {

	public static void main(String[] args) {
		Map<Integer, String> hm = new HashMap<>();
		hm.put(120, "java");
		hm.put(120, "jse");
		hm.put(777, "array");
		hm.put(779, "array");
		hm.put(977, "array");
		hm.put(477, "Collection");
		hm.put(10, "jme");
		hm.put(100, "jee");
		hm.put(110, "jse");
		System.out.println("Before deletion");
		System.out.println(hm);
		
		// Remove all K,V pairs whose key is divisible by 10
		
//		for (Entry<Integer, String> e : hm.entrySet()) {
//			// BAD! 2 processes are trying to access or change the same thing at once
//			if (e.getKey()%10==0) {
//				hm.remove(e.getKey());
//			}
//		}
		
		/*
		 * Fix with Iterators
		 * 
		 * Enumerator: read only from top to bottom
		 * 		Iterator (child of Enumerator): can read and remove top to bottom
		 * 			ListIterator (child of Iterator): add, read, remove and is bidirectional (top to bottom, bottom to top),
		 * 												but applicable only on Lists (not used very much in pracice)
		 */
		
		// if it is List or Set, then you can assign to iterator via List.iterator (for list) or Set.iterator (for set)
		// (you'll get concurrent modification exception with list, set, or map) for Map, see below 
		// (you convert to entrySet().iterator() and then use that to loop as shown)
		Iterator<Entry<Integer, String>> i = hm.entrySet().iterator();
		while(i.hasNext()/*checks if iterator has anything to read*/) {
			Entry<Integer, String> e = i.next();// increments the pointer from previous to the next record
			if (e.getKey() % 10 == 0) {
				i.remove();// can't access hm directly, because we're still reading from it, but via iterator proxy
			}
		}
		
		
		System.out.println("\nAfter deletion");
		System.out.println(hm);

	}

}
