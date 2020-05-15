import java.util.*;

// Reference: https://www.w3resource.com/java-exercises/collection/index.php#hashset

public class HashSetPractiece {
    public static void main(String[] args) {
        
        // 1. Append a specified element to the end of a HashSet.
        Set<String> hs = new HashSet<>();
        hs.add("Car");
        hs.add("Plane");
        hs.add("Train");
        hs.add("Boat");
        hs.add("Wagon");
        System.out.println(hs);
    }
}