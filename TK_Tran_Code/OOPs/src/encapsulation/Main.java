package encapsulation;

public class Main {
    public static void main(String[] args) {
        Person x = new Person();
        // x.name = "John";                // ERROR, cannot directly update Person's private var
        // System.out.println(x.name);     // ERROR, cannot directly return Person's private var
        x.setName("TK");                   // Updates private var value via call to setter method
        System.out.println(x.getName());   // Returns private var value via call to getter method
    }
}

/* 
    Encapsulation: hiding "sensitive" data from users.
        - Declare class variables as private.
        - Provide public getters and setters to access and update private values.
    Why Encapsulate?
        - Better control of class attributes and methods.
        - Class attributes are made read-only or write-only.
        - Flexible: dev can change one part of code w/o affecting other parts.
        - Increased security of data.
*/