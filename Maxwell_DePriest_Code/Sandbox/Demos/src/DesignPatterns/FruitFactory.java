package DesignPatterns;

public class FruitFactory {

	public FruitFactory() {
		// TODO Auto-generated constructor stub
	}

	public Fruit getFruit(String name) {
		Fruit f = null;
		if (name.equalsIgnoreCase("Apple")) {
			f = new Apple("Apple", "red", 100);
			
		} else if (name.equalsIgnoreCase("Kiwi")) {
			f = new Kiwi("Kiwi", true);
		}
		
		return f;
	}


}
