package encapsulation;

public class Person {

    private String name;                // private = restricted access to this class

    public String getName() {           // Getter method RETURNS value from private var
        return name;
    }

    public void setName(String name) {  // Setter method UPDATES value of private var
        this.name = name;               // "this" keywordd refers to current object
    }
}