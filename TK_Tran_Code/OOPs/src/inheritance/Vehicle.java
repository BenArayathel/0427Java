package inheritance;

public class Vehicle {

    protected String make = "Subaru";           // Vehicle's member (protected, visible within package)

    public void honk() {                        // Vehicle's method
        System.out.println("Suututututu!");
    }
}