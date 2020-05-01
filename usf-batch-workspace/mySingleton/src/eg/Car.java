package eg;

public class Car {

	private int doors;
	private int wheels;

	public Car() {
		super();
	}

	public Car(int doors, int wheels) {
		super();
		this.doors = doors;
		this.wheels = wheels;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	@Override
	public String toString() {
		return "Car [doors=" + doors + ", wheels=" + wheels + "]";
	}
	
	

}
