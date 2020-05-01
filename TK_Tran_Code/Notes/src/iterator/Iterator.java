package iterator;

import java.security.KeyStore.Entry;
import java.util.HashMap;

public class Iterator {
    public static void main(String[] args) {

        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(120, "java");
        hm.put(140, "jse");
        hm.put(777, "jme");
        hm.put(888, "array");
        hm.put(999, "Collection");
        System.out.println("HashMap before deletion: " + hm);

        // Want to remove all key/value pairs whose key is multiples of 10 (divisible by 10)?

        // // Bad practice: will throw ConcurrentModificationException
        // for (Entry<Integer, String> e : hm.entrySet()) {
        //     if (e.getKey() % 10 == 0) {
        //         hm.remove(e.getKey());
        //     }
        //     // Tries to read (getKey()) and write (remove(e.getKey)) at the same time, BAD PRACTICE
        // }

        // Best practice: use iterator which can read AND remove concurrently
        Iterator<Entry<Integer, String>> i = hm.entrySet().iterator(); // Assigns map to iterator
        while (i.hasNext()) {
            Entry<Integer, String> e = i.next(); // iterator points to first record

            if (e.getKey() % 10 == 0) { // if that record's key is divisible by 10
                i.remove(); // remove that record
            }
            // Can assign iterators to other collections via list.iterator() for List, set.iterator() for Set, or for Queue.
        }

        System.out.println("HashMap after deletion: " + hm);
    }
}

/*
    Enumerator (read only, from top to bottom)
        Iterator (read AND remove from top to bottom)
            ListIterator (add, read, remove and is bidirectional but applicable only to Lists)
*/