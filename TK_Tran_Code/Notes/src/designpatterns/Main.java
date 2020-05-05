package designpatterns;

import designpatterns.Singleton.Car;
import designpatterns.Singleton.SingletonCar;

public class Main {
    public static void main(String[] args) {

        /* SINGLETON PATTERN */
        // Instantiating normally
        Car car1 = new Car();
        Car car2 = new Car();
        car1.color = "blue"; // This modifies only one instance (car1); we want to modify all instances of new Car objects.
        System.out.println(car1.color); // blue
        System.out.println(car2.color); // red

        // Instantiating w/ singleton class
        SingletonCar myCar1 = SingletonCar.getCar(); // Uses singleton class' constructor to create instance.
        SingletonCar myCar2 = SingletonCar.getCar(); // Uses same singleton class to create a another instance.
        myCar1.color = "green"; // Changing color of one car now changes color of every other car instance.
        System.out.println(myCar1.color); // green
        System.out.println(myCar2.color); // green



        /* FACTORY PATTERN */
		
    }
}