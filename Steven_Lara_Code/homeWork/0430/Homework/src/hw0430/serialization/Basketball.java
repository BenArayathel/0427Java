package hw0430.serialization;

import java.io.Serializable;

public class Basketball implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737608813489124693L;
	
	private transient String brand;
	private String color;
	
	public Basketball() {
		this.brand = "Spalding";
		this.color = "Orange";
	}

	public Basketball(String brand, String color) {
		super();
		this.brand = brand;
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Basketball [brand=" + brand + ", color=" + color + "]";
	}
	
	public void bounce() {
		System.out.println("Ball is bouncing...");
	}
	
	public void roll() {
		System.out.println("Ball is rolling...");
	}

}
