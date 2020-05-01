package DesignPatterns;

public class Apple implements Fruit{

	private String name;
	private String color;
	private int calories;
	public Apple() {
		// TODO Auto-generated constructor stub
	}
	
	public Apple(String name, String color, int calories) {
		this.name = name;
		this.color = color;
		this.calories = calories;
	}

	public boolean isTasty() {
		System.out.println("Yes");
		return true;
		
	}

}
