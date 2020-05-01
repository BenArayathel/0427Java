package Serialization;

import java.io.Serializable;

public class Car implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1342311189774198008L;
	private String brand;
	private String model;
	private String color;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Car(String brand, String model, String color) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
	}
	
	public void drive() {
		System.out.println("Car is driving");
	}
	
	public void stop() {
		System.out.println("Car is stopping");
	}

	@Override
	public String toString() {
		return "Car [Brand = " + brand + ", Model = " + model + ", Color = " + color + "]";
	}
	
	

}
