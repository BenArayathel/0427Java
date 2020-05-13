package com.banking.models;

public class Employee extends User {
    String id;

    public Employee() {
    }

    public Employee(String username, String password, String id) {
        super(username, password);
        this.id = id;
    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String username, String id) {
        super(username);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
