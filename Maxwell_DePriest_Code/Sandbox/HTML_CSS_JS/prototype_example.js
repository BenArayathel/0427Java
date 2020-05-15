class Person {
    constructor(firstName, lastName, age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.getFullName = function () {
            return this.firstName + " is my first name. " + this.lastName + " is my last name";
        }
    }
}

let person = new Person("Tom", "Hiddleston", 38);
let person2 = new Person("Jason", "Bourne", 42);

console.log(person.getFullName());
console.log(person2.getFullName());

Person.prototype.run = function () {
    console.log("I'm running super fast");
}

console.log(person.getFullName());
console.log(person2.run());