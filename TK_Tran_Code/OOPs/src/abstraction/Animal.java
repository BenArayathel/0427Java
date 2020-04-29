package abstraction;

abstract class Animal {                     // Abstract class
    public abstract void animalSound();     // Abstract method (no implementation, yet)
    public void sleep() {                   // Regular methods
        System.out.println("Zzz");
    }
}