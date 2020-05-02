package designpatterns.Singleton;

/* 
    Lazy Initialization Method
        Pros:
            Object created only if needed; ensures no resources wasted.
            Exception handling is possible in method.
        Cons:
            Condition of null has to be checked every time.
            Instance can't be accessed directly (must use getter).
            In multithreaded environment, singleton property might break.
    Source: Reference: https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/
*/

public class SingletonCar {
    // Private instance; only accessible via getCar().
    private static SingletonCar car;
    public String color;

    // Private constructor.
    private SingletonCar() {
        this.color = "red";
    }

    // Customize getter that returns an instance.
    public static SingletonCar getCar() {

        // If car object doesn't exist, instantiate one..
        if (car == null) {
            car = new SingletonCar();
        }

        // otherwise, pass the same instance back.
        return car;
    }

    public static void setCar(SingletonCar car) {
        SingletonCar.car = car;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}