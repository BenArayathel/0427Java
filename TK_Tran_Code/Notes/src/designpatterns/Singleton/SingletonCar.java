package designpatterns.Singleton;

public class SingletonCar {

    private static SingletonCar car;
    public String color;

    // Make constructor private; only class itself has access to this
    private SingletonCar() {
        this.color = "red";
    }

    // Create public static method that accesses constructor inside the class
    public static SingletonCar getCar() {

        // If car object doesn't exist, we create a new one..
        if (car == null) {
            car = new SingletonCar();
        }

        // otherwise, pass the same instance back.
        return car;
    }
}