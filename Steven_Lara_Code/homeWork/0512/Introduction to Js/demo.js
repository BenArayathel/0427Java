console.log("We're doing JS!!!")

/*
Created in 1995 by Brandan Eich (in 10 day)

JS is loosely typed, intereted, object-based, scripting language(that's also dynamic),
conveived to create additionally functionality to webpages.

Benefite:
    less server interaction
    increased interactivity

    features:
        automatic semicolon
*/

// This is an inline comment

/*
Data types in JS
Number
String
Boolean
Null
Undefined
Array
Object
Function
Symbol - ES6, unique and immutable primitive values, can be used as the key for an object
*/

/*
    What is ES6?
    ECMAScript 2015 (version 6) - add more stuff to the language
    ECMA is an interal body that sets the standard for all scripting languages
*/

/*
What are the ways to declare a variable?

    var: global or local, mutable
    
ES6:
    let: global, local, or block scope, and is mutable
    const: global, local, or block scope, but is immutable
*/

/*
What are the types of scopes inside JS?
    Global - its accessible everywhere
    Local - function/object scope
    Block - within conditional statements (control flow statements)
*/

var a;
let b;
const c = "This value cannot change";

//c = "Another String"; invalid const cannot be reassigned
console.log(c);

console.log(a);

a = 5;
b = "5"; // JS is loosely typed, can switch between data types at runtime

console.log("Is a equal to be? " + (a == b));

console.log("Is a equal to b, and of the same type? " + (a === b));

// Creating an Object!
var obj = {}; // Just made an object

let planet = {
    name: "Earth",
    distance: 1,
    "humans": true
};

console.log(planet.name);
console.log(planet.distance);
console.log(planet.humans); // dot notation

console.log(planet["name"] + ": name of planet"); // bracket notation

planet.slogan = "IM RED!!!";
planet.name = "MARS"; // can change value outside of object
planet["humans"] = "none here mate";
delete planet.distance;

// dynamic aspect of JS, can add/delete new fields into objects at runtime.

console.log(planet);

let num1 = 10;
let num2 = num1;
num1 = 25;

console.log("Number 1: " + num1);
console.log("Number 2: " + num2);

// pass by value where num2 stays the same but num1 changes

// let obj1 {
//     value = 10
// };

// let num3 = obj1.value;
// obj1.value = 55;
// console.log(num3);
// console.log(obj1.value);

// Creating an Array

let arrayOfPlantes = [
    ["Mercury", 0 ,"Doesn't have rings"],
    ["Mars", 2],
    ["Jupiter", "Has 67 moons", true]
]

console.log(arrayOfPlantes[0]);
console.log(arrayOfPlantes[0][2]); // have multidimensional arrays

arrayOfPlantes[1][2] = false; // arrays are very mutable
console.log(arrayOfPlantes[1][2]);
console.log(arrayOfPlantes[1]);

arrayOfPlantes[3] = arrayOfPlantes;
console.log(arrayOfPlantes);

// Template literals, which use backticks ``, NOT "" ''

// OLD BORING WAY

let tempNum = 42;
let oldWay = "The answer " + tempNum + "\n is the answer to the universe";

// NEW COOL WAY (aspect of ES6)
let newWay = ` The answer ${tempNum}
    is
        the
            answer to the
                                    UNIVERSE`

console.log(oldWay);
console.log(newWay);

// Functions

printStuff(); // printStuff is called before it is defined

function printStuff(){
    console.log(g); // g is called before it is initialized
    console.log("stuff");
    var g = 7;
}

// hoisting is used with var
/* 
all declarations get moved to the top of their scope
var declarations get moved, let and const don't (ES6)
fucntion declarations are hoisted completely, with function body as well
*/

/* 
Truthy vs Falsy values
Everything has a true or boolean value, when used in conditional statements

    falsy values:
        null
        0 is false (but every other number is true)
        undefined
        false
        "" (empty string is false, every other string is truthy)
        NaN
*/

function checkTruthy(input) {
    console.log(`input = ${input}, and is typeof ${typeof(input)} and
    input is ${!!input}`)
}

