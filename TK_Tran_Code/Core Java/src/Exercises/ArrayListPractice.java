import java.util.*;

// Reference: https://www.w3resource.com/java-exercises/collection/index.php#arraylist

public class ArrayListPractice {
    public static void main(String[] args) {
        
        // 1. Create an ArrayList and add some strings.
        List<String> al = new ArrayList<>();
        al.add("Red");
        al.add("Green");
        al.add("Blue");
        al.add("Yellow");
        al.add("Purple");
        System.out.println(al);

        // 2. Iterate through all elements in the ArrayList.
        for (String s : al) {
            System.out.println(s);
        }

        // 3. Insert an element into the ArrayList at specified index.
        al.add(0, "Pink");
        System.out.println("Pink insertion: " + al);

        // 4. Retrieve a specific element from the ArrayList.
        String firstElement = al.get(0);
        System.out.println("First element = " + firstElement);

        // 5. Update a specific element in the ArrayList.
        al.set(1, "Lime");
        System.out.println("Updated 1st index = " + al);

        // 6. Remove a specific element from the ArrayList.
        al.remove(2);
        System.out.println("After removing 2nd index = " + al);

        // 7. Search for an element in the ArrayList.
        if (al.contains("Red")) {
            System.out.println("Found the element.");
        } else {
            System.out.println("Element not found.");
        }

        // 8. Sort the ArrayList.
        Collections.sort(al);
        System.out.println("Sorted in alphametical order = " + al);

        // 9. Copy one ArrayList into another.
        List<String> al1 = new ArrayList<>();
        Collections.copy(al, al1);
        System.out.println("Source list = " + al);
        System.out.println("Destination list = " + al1);

        // 10. Shuffle elements in the ArrayList
        Collections.shuffle(al);
        System.out.println("Shuffled elements = " + al);

        // 11. Reverse elements in the ArrayList
        Collections.reverse(al);
        System.out.println("Reversed elements = " + al);

        // 12. Extract a portion of the ArrayList
        List<String> subList = al.subList(0, 1);
        System.out.println("Extracted from 0th to 1st index = " + subList);

        // 13. Compare two ArrayLists
    }
}