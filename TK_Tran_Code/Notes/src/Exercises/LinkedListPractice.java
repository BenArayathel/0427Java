import java.util.*;

// Reference: https://www.w3resource.com/java-exercises/collection/index.php#linkedlist

public class LinkedListPractice {
    public static void main(String[] args) {
        
        // 1. Append a specified element to the end of a LinkedList.
        List<String> ll = new LinkedList<>();
        ll.add("Dog");
        ll.add("Cat");
        ll.add("Bird");
        ll.add("Fish");
        ll.add("Cow");
        System.out.println(ll);
    }
}