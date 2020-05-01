package designpatterns;

public class Main {
    public static void main(String[] args) {
        
        /* SINGLETON PATTERN
        // Instantiates normally
        Car car1 = new Car();
        Car car2 = new Car();
        car1.color = "blue"; // This modifies only one instance, we want it to modify all instances of new Car objects
        System.out.println(car1.color); // blue
        System.out.println(car2.color); // red

        // Created an instance of a car, w/o main() calling upon constructor
        // Used static method within SingletonCar to create the object for us
        SingletonCar myCar = SingletonCar.getCar();
        SingletonCar myCar2 = SingletonCar.getCar();
        SingletonCar myCar3 = SingletonCar.getCar();
        myCar.color = "green"; // Changing color of one car now changes color of every other car
        System.out.println(myCar.color); // green
        System.out.println(myCar2.color); // green
        System.out.println(myCar3.color); // green
        */


        /* FACTORY DESIGN */

    }
}

/* 
    Design Patterns:
    Language agnostic solutions to common problem scenarios.
    Allows for more reusable and maintainable code.
    Creational Design Patterns
        Meant to resolve issues w/ creating objects in a system.
        Singleton Pattern: forces there to be only one instance of a class.
        Factory Design: allows to create multiple instances and hides creation logic.
    Structural Design Patterns
        Focuses on how classes and objects can be composed.
        Identifies simplest way to realize relationships bt entities.
        Adapter: 
    Behavioral Design Patterns
        Deals with how objects interact; how they communicate w/ each other.
        To achieve simpler formats of comms.
        Chain of Responsibility: 
*/ 