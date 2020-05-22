import {RandomClass} from "./rando"

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
  let s3: number; //strongly typed to number
  let s4: number | boolean | string; //you can restrict datatypes

  s2 = "hello";
  s2 = 3;

//   s3 = "hohohohoh"; 

  s4 = 3;
  s4 = "a string";
  s4 = true;
//   s4 = [];

let v1: null = null;
let v2: void =  null;
let v3: null = undefined;
let v4: undefined = null; 
let v5: undefined = undefined;
// let v6: void = void;


//Arrays
let array1: string[];
array1 = ["one","two","three"];

let array2: Array<number>;
array2 = [2,3,4];

//Tuples (fixed size and datatypes are ordered in array)
let tuple: [string, boolean, number];
tuple = ["hola", true, 5];
// tuple = [5, true, "hola"];

//Enum 
//An enumeration is a collection of constants, Representational values.

//Creating inside of JS

const OFF =  0;
const IDLE = 1;
const ACCEL = 2;

let myVar = ACCEL;

if(myVar == OFF){
    //car off state logic
}
if(myVar == IDLE){
    //car idle logic
}

if(myVar){
    console.log("Do truthys still exist in TS?");
}

//Creating enums inside of TS

enum carStates {OFF = 0, IDLE = 1, STOPLIGHT = 4, ACCEL = 2, DRIVING = 3};

if(myVar == carStates.ACCEL){
    //car accelerating logic
}

//FUNCTION

function myFunc(a , b, c){
    //basically JS
}

function myFunc1(first:number, second: string){
    return "stuff" + first + second;
}

function myOtherFunc(): void {
    // return 5;
}

interface Moon{
    name: string;
}

interface Planet{
    name: string;
    hasRings: boolean;
    moons: Array<string>;
    orbit(): void;
}

let pluto: Planet = {'name':'Pluto', 'hasRings':false, 'moons': ['Hydra','Nix'],
            'orbit': () => console.log("I'm orbiting!!!")}

class Star implements Moon {

    /**
     * Accessmodifiers in typescript:
     * private, public, protected. There is NO DEFAULT
     * public acts as the "default" modifier if you odn't use a keyword
     */
    public name: string; //accessible anywhere
    protected size: number; //accessible internally or any class htat extends it
    private planets: number; //accessible only inside your function

    constructor(name: string, size: number, planets: number){
        this.name = name;
        this.size = size;
        this.planets = planets;
    }

    fusion(): void{
        console.log(this.name + " getting brighter!");
    }
}

let Sun: Star = new Star("the Sun",1,8,);
Sun.fusion();

class NeutronStar extends Star{

    //does not force you to immplement all the previous variables
    public hasExploded: boolean;

    constructor(name: string, size: number, planets: number){
        super(name,size,planets);
        this.hasExploded = false;
        // this.hasExploded = hasExploded;
    }

    fusion(): void{
        console.log("go boom");
        this.hasExploded = true;
        super.fusion();
    }

}

let pulsar: Star = new NeutronStar("pulsar",2.5,3);

pulsar.fusion();
// pulsar.hasExploded; pulsar does not have access because it's a parent class


interface A{}
interface B{}

class Animal implements A, B{

    // private name: string;
    // private age: number;
    // private breed: string;

    /**
     * question marks in the constructor makes the aprameter optional 
     * each parameter to the right of hte question mark must also be optional
     */

    constructor(private name?: string, private age?: number,private breed?: string){

    }

    get Name(): string{
        console.log("in getter");
        return this.name;
    }

    set Name(name: string) {
        console.log("in setter");
        this.name = name;
    }
}

let dog: Animal = new Animal("Bob",null,"German Shepard");
// console.log(dog.age + " " + dog.name + " " + dog.breed);
// dog.Name("Alfonso");
dog.Name;
dog.Name = "Alfonso";


function printRand(r1: RandomClass){
    console.log(r1.varA + " " + r1.varB);
}

printRand({varA: "1st", varB: 2});