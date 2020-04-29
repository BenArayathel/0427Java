package Composition_Example;

public class Person {

	private int pID;
	private String firstName;
	private String lastName;
	private int age;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(int pID, String firstName, String lastName, int age) {
		super();
		this.pID = pID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public void printPerson() {
		System.out.println("***  PERSON INFORMATION  ***");
		System.out.println("Person ID: " + pID);
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Age: " + age);
	}
}
