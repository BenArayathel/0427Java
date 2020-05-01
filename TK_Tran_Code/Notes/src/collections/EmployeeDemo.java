package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDemo {
    public static void main(String[] args) {

        Map<Integer, Employee> empMap = new HashMap<>();
        empMap.put(100, new Employee(100, "Ravi", "Developer", 50000.00, 14));
        empMap.put(150, new Employee(150, "Ben", "Trainer", 90000.00, 17));
        empMap.put(120, new Employee(120, "Sim", "COO", 80000.00, 12));
        empMap.put(140, new Employee(140, "Jean", "CFO", 60000.00, 11));
        empMap.put(130, new Employee(130, "Dora", "CEO", 100000.00, 16));

        // // Prints all employees
        // System.out.println("Printing all Employees: ");
        // for (Entry<Integer, Employee> e : empMap.entrySet()) {
        //     System.out.println(e.getValue());
        // }

        // // Want all employees whose designation is manager
        // System.out.println("Printing all IDs who are Managers: ");
        // for (Entry<Integer, Employee> e : empMap.entrySet()) {
        //     // Checks designation
        //     if (e.getValue().getDesignation().equals("Manager")) {
        //         System.out.println(e.getKey());
        //     }
        // }

        // Transfer Map to List
        List<Employee> empList = new ArrayList<>(empMap.values()); // Pass in empMap's values
        System.out.println(empList);


        // HW: Print IDs of employees with more than 10 years experience.

        // HW: Print IDs of employees whose name ends with either n or a.

        // HW: Print IDs of employees whose salary range bt 80000 to 100000.

    }
}

/*
    Homework:
    Print IDs of employees with more than 10 years experience.
    Print IDs of employees whose name ends with either n or a.
    Print IDs of employees whose salary range bt 80000 to 100000.
*/