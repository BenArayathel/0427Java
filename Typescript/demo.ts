import {RandomClass} from "./rando";// don't end it with the ts extension, it assumes it

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
let s3: number; //strongly typed to number
let s4: number | boolean | string;// you can restrict datatypes

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
let array1: string[];
array1 = ["one", "two", "three"];

let array2: Array<number>;
array2 = [2,3,4];

let tuple: [string, boolean, number];
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
    console.log("Truthys still exist in TS!")
}

// Creating enums inside of TS

enum carStates {OFF=0, IDLE=1, STOPLIGHT=4, ACCEL=2, DRIVING=3};

if (myVar==carStates.ACCEL) {
    // More clear on syntax and more organized
    // Car accelerating logic
}

//FUNCTION
function myFunc(a, b, c) {
    // identical to JS, but let's do strongly typed stuff
}

function myFunc1(first:number, second:string, third:boolean) {
    return "stuff "+first+" "+second;
}

function myOtherFunc(): void {
    // specifies void return type
    // Can't use the following:
    // return "stuff";
}

function betterFunc(a:number, b:string, c:boolean):object {
    return {"a": a, "b": b, "c": c}
}

let v1:null = null;
let v2:void = null;
let v3:null = undefined;
let v4:undefined = null;
let v5:undefined = undefined;
// Throws exception since void isn't a JS datatype
// let v6:void = void;


// TS has interfaces, but they're purely a TS construct, so they
// won't appear in the compiled JS file (demo.js)
interface Moon {
    name: string;
}

interface Planet {
    name: string;
    hasRings: boolean;
    moons: Array<string>;
    orbit():void;
}

let pluto:Planet = {
    "name": 'Pluto',
    'hasRings': false,
    'moons': ["Hydra"],
    'orbit': () => console.log("orbiting")//, 'random': 'rando'
    // throws an exception for random in TS b/c it's not in the
    // interface, but it will appear in JS just fine
    // also, the arrow function will turn into a regular funciton 
}

class Star implements Moon {
/**
 * Access modifiers in typescript:
 *      private, public, protected
 *      (there is no default/package, instead public acts as the 
 *      default, i.e. if there's no keyword, it's public)
 */

    public name:string;// accessible anywhere
    protected size:number;// accesible internally or any extending class
    private planets:number;//accessible only inside the class

    constructor(name:string, size:number, planets:number) {
        this.name=name;
        this.size=size;
        this.planets=planets;
    }

    fusion():void {
        console.log(this.name+" getting brighter!");
    }
}

let Sun = new Star("the Sun", 1, 8);

console.log(Sun.name);


class NeutronStar extends Star {
    // does not force you to implement all the previous variables

    public hasExploded: boolean;

    constructor (name:string, size:number, planets:number) {
        super(name, size, planets);//must put it in
        this.hasExploded = false;
    }

    fusion():void {
        super.fusion();
        console.log("and it goes boom!");
        this.hasExploded = true;
    }

    otherThing():void {
        console.log("Not accessible with just a Star reference variable.");
    }
}

// Upcasting
let pulsar:Star = new NeutronStar("pulsar", 2.5, 3);

pulsar.fusion();
console.log(pulsar.name)

// Downcasting
let actualPulsar:NeutronStar = pulsar as NeutronStar;

interface A{}
interface B{}

class Animal implements A, B {
    // private name:string;
    // private age:number;
    // private breed:string;

    /** 
     * ? denotes optional parameter!
     * each parameter to the right of the question mark must also 
     *      be optional!
     * Constructor overloading doesn't exist, so we need to use the optional parameters
     */
    constructor(private name?:string, private age?:number, private breed?:string) {}

    // method overloading exists!

    // get keyword denotes a getter
    get Name():string {
        console.log("in name getter");
        return this.name;
    }
    // set keyword denotes a setter
    set Name(name:string) {
        console.log("in name setter");
        this.name = name;
    }
}

let dog1:Animal = new Animal("Bob");
let dog2:Animal = new Animal("Bill", 3);
let dog3:Animal = new Animal("Billy", 4, "Labrador");
let dog4:Animal = new Animal("Billybob", null, "GermanShepard")


// Will throw an error because these are private
// console.log(dog4.age+" "+dog4.name+" "+dog4.breed);
console.log(dog1.Name);
dog1.Name = "Dan";
console.log(dog1.Name);

function printRandom(r1:RandomClass) {
    console.log(r1.varA + " "+ r1.varB);
}

printRandom({varA:"1st", varB:2});