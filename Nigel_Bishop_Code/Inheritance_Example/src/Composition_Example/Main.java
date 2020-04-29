package Composition_Example;

public class Main {

	public static void main(String[] args) {
		
		Organization o = new Organization(10,"Home Tech.","Seattle, Washingtion");
		Address a = new Address(5,"121 5th street","Homestead","Washington",11233);
		Person p = new Person(5,"John","Doe",25);
		Employee e = new Employee(10,"Web Developer",a,o);
		
		e.printEmployee();
		
	}

}
