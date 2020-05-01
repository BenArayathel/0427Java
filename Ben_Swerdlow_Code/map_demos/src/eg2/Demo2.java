package eg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Demo2 {
	// Map<Country, List<State>> -> you have to override equals method in Country class to differentiate countries
	// and if this is a TreeMap, you have to implement Comparable as well (needs a compareTo)
	public static void main(String[] args) {
		Map<Integer, Employee> empMap = new HashMap<>();
		
		empMap.put(100, new Employee(100, "Ravi", "Manager", 12333.44, 10));
		empMap.put(110, new Employee(110, "Ben", "Trainer", 42333.44, 15));
		empMap.put(120, new Employee(120, "Sim", "Manager", 2333.44, 12));
		empMap.put(109, new Employee(109, "Jean", "Developer", 1233.44, 13));
		empMap.put(101, new Employee(101, "Dora", "Manager", 2333.44, 10));
		empMap.put(199, new Employee(199, "Parker", "Associate", 333.44, 9));
		
		System.out.println("Employees unsorted (does not maintain order of insertion)");
		for (Entry<Integer, Employee> e : empMap.entrySet()) {
			System.out.println(e.getValue());
		}
		
		System.out.println("\nPrinting all Ids who are managers.");
		for (Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getDesignation() == "Manager") {
				System.out.println(e.getKey());	
			}
		}
		// Transfer the Map to the List
		List<Employee> empList = new ArrayList<>(empMap.values());
		System.out.println(empList);
	}
	
	public static void printEmployees() {}

}
/*
 * Homework: 
 * Print Ids of employees who have more than 10 years of experience
 * Print Ids of employees whose name ends with either "n" or "a"
 * Print Ids of employees who have salaries between 100 to 1000
 */