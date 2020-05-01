package DesignPatterns;

public class MainDriver {

	public MainDriver() {
		
	}
	
	public static void main(String[] args) {
		
		
		SingletonCar myCar = SingletonCar.getCar();
		SingletonCar myCar2 = SingletonCar.getCar();
		
		myCar.color = "green";
		
		//System.out.println(myCar.color);
		//System.out.println(myCar2.color);
		
		
		FruitFactory ff = new FruitFactory();
		Fruit f = ff.getFruit("Kiwi"); // All other properties are handles by fruit factory. Hides creation logic
	}

}
