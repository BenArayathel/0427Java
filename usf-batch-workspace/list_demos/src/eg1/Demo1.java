package eg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Demo1 {

	public static void main(String[] args) {
		LinkedList l1=new LinkedList(); //java.lang.Object
		l1.add("heyyy");
		l1.add(124);
		l1.add(4555.999);
		l1.add(null);
		l1.add(null);
		l1.add(true);
		System.out.println(l1);
		
		List<Integer> li=new LinkedList<>();
		li.add(100);
		li.add(99);
		li.add(100);
		li.add(100);
		li.add(null);
		li.add(null);
		li.add(123);
		li.add(1);
		System.out.println(li);
		li.add(0, 999);
		System.out.println(li);
		
		li.set(0, 1);
		System.out.println(li);
		Integer i=100;
		li.remove(i);//remove is overloaded remove(int) remove(Object)- here it is remove(ObJect)
		System.out.println(li);
		li.remove(0);//here it is remove(position)
		System.out.println(li);
		System.out.println(li.size());
		while(li.remove(i)) {}
		System.out.println("li = "+li);
		
		List<Integer> li2=new ArrayList<>();
		li2.addAll(li);//merge or union
		System.out.println("li2 = "+li2);
		li2.add(99);
		li2.add(55);
		li2.add(2, 88);
		li2.add(1, 78);
		System.out.println("li2 = "+li2);
		//li2.retainAll(li); //common between li2 and li -> results will be stored in li2//intersection
		li2.removeAll(li); // elements of li2 which are not there in li// A-B
		System.out.println("li2 = "+li2);
		li2.clear();
		System.out.println(li2);
		
		Collections.replaceAll(li, null, 900);
		//Collections.sort(li);//ascending
		Collections.sort(li, Collections.reverseOrder());//desc
		System.out.println(li);
		
		Collections.shuffle(li);
		System.out.println(li);
		Collections.reverse(li);
		System.out.println("reversed list "+li);
		//prior doing binary search make sure your collection is sorted in ascending and ascending order only
		Collections.sort(li);
		System.out.println("After sorting "+li);
		System.out.println(Collections.binarySearch(li, 1000));
		System.out.println(Collections.binarySearch(li, 900));
		
		

	}

}
