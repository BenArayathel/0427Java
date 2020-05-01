package collections;

public class Employee {
    
    private int id;
    private String name;
    private String destination;
    private double salary;
    private int yearsExperience;

    public Employee() {
    }
    
    public Employee(int id, String name, String destination, double salary, int yearsExperience) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.salary = salary;
        this.yearsExperience = yearsExperience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    @Override
    public String toString() {
        return "Employee [destination=" + destination + ", id=" + id + ", name=" + name + ", salary=" + salary
                + ", yearsExperience=" + yearsExperience + "]";
    }
}