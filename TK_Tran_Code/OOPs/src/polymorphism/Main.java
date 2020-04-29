package polymorphism;

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myPig = new Pig();
        Animal myDog = new Dog();
        myAnimal.animalSound();
        myPig.animalSound();
        myDog.animalSound();
    }
}

/* 
    Polymorphism: when inherited methods can have multiple meanings (implementations).
    Why and When to Use Inheritance and Polymorphism?
        - Code reusability.
*/