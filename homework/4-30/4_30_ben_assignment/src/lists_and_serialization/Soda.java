package lists_and_serialization;

import java.io.Serializable;

public class Soda implements Serializable {
	
	
	private static final long serialVersionUID = 946108669238338469L;
	
	private String name;
	private boolean diet;
	private int calories;
	
	public Soda() {
		super();
	}

	public Soda(String name, boolean diet, int calories) {
		super();
		this.name = name;
		this.diet = diet;
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDiet() {
		return diet;
	}

	public void setDiet(boolean diet) {
		this.diet = diet;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "Soda [name=" + name + ", diet=" + diet + ", calories=" + calories + "]";
	}
	
	
	
	

}
