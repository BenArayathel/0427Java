package maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Iterator_Demo {

	public Iterator_Demo() {
		
	}

	public static void main(String[] args) {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(120, "Java");
		hm.put(123, "JSE");
		hm.put(222, "JME");
		hm.put(111, "TRY"); 
		hm.put(450, "Name");
		hm.put(150, "Name");
		hm.put(454, "Paul");
		System.out.println(hm);
		System.out.println();
		System.out.println("Before deletion");
		System.out.println(hm);
//		for(Entry<Integer, String> e: hm.entrySet()) {
//			if(e.getKey()%10 ==0) {
//				hm.remove(e.getKey());  //Causes concurrentModificationException because hm and e are trying to modify same data
//			}
//		}
		
		/*
		 *  Enumerator (read only from top to bottom)
		 *  Iterator (read and remove from top to bottom)
		 *  ListIterator (add, read, remove, and is bi-directional but applicable only on List
		 */
		// if it is List or set then you can aassign to iterator by doing list.iterator() for List and 
		// set.iterator for set and Map check out below
		Iterator<Entry<Integer, String>> i = hm.entrySet().iterator();
		while(i.hasNext()) {
			Entry<Integer, String> e = i.next();
			if(e.getKey() % 10 ==0) {
				i.remove();
			}
		}
		System.out.println("\nAfter Deletion");
		System.out.println(hm);

	}

}
