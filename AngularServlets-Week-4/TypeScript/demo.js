/**
 * What is node.js?
 *      Node.js is an open server environment
 *      JS on a server
 *      Node is free and runs on various platforms
 *
 * What is NPM?
 *      Node package manager
 *      NPM is a platform and command line interface
 *
 * What it TypeScript?
 *      Typescript is a superset of Javascript (developed by Microsoft)
 *
 *      Typescript was developed for larger apps. The syntax is closer to heigner
 *      level languages like java or c#.
 *
 *      TS is strongly typed, easier to debug, also adds acces modifiers and encapsulation.
 *      You can even see syntax errors
 *
 * We can instally typescrip from NPM using:
 *      npm install -g typescript
 */
console.log("We're in typescript!!");
/**
 * What re the datatypes in typescript?
 * number, string, boolean, null, undefined, object, fcuntion, array,
 * void, enum, any, tuple, symbol
 */
let s1 = "hello";
//   s1 = 5;
console.log(s1);
//NOTE: even though typescript gives an error, it will still transpile into JS
let s2; //any type
let s3; //strongly typed to number
let s4; //you can restrict datatypes
s2 = "hello";
s2 = 3;
//   s3 = "hohohohoh"; 
s4 = 3;
s4 = "a string";
s4 = true;
//   s4 = [];
let v1 = null;
let v2 = null;
let v3 = undefined;
let v4 = null;
let v5 = undefined;
// let v6: void = void;
//Arrays
let array1;
array1 = ["one", "two", "three"];
let array2;
array2 = [2, 3, 4];
//Tuples (fixed size and datatypes are ordered in array)
let tuple;
tuple = ["hola", true, 5];
// tuple = [5, true, "hola"];
//Enum 
//An enumeration is a collection of constants, Representational values.
//Creating inside of JS
const OFF = 0;
const IDLE = 1;
const ACCEL = 2;
let myVar = ACCEL;
if (myVar == OFF) {
    //car off state logic
}
if (myVar == IDLE) {
    //car idle logic
}
if (myVar) {
    console.log("Do truthys still exist in TS?");
}
//Creating enums inside of TS
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
    //car accelerating logic
}
//FUNCTION
function myFunc(a, b, c) {
    //basically JS
}
function myFunc1(first, second) {
    return "stuff" + first + second;
}
function myOtherFunc() {
    // return 5;
}
let pluto = { 'name': 'Pluto', 'hasRings': false, 'moons': ['Hydra', 'Nix'],
    'orbit': () => console.log("I'm orbiting!!!") };
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
Sun.fusion();
class NeutronStar extends Star {
    constructor(name, size, planets) {
        super(name, size, planets);
        this.hasExploded = false;
        // this.hasExploded = hasExploded;
    }
    fusion() {
        console.log("go boom");
        this.hasExploded = true;
        super.fusion();
    }
}
let pulsar = new NeutronStar("pulsar", 2.5, 3);
pulsar.fusion();
class Animal {
    // private name: string;
    // private age: number;
    // private breed: string;
    /**
     * question marks in the constructor makes the aprameter optional
     * each parameter to the right of hte question mark must also be optional
     */
    constructor(name, age, breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
    get Name() {
        console.log("in getter");
        return this.name;
    }
    set Name(name) {
        console.log("in setter");
        this.name = name;
    }
}
let dog = new Animal("Bob", null, "German Shepard");
// console.log(dog.age + " " + dog.name + " " + dog.breed);
// dog.Name("Alfonso");
dog.Name;
dog.Name = "Alfonso";
function printRand(r1) {
    console.log(r1.varA + " " + r1.varB);
}
printRand({ varA: "1st", varB: 2 });
