package eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Demo1 {

	public static void main(String[] args) {
		/* 
		 * You don't need generics, but they're encouraged for clarity and you'll usually know data type you need
		 * if you don't specify a generic, it will take java.lang.Object (so any Object)
		 * Bad practice to use LinkedList as a reference variable, since  it can behave like a List or a Queue
		 * This can obscure the function of the list
		*/
		//LinkedList l1 = new LinkedList();
		List l1 = new LinkedList();
		
		l1.add("heyyyy");
		l1.add(124);
		l1.add(4555.99);
		l1.add(null);
		l1.add(null);
		l1.add(true);
		System.out.println(l1);

		
		List<Integer> li = new LinkedList<>();
		
		// the following line will not work because not int
//		li.add("qw;ekfj");
		li.add(99);
		li.add(100);
		li.add(100);
		li.add(null);
		li.add(null);
		li.add(123);
		System.out.println(li);
		
		// overloaded add(index, element);
		li.add(0, Integer.MAX_VALUE);
		System.out.println(li);
		
		// updates the value at index 0
		li.set(0, null);
		System.out.println(li);
		
		// removes the value at index 0
		li.remove(0);// remove(int) [index in list] or remove(Object) [removes the first occurance of the Object]
		System.out.println(li);
		li.remove(li.size()-1);
		System.out.println(li);
		// remove all null values
		while (li.remove(null)) {}
		System.out.println(li);
		// will remove all values of 100 (not the index 100)
		while (li.remove(new Integer(100))) {}
		System.out.println(li);
		li.add(100000);
		li.add(99);
		System.out.println(li);
		
		List<Integer> li2 = new ArrayList<>();
		System.out.println("li2 = "+li2);
		li2.addAll(li);
		System.out.println("li2 = "+li2);
		li2.add(99);
		li2.add(99);
		li2.add(55);
		li2.add(2, 88);
		li2.add(1, 78);
		System.out.println("li = "+li);
		System.out.println("li2 = "+li2);
		li2.retainAll(li); // returns common elements between li2 and li stored in li2
		System.out.println("li = "+li);

		System.out.println("Retaining");
		System.out.println("li2 = "+li2);
		li2.removeAll(li); // elements of li2 which are not in li stored in li2
		System.out.println("li2 = "+li2);
		
		li.add(null);
		Collections.replaceAll(li, null, 0);// replaces all null values with 0
		Collections.sort(li);
		System.out.println(li);
		
		// reverseOrer() will reverse the order of the sort
		Collections.sort(li, Collections.reverseOrder());
		System.out.println(li);
		// reverse() will just return the entries in reverse order (regardless of sorting)
		Collections.reverse(li);
		System.out.println(li);
		
		Collections.shuffle(li);
		System.out.println(li);
		
		//prior to doing binary search, make sure your collection is sorted in ascending order only!
		Collections.sort(li);
		System.out.println("After sorting: "+li);
		System.out.println("Now we can do a binary search.");
		// binary search searches for value, if it's found it'll return positive value, if not it'll return negative value
		System.out.println(Collections.binarySearch(li, 99));
		System.out.println(Collections.binarySearch(li, 1));
	}

}
