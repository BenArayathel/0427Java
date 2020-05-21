/**
 * What is node.js?
 *      Node.js is an opensource server environment
 *      Allows js to run on the backend
 *      JS is on a server
 *      Node is free and runs on various platforms
 *
 * What is NPM?
 *      Node Package Manager
 *      NPM is a platform and command line interface
 *
 * What is typescript?
 *      Typescript is a superset of Javascript (developed by Microscoft)
 *      Typescript was developed for making larger apps.
 *      It's syntax is closer to Java or C#, allowing better debugging and error detection
 *      TS is strongly typed, easier to debug, and has access modifiers and encapsulation
 *          and will display syntax errors
 * We can install typescript from NPM using
 *      npm install -g typescript
 *      If it's installed it will update it :)
*/
console.log("We're in typescript!!");
/**
 * What are the datatypes in typescript
 *      number string boolean null undefined object function array
 *      void enum any tuple symbol
 */
let s1 = "hello";
// This will throw an error in typescript, but it will still compile
// in JS
//  s1 = 5;
// NOTE even though typescript gives an error, it will still transpile into JS
let s2;
let s3; //strongly typed to number
let s4; // you can restrict datatypes
s2 = "hello";
s2 = 3;
//s2 can be anything
s3 = 4;
// Exception
// s3 = "four";
s4 = 5;
s4 = false;
s4 = "five";
// Exception
// s4 = {"yo": "dog"};
// Arrays
let array1;
array1 = ["one", "two", "three"];
let array2;
array2 = [2, 3, 4];
let tuple;
tuple = ["hola", true, 5];
// Throws an exception
// tuple = [true, 5, "hola"];
// Enum
// An enumeration is a collection of constants
//      i.e. representational values
const OFF = 0;
const IDLE = 1;
const ACCEL = 2;
let myVar = ACCEL;
if (myVar == OFF) {
    // do some car off logic;
}
if (myVar == IDLE) {
    // idle logic
}
if (myVar) {
    console.log("Truthys still exist in TS!");
}
// Creating enums inside of TS
var carStates;
(function (carStates) {
    carStates[carStates["OFF"] = 0] = "OFF";
    carStates[carStates["IDLE"] = 1] = "IDLE";
    carStates[carStates["STOPLIGHT"] = 4] = "STOPLIGHT";
    carStates[carStates["ACCEL"] = 2] = "ACCEL";
    carStates[carStates["DRIVING"] = 3] = "DRIVING";
})(carStates || (carStates = {}));
;
if (myVar == carStates.ACCEL) {
    // More clear on syntax and more organized
    // Car accelerating logic
}
//FUNCTION
function myFunc(a, b, c) {
    // identical to JS, but let's do strongly typed stuff
}
function myFunc1(first, second, third) {
    return "stuff " + first + " " + second;
}
function myOtherFunc() {
    // specifies void return type
    // Can't use the following:
    // return "stuff";
}
function betterFunc(a, b, c) {
    return { "a": a, "b": b, "c": c };
}
let v1 = null;
let v2 = null;
let v3 = undefined;
let v4 = null;
let v5 = undefined;
let pluto = {
    "name": 'Pluto',
    'hasRings': false,
    'moons': ["Hydra"],
    'orbit': () => console.log("orbiting") //, 'random': 'rando'
    // throws an exception for random in TS b/c it's not in the
    // interface, but it will appear in JS just fine
    // also, the arrow function will turn into a regular funciton 
};
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
let Sun = new Star("the Sun", 1, 8);
console.log(Sun.name);
class NeutronStar extends Star {
    constructor(name, size, planets) {
        super(name, size, planets); //must put it in
        this.hasExploded = false;
    }
    fusion() {
        super.fusion();
        console.log("and it goes boom!");
        this.hasExploded = true;
    }
    otherThing() {
        console.log("Not accessible with just a Star reference variable.");
    }
}
// Upcasting
let pulsar = new NeutronStar("pulsar", 2.5, 3);
pulsar.fusion();
console.log(pulsar.name);
// Downcasting
let actualPulsar = pulsar;
class Animal {
    // private name:string;
    // private age:number;
    // private breed:string;
    /**
     * ? denotes optional parameter!
     * each parameter to the right of the question mark must also
     *      be optional!
     * Constructor overloading doesn't exist, so we need to use the optional parameters
     */
    constructor(name, age, breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
    // method overloading exists!
    // get keyword denotes a getter
    get Name() {
        console.log("in name getter");
        return this.name;
    }
    // set keyword denotes a setter
    set Name(name) {
        console.log("in name setter");
        this.name = name;
    }
}
let dog1 = new Animal("Bob");
let dog2 = new Animal("Bill", 3);
let dog3 = new Animal("Billy", 4, "Labrador");
let dog4 = new Animal("Billybob", null, "GermanShepard");
// Will throw an error because these are private
// console.log(dog4.age+" "+dog4.name+" "+dog4.breed);
console.log(dog1.Name);
dog1.Name = "Dan";
console.log(dog1.Name);
function printRandom(r1) {
    console.log(r1.varA + " " + r1.varB);
}
printRandom({ varA: "1st", varB: 2 });
