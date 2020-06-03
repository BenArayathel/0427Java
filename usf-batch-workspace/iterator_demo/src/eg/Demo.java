package eg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Demo {

	public static void main(String[] args) {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(120, "java");
		hm.put(777, "array");
		hm.put(779, "array");
		hm.put(977, "array");
		hm.put(477, "Collection");
		hm.put(130, "jme");
		hm.put(140, "jme");
		hm.put(110, "jse");
		hm.put(100, "jee");
		System.out.println("Before deletion");
		System.out.println(hm);
		
//		for(Entry<Integer, String> e: hm.entrySet()) {
//			if(e.getKey()%10==0) {
//				hm.remove(e.getKey());
//			}
//		}
		/*
		 * Enumerator (read only from top to bottom)
		 * Iterator (read and remove from top to bottom)
		 * ListIterator(add, read, remove and is bidirectional but applicable only on List)
		 */
		// if it is List or Set then you can assign to iterator by doing list.iterator() for List and
		// set.iterator() for set and for Map check out below
		Iterator<Entry<Integer, String>> i= hm.entrySet().iterator();
		while(i.hasNext()) {
			Entry<Integer, String> e=i.next();
			if(e.getKey()%10==0) {
				i.remove();
			}
		}
		System.out.println("After Deletion");
		System.out.println(hm);
		

	}

}
