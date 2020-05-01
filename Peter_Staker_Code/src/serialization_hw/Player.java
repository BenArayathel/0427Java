package serialization_hw;

import java.io.Serializable;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8404146945615917099L;
	
	private String name; 
	private float weight;
	private int height;
	private double salary;

	public Player() {
		
	}
	
	public Player(String name, float weight, int height, double salary) {
		super();
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void run() {
		System.out.println("I'm running!");
	}
	public void injured() {
		System.out.println("I'm hurt :(");
	}
	
	

}