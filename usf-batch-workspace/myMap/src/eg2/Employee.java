package eg2;

public class Employee {

	private int id;
	private String name;
	private String designation;
	private double myDouble;
	private int myInt;

	public Employee() {
		super();
	}

	public Employee(int id, String name, String designation, double myDouble, int myInt) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.myDouble = myDouble;
		this.myInt = myInt;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getMyDouble() {
		return myDouble;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", myDouble=" + myDouble
				+ ", myInt=" + myInt + "]";
	}

}