let k;
checkTruthy(k);
checkTruthy(42);
checkTruthy(0);
checkTruthy("0");
checkTruthy("");
checkTruthy({});
checkTruthy([]);
checkTruthy(null);
checkTruthy(NaN);

/* 
What is NaN?
    Not a keyword or a data type, it is a property of the global object.
    isNan - function returns true is variable is not a type of number
    
    1) division of zero by zero
    2) division of infinity by infinity
    3) multiplication infinity by zero
    4) converting a nun-numeric string or undefined into a number
*/

// 

let foods = ["cake", "cookies", "pizza", "apples"];

let robot = {
    name: "Bob",
    ability: "jump",
    weight: 1
}

// traditional for loop
for (let i = 0; i < foods.length; i++) {
    console.log(foods[i]);
}

// for-in loop
for (let i in robot) { // "in" is used for objects with key and value pairs
    console.log("Key: " + i + " value: " + robot[i]);
}

// for-each loop: iterates through the values of an iterable, e.g. array
for (const i of foods) {
    //console.log(foods[i]);
    console.log(i);
}

//a little bit more about functions

// functional declaration

function add(x,y) {
    return x + y;
};

let addingStuff = add(2,3);
console.log(addingStuff);

// anonymous function; no name or way to call the function; it is stored inside a variable
var anon = function(a,b){ return a * b;};

console.log(anon(3,3));

var ab = anon(7,7);
console.log(ab);

// callback function
// passes a function as a parameter to another function
// guarantee execution order

move("Cypress", weather);

function move(destination, myfunc) { // 2nd parameter is allowing you pass a function as an argument
    console.log(`Moveing to ${destination}`);
    myfunc();
    return 0;
    
}

function weather() {
    console.log("It's a tad warm here!");
    
}

// contructors

function Robot(name, ability, weight) {
    this.name = name;
    this.ability = ability;
    this.weight = weight;
};

var r1 = new Robot("Steve", "Run, like really fast", 0.8);

var r2 = new Robot("Bob 2.0", move, true);

console.log(r1);
console.log(r2.ability("Italy", weather));

// self invoking functions
// functions that call themselves
// executes automatically if the expressions is followed by paranethesis
// IIFE - Immediately Invokable Function Expression

let func1 = function() {
    console.log("Inside IIFE");
}(); // paraenthesis and semicolon at the end will instantly invoke this function

// Fat arrow notation, introduced in ES6, similar to lamnda functions

let div = (x,y) => {return x/y};

//function func2(x,y) => {return x/y}; fat arrow will only work with anynomous functions

console.log(div("2", "3"));

// Closure 
// there is alot of debate whether or not JS is object-oriented or not
// using scopes to achieve encapsulation in JS

// Example
// var num = 0;

function add() {
    var num = 0;
    num += 1;
}

add();
add();
add();

num = 0;

console.log(num);

// nested functions, a function inside a function
// scopes available to inner function?
// 1) Access to its own scope
// 2) Access to the outer functions variables
// 3) Access to global variables

let funcy = (function(){
    console.log("Inside Outer Function!");
    
    let num = 0;
    return function() {
        console.log("Inside Inner Function!");
        
        return num += 1;
    }
})();

function rando(){
    console.log("doing stuff outside");
    let num = -7;
    
    return function() {
        return num += 1;
    }
}

let moreExplicit = function() { return num += 1 };

let ofuncy = rando();

let o2funcy = rando();

console.log(funcy());
console.log(funcy());
console.log(funcy());
console.log(funcy());

// Inheritance
// prototypical inheritance
/*
In JS, when it comes to inheritance, it only has one construct: objects.
Each object has a private property which holds a link to another object,
    called its prototype

The prototype has a prototype of its own and so on, until an object has reached
    a null as its property.
*/

function newStuff() {
    this.a = 1;
    this.b = "2";
};

let o = new newStuff();
//o.c = 3;

newStuff.prototype.b = 3;
newStuff.prototype.c = 5;

let p = newStuff.prototype;

o.pro

console.log(o);
console.log(p);

/*
Array object inherits from Array.prototype
p object inherits from newStuff.prototype
Object.prototype is at the top of the chain for most standard object in JS
*/

let k = Object.prototype;

let z = k.prototype;

let asray = [];


// console.log(o);
