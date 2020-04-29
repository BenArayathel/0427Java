package inheritance;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();                                 // Creates a Car object
        myCar.honk();                                          // Calls Vehicle's method
        System.out.println(myCar.make + " " + myCar.model);    // Returns Vehicle's and Car's members
    }
}

/* 
    Inheritance: one class inherits members and methods from another.
    Superclass: parent, the class being inherited from.
    Subclass: child, the class that inherits from another.
    Inherit a class via the "extends" keyword.
    final keyword used when you don't want other classes to inherit from a class.
        final class Vehicle {
            ...
        }

        class Car extends Vehicle {     // ERROR, cannot inherit from final vehicle
            ...
        }
 */