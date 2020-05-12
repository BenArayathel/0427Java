package hw0501.map;

public class Employee {
	
	private String name;
	private int yearsOfExperience;
	private double salary;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, int yearsOfExperience, double salary) {
		super();
		this.name = name;
		this.yearsOfExperience = yearsOfExperience;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", yearsOfExperience=" + yearsOfExperience + ", salary=" + salary + "]";
	}
	

}
