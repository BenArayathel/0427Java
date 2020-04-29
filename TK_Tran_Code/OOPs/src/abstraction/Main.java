package abstraction;

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