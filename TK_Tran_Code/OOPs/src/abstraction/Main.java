package abstraction;

abstract class Animal {                     // Abstract class
    public abstract void animalSound();     // Abstract method (no implementation, yet)
    public void sleep() {                   // Regular methods
        System.out.println("Zzz");
    }
}

class Cow extends Animal {                  // Subclass (inherits from Animal via inheritance)
    public void animalSound() {             // animalSound() implementation provided here
        System.out.println("Moo");
    }
}

public class Main {
    public static void main(String[] args) {
        Cow cow = new Cow();                // Creates a Cow object
        cow.animalSound();
        cow.sleep();
    }
}

/* 
    Abstraction: process of hiding details and showing only essential information.
        - Can be achieved via abstract classes or interfaces.
        - "abstract" keyword used for classes and methods.
    Abstract Class: restricted class that cannot create objects.
        - To access, it must be inherited from another class.
        - Can contain both abstract and regular methods.
    Abstract Method: created inside an abstract class but w/o an implementation.
        - Implementation is provided by "concrete" subclass.
    Why and When to use Abstract Classes and Methods?
        - Ensures security, hide certain details and show only important details of an object.
 */