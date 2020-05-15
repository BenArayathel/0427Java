package collections;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListDemo {
    public static void main(String[] args) {

		// ArrayList: like a resizable array; ArrayList automatically grows as elements are added.
		ArrayList<String> a1 = new ArrayList<>();
		a1.add("TK");
		a1.add("Pham");
        a1.add("Tran");
        // a1.add(123); // ERROR, this List only accepts Strings
        System.out.println("ArrayList Elements: " + a1);

        // Iterating ArrayList
        for (String s : a1) {
            System.out.println(s);
        }

        // ArrayList Size
        System.out.println("ArrayList Size: " + a1.size());

        // LinkedList: elements (nodes) linked with each other using pointers.
        // Each element has a reference (address/pointer) to the next element.
		LinkedList<String> l1 = new LinkedList<>();
		l1.add("Ly");
		l1.add("Cam");
        l1.add("Ngo");
		System.out.println();
		System.out.println("LinkedList Elements: " + l1);

		// li1.retainAll(li2) returns matching elements bt li1 and li2.
        // li1.removeAll(li2) removes all matching elements bt li1 and li2, from li1.
    }
}