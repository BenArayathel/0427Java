package serialization;

import java.io.Serializable;

/*
    For an object to be serializable, it must implement the Serializable interface.
    Serializable interface is a "marker" interface; one that has no members or methods.
*/

public class Car implements Serializable{

    /** <-
     *  Java Documentation Annotation
     */
    private static final long serialVersionUID = 5332604481759270030L;

    // Private Members
    private transient int year; // transient tells JVM that you don't want to save the value in the file.
    private String model;
    private String make;
    private String color;

    // Constructor
    public Car(int year, String model, String make, String color) {
        this.year = year;
        this.model = model;
        this.make = make;
        this.color = color;
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Overrides Java's toString()
    @Override
    public String toString() {
        return "Car [color=" + color + ", make=" + make + ", model=" + model + ", year=" + year + "]";
    }
}