package com.revature.hashmap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
			
		// TODO Auto-generated method stub
		Map<Integer, Employee> mapEmployee = new TreeMap<>();
		mapEmployee.put(1001, new Employee(1001, "Ben", "Manager", 42000.00, 10));
		mapEmployee.put(1002, new Employee(1002, "Richard", "Developer", 45000.00, 3));
		mapEmployee.put(1003, new Employee(1003, "Sergio", "Full Stack", 60000.00, 5));
		mapEmployee.put(1004, new Employee(1004, "Jason", "Associate", 22000.00, 2));
		mapEmployee.put(1005, new Employee(1005, "Mark", "CEO", 100000.00, 15));
		mapEmployee.put(1006, new Employee(1006, "Diego", "Manager", 50000.00, 12));
		mapEmployee.put(1000, new Employee(1000, "Ravi", "Manager", 12333.44, 10));
		mapEmployee.put(1100, new Employee(1100, "Ben", "Trainer", 423333.44, 15));
		mapEmployee.put(1020, new Employee(1020, "Sim", "Manager", 233.44, 12));
		mapEmployee.put(1009, new Employee(1009, "Jean", "Developer", 1233.44, 13));
		mapEmployee.put(1010, new Employee(1010, "Dora", "Manager", 2333.44, 4));
		mapEmployee.put(1099, new Employee(1099, "Parkker", "Associate", 333.44, 9));
		
		// Printing IDs of employees who has more than 10 years of experience
		System.out.println("Printing all Employees");
		for(Entry<Integer, Employee> e: mapEmployee.entrySet()) {
			System.out.println((Employee)e.getValue());
		}
		System.out.println("\n");
		
		// Printing IDs of employees who has more than 10 years of experience
		System.out.println("Printing all IDs of Employees who are Managers");
		for(Entry<Integer, Employee> e: mapEmployee.entrySet()) {
			if(e.getValue().getDesignation().equals("Manager")) {
				System.out.println(e.getKey());
			}
		}
		System.out.println("\n");
		
		// Printing IDs of employees who has more than 10 years of experience
		System.out.println("Printing IDs of employees who has more than 10 years of experience");
		for(Entry<Integer, Employee> e: mapEmployee.entrySet()) {
			if(e.getValue().getYop() > 10) {
				System.out.println(e.getKey());
			}
		}
		System.out.println("\n");
		
		// Printing IDs of employees whose name ends whit either N or A
		System.out.println("Printing IDs of employees whose name ends whit either N or A");
		for(Entry<Integer, Employee> e: mapEmployee.entrySet()) {
			if(e.getValue().getName().endsWith("n") || e.getValue().getName().endsWith("a")) {
				System.out.println(e.getKey());
			}
		}
		System.out.println("\n");
		
		// Printing IDs of employees who has the salary range between 100 to 1000
		System.out.println("Printing IDs of employees who has the salary range between 100 to 1000");
		for(Entry<Integer, Employee> e: mapEmployee.entrySet()) {
			if(e.getValue().getSalary() > 100 && e.getValue().getSalary() < 1000) {
				System.out.println(e.getKey());
			}
		}
		System.out.println("\n");
		
	}

}
