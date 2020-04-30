package serialization;

import java.io.Serializable;

/*
    For an object to be serializable, it must implement the Serializable interface.
    Serializable interface is a "marker" interface.
*/

public class Parrot implements Serializable{
    
    /** Java Documentation Annotation
     *
     */
    private static final long serialVersionUID = -2161012551141600604L;

    // Private Members
    // private transient name; // transient keyword ensures name will not be persisted (saved)
    private String name;
    private String color;

    // No Args Constructor
    public Parrot() {
        // super() is implied here
    }

    // Parameterized Constructor
    public Parrot(String name, String color) {
        // super() is implied here
        this.name = name;
        this.color = color;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Overriding Java's toString()
    @Override
    public String toString() {
        return "Parrot [color=" + color + ", name=" + name + "]";
    }

    // Custom Methods
    public void fly(){
        System.out.println("Flying");
    }

    public void speak(){
        System.out.println("Speaking");
    }
}