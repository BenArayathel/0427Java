package maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Demo2 {

	public Demo2() {
		// TODO Auto-generated constructor stub
	}
	// Map<Country, List<State>> -> you'd have to override equals() inside Country class to differentiate the list
	// and if it is a treeMap have to implement comparable
	public static void main(String[] args) {
		Map<Integer, Employee> empMap = new HashMap<>();
		empMap.put(100, new Employee(100, "Squirrely Dan", "Tech", 1234.56, 12));
		empMap.put(103, new Employee(103, "Sled Ted", "Management", 1834.56, 1));
		empMap.put(102, new Employee(102, "Darryl", "Janitor", 234.56, 18));
		empMap.put(104, new Employee(104, "Joint Boy", "Driver", 123.56, 8));
		
		System.out.println("Printing All Employees");
		for(Entry<Integer, Employee> e : empMap.entrySet()) {
			System.out.println(e.getValue());
		}
 		// Not working, sort out later
		for(Entry<Integer, Employee> e : empMap.entrySet()) {
			if(e.getValue().getDesignation().equals("Management")) {
				System.out.println(e.getKey());
			}
		}
		
		//Transfer the map to list 
		List<Employee> empList = new ArrayList<>(empMap.values());
		
		
	}
	

}


// ID's of employees who has more than 10 years exp
//Id's whose name ends with either n or a 
//ids who has salary range between 100 to 1000
