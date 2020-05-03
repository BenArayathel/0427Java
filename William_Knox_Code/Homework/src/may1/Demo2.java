package may1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Homework:
 * Print ids of employees who have more than 10 years of experience
 * Print ids of employees whose name ends with either n or a
 * Print ids of employees who have a salary range between 100 and 1000 (going to assume that this is inclusive)
 */

public class Demo2 {
	public static void main(String[] args) {
		Map<Integer, Employee> empMap = new HashMap<>();
		empMap.put(100, new Employee(100, "Ravi", "Manager", 12333.44, 10));
		empMap.put(110, new Employee(110, "Ben", "Trainer", 42333.44, 15));
		empMap.put(120, new Employee(120, "Sim", "Manager", 233.44, 12));
		empMap.put(109, new Employee(109, "Jean", "Developer", 1233.44, 13));
		empMap.put(101, new Employee(101, "Dora", "Manager", 2333.44, 4));
		empMap.put(199, new Employee(199, "Parker", "Associate", 333.44, 9));
		
		System.out.println("Printing out the IDs of all employees have more than 10 years of experience");
		for(Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getYop() > 10) {
				System.out.println(e.getValue().getId());
			}
		}
		System.out.println();
		System.out.println("Printing out the IDs of all employees whose name ends with either n or a");
		for(Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getName().endsWith("n") || e.getValue().getName().endsWith("a")) {
				System.out.println(e.getValue().getId());
			}
		}
		System.out.println();
		System.out.println("Printing out the IDs of all employees who have a salary range between 100 and 1000");
		for(Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getSalary() >= 100 && e.getValue().getSalary() <= 1000) {
				System.out.println(e.getValue().getId());
			}
		}
	}
}
