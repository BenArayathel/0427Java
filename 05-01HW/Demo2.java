package treesandmaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Demo2 {
//Map<Country,List<State>> ->you have to override equals() in Country class to differntiate Countries
	// and if it is TreeMap you have to implement Comparable as well.
	public static void main(String[] args) {
		Map<Integer, Employee> empMap=new TreeMap<>();
		empMap.put(100, new Employee(100, "Ravi", "Manager", 12333.44, 10));
		empMap.put(110, new Employee(110, "Ben", "Trainer", 42333.44, 15));
		empMap.put(120, new Employee(120, "Sim", "Manager", 233.44, 12));
		empMap.put(109, new Employee(109, "Jean", "Developer", 1233.44, 13));
		empMap.put(101, new Employee(101, "Dora", "Manager", 2333.44, 4));
		empMap.put(199, new Employee(199, "Parker", "Associate", 333.44, 9));
		
		System.out.println("Printing all Employees");
		for(Entry<Integer, Employee> e: empMap.entrySet()) {
			System.out.println(e.getValue());
		}
		
		System.out.println("Printing all Ids who are Managers");
		for(Entry<Integer, Employee> e: empMap.entrySet()) {
			if(e.getValue().getDesignation().equals("Manager")) {
				System.out.println(e.getKey());
			}
		}
		//Transfer the Map to the List
		List<Employee> empList=new ArrayList<>(empMap.values());
		System.out.println(empList);
		/*
		 * Print Ids of employees who has more than 10 years of exp
		 * Print Ids of employees whose name ends with either n or a
		 * Print Ids of employees who has the salary range between 100 to 1000
		 */
		
		System.out.println("Printing all Employees with more than 10 years of exp");
		for(Entry<Integer, Employee> e: empMap.entrySet()) {
			if(e.getValue().getYop() > 10) {
				System.out.println("Employees with more that 10 years of service: " + e.getKey());
			}
		}
		System.out.println("Printing all Ids of employees whose name ends with either n or a");
		for(Entry<Integer, Employee> e: empMap.entrySet()) {

			if(e.getValue().getName().charAt(e.getValue().getName().length() - 1) != 'a' 
				&& e.getValue().getName().charAt(e.getValue().getName().length() - 1) != 'n' ) {
				System.out.println("No names ending in a or n: " + e.getKey());
			}
		}
		System.out.println("Printing all Ids of employees who has the salary range between 100 to 1000");
		for(Entry<Integer, Employee> e: empMap.entrySet()) {
			if(e.getValue().getSalary() >= 100 && e.getValue().getSalary() <= 1000) {
				System.out.println("Employees whose salary is inclusively between 1oo and 1000: " + e.getKey());
			}
		}
		 
	}
	
	

}
