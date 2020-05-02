package com.examples.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eg2.Employee;

public class employeeFilter {

	public static void main(String[] args) {
		// from map_demos/eg2/Demo2
		Map<Integer, Employee> empMap = new HashMap<>();
		
		empMap.put(100, new Employee(100, "Ravi", "Manager", 12333.44, 10));
		empMap.put(110, new Employee(110, "Ben", "Trainer", 42333.44, 15));
		empMap.put(120, new Employee(120, "Sim", "Manager", 2333.44, 12));
		empMap.put(109, new Employee(109, "Jean", "Developer", 1233.44, 13));
		empMap.put(101, new Employee(101, "Dora", "Manager", 2333.44, 10));
		empMap.put(199, new Employee(199, "Parker", "Associate", 333.44, 9));
		// end code from map_demos/eg2/Demo2
		
		System.out.println("Id's of employees with more than 10 years of experience:");
		for (Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getYop() > 10) {
				System.out.println(e.getKey());
			}
		}
		
		System.out.println("Id's of employees whose name ends with either \"n\" or \"a\":");
		for (Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getName().endsWith("n") || 
					e.getValue().getName().endsWith("a")) {
				System.out.println(e.getKey());
			}
		}
		
		System.out.println("Id's of employees who have salaries between 100 and 1000");
		for (Entry<Integer, Employee> e : empMap.entrySet()) {
			if (e.getValue().getSalary() > 100 &&
					e.getValue().getSalary() < 1000) {
				System.out.println(e.getKey());
			}
		}
	}

}
/*
 * Homework: 
 * Print Ids of employees who have more than 10 years of experience
 * Print Ids of employees whose name ends with either "n" or "a"
 * Print Ids of employees who have salaries between 100 to 1000
 */
