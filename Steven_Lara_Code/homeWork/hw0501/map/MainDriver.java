package hw0501.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MainDriver {
	
	/*
	 * Print IDs of employees who has more than 10 years of experience
	 * Print IDs of employees whose names end with either n or a
	 * Print IDs of employees who has the salary range between 100000 to 200000
	 */

	public static void main(String[] args) {
		
		Map<Integer, Employee> em = new HashMap<Integer, Employee>();
		em.put(3, new Employee("Tyler", 13, 250000));
		em.put(2, new Employee("Luke", 11, 190000));
		em.put(4, new Employee("Peter", 1, 65000));
		em.put(5, new Employee("Dylan", 5, 95000));
		em.put(1, new Employee("Mike", 9, 150000));
		
		for(Entry<Integer, Employee> e : em.entrySet()) {
			System.out.println(e.getValue());
		}
		
		System.out.println("\nPrinting all IDs who have more than 10 years of experience...\n");
		
		for(Entry<Integer, Employee> e : em.entrySet()) {
			if (e.getValue().getYearsOfExperience() > 10) {
				System.out.println("ID#" + e.getKey() + " --> " + e.getValue());
			}
		}
		
		System.out.println("\nPrinting all IDs whose names end with either n or a...\n");
		
		for (Entry<Integer, Employee> e : em.entrySet()) {
			String lastChar = e.getValue().getName().substring(e.getValue().getName().length() - 1);
			if (lastChar.equals("n") || lastChar.equals("a")) {
				System.out.println("ID#" + e.getKey() + " --> " + e.getValue());
			}
		}
		
		System.out.println("\nPrinting all IDs who have a salary range between $100,000 and $200,000...\n");
		
		for (Entry<Integer, Employee> e : em.entrySet()) {
			if (e.getValue().getSalary() > 100000 && e.getValue().getSalary() < 200000) {
				System.out.println("ID#" + e.getKey() + " --> " + e.getValue());
			}
		}
		
	}
	
}
