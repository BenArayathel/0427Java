package DesignPatterns;

public class Kiwi implements Fruit{
	private String name;
	private boolean isHairy;

	public Kiwi(String name, boolean isHairy) {
		this.name = name;
		this.isHairy = isHairy;
	}

	public Kiwi() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isTasty() {
		
		System.out.println("No");
		return false;
		
	}

}
