/*
    Node.js
        Open-source server environment.
        JS on a server.
    NPM
        Node Package Manager.
        Platform and CLI.
    TypeScript
        Basically a superset of JavaScript (developed by Microsoft).
        Developed for larger apps.
        Syntax similar to Java/C#.
        Strongly-typed, easier to debug, also adds access modifiers and encapsulation.
        Can even see syntax errors!
    Install TypeScript from NPM using:
        npm install -g typescript
    Transpile a TS file to JS file by:
        tsc demo.ts
*/
console.log("We're in TypeScript!");
// Data Types --------------------------------------------------
/*
    TypeScript Data Types:
        number, string, boolean, null, undefined, object, function, array,
        void, enum, any, tuple, symbol.
*/
let s1 = "Hello";
// s1 = 5; // even though this gives TS errors, it'll still transpile into JS (JS doesn't care about data types).
console.log(s1);
let s2; // when type omitted, TS assigns a type of "any".
let s3; // strongly typed to number.
let s4; // can also restrict data types.
s2 = "hello";
s2 = 3; // won't yell bc it's typed as "any".
// s3 = "hi"; // yells b/c we typed it as number.
s4 = 3; // won't yell bc s4 allows numbers, booleans, or strings
s4 = "string";
s4 = true;
// Arrays --------------------------------------------------
let array1; // declares an array
array1 = ["one", "two", "three"];
let array2; // another way to declare an array
array2 = [2, 3, 4];
// Tuples; fixed size and data types are ordered in array
let tuple;
tuple = ["hola", true, 5];
// tuple = [5, true, "hola"]; // yells bc data type order MUST be consistent
// Enums; a collection of constants, representational values
// Creating enums in JS:
const OFF = 0;
const IDLE = 1;
const ACCEL = 2;
let myCar = ACCEL;
if (myCar == OFF) {
    // car off logic
}
if (myCar == IDLE) {
    // car idle logic
}
if (myCar) {
    console.log("Do truthies still exist in TS?");
}
// Creating enums in TS:
var carStates;
(function (carStates) {
    carStates[carStates["OFF"] = 0] = "OFF";
    carStates[carStates["IDLE"] = 1] = "IDLE";
    carStates[carStates["STOPLIGHT"] = 4] = "STOPLIGHT";
    carStates[carStates["ACCEL"] = 2] = "ACCEL";
    carStates[carStates["DRIVING"] = 3] = "DRIVING";
})(carStates || (carStates = {}));
;
if (myCar == carStates.ACCEL) {
    // car accel logic
}
// Functions --------------------------------------------------
function myFunc(a, b, c) {
    // this is JS way.. WE WANT STRONGLY TYPED!
}
function myFunc1(first, second) {
    return "stuff" + first + second;
    // hover over myFunc1 and notice the return type
}
function myOtherFunc() {
    // return 5; // yells bc we typed the function as void
}
// How null and undefined work in TS
let v1 = null;
let v2 = null;
let v3 = undefined;
let v4 = null;
let v5 = undefined;
let pluto = {
    "name": "Pluto",
    "hasRings": false,
    "moons": ["Hydra", "Nix"],
    "orbit": () => console.log("I'm orbiting!")
};
// Classes --------------------------------------------------
class Star {
    constructor(name, size, planets) {
        this.name = name;
        this.size = size;
        this.planets = planets;
    }
    fusion() {
        console.log(this.name + " getting brighter!");
    }
}
let sun = new Star("Sun", 1, 8); // "instantiating"
console.log(sun);
sun.fusion();
class NeutronStar extends Star {
    constructor(name, size, planets) {
        super(name, size, planets); // super must be explicit
        this.hasExploded = false;
    }
    fusion() {
        console.log("go boom..");
        this.hasExploded = true;
        super.fusion(); // calling parent's fusion
    }
}
let pulsar = new NeutronStar("Pulsar", 2.5, 3); // "instantiating"
console.log(pulsar);
pulsar.fusion();
class Animal {
    // private name: string; // can plop these into constructor itself
    // private age: number;
    // private breed: string;
    // Overloading
    // Question mark (?) in a constructor makes the param optional.
    // Every consecutive param after the first ? must ALSO be optional!
    constructor(name, age, breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
    // Getter
    get getName() {
        console.log("Getter returning name.");
        return this.name;
    }
    // Setter
    set setName(name) {
        console.log("Setter setting name.");
        this.name = name;
    }
}
let dog = new Animal("Bob", null, "Shibe"); // allowed
// let dog2: Animal = new Animal("Bob", 2); // also allowed
// let dog3: Animal = new Animal("Bob", "Shibe"); // not allowed, order must match constructor!
// console.log(dog.name + " " + dog.age + " " + dog.breed);
dog.getName; // invoking getter
dog.setName = "DOG"; // invoking setter
console.log(dog.getName);
// Using a class from another file --------------------------------------------------
function printRand(r1) {
    console.log(r1.varA + " " + r1.varB);
}
printRand({ varA: "1st", varB: 2 });
// --------------------------------------------------
