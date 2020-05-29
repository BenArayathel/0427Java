console.log("Reviewing JavaScript!")

// OBJECTS ------------------------------------------------------------
let planet = {
    name: "Earth",
    distance: 1,
    "humans": true
}

// Accessing an Object
console.log(planet.name); // dot notation
console.log(planet.humans);
console.log(planet.distance);

console.log(planet["name"]); // bracket notation
console.log(planet["humans"]);
console.log(planet["distance"]);

// Adding an attribute outside the object declaration
planet.slogan = "I'm red.";
console.log(planet);

// Overriding an attribute
planet.name = "Mars"; // dot notation
planet["humans"] = "none here mate"; // bracket notation
console.log(planet);

// Pass by Value
let num1 = 0;
let num2 = num1;
num1 = 25;
console.log("Number 1: " + num1);
console.log("Number 2: " + num2);

let obj1 = {
    value: 10
}
let num3 = obj1.value;
obj1.value = 55;
console.log(num3);
console.log(obj1.value);

// ARRAYS (multidimensional) ------------------------------------------------------------
let arrayOfPlanets = [
    ["Mercury", 0, "No rings"],
    ["Mars", 2],
    ["Jupiter", 67, true]
]

console.log(arrayOfPlanets[0]);
console.log(arrayOfPlanets[0][2]); // selecting multidimensional elements
arrayOfPlanets[1][2] = false;
console.log(arrayOfPlanets[1][2]);
console.log(arrayOfPlanets[1]);

arrayOfPlanets[3] = arrayOfPlanets;
console.log(arrayOfPlanets);

// ES6 Feature - TEMPLATE LITERALS; uses backticks (`)
    // Old way
    let tempNum = 42;
    let oldWay = "The answer " + tempNum + "\n is the answer to the universe.";
    console.log(oldWay);

    // New way
    let newWay = ` The answer ${tempNum}
        is the answer to
                        the universe`;
    console.log(newWay);

// FUNCTIONS ------------------------------------------------------------
/* Hoisting
        All declarations get moved to the top of their scope.
        var declarations get moved, let and const don't (ES6).
        Function declarations are hoisted completely, with function body as well.
*/
printStuff(); // can invoke BEFORE it's definition; thanks to "hoisting"

function printStuff() {
    var g;
    console.log(g);
    console.log("stuff");
    var g = 7;
}

/* Truthy vs. Falsey Values
    Everything has boolean value when used in conditional statements.

    Falsey values:
        0 is false (but every other number is true)
        null
        undefined
        false
        "" (empty string is false, every other string is truthy)
        NaN
*/

let k;
checkTruthy(k);
checkTruthy(42);
checkTruthy(0);
checkTruthy("0");
checkTruthy("");
checkTruthy({}); // passing in empty obj
checkTruthy([]); // passing in empty arr
checkTruthy(null);
checkTruthy(NaN);

function checkTruthy(input) {
    console.log(`input = ${input}, is typeof ${typeof(input)} and input is ${!!input}`); // ! "not", !! true
}

// NaN ------------------------------------------------------------
/*
    What is NaN?
        Not a keyword or a data type, it's a property of the global object.
        isNaN() - func returns true if var is not a type of number.

    1. Division of zero by zero
    2. Division of infinity by infinity
    3. Multiplication of infinity by zero
    4. Converting a non-numeric string or undefined into a number
*/

// Loops ------------------------------------------------------------

let foods = ["Cake", "Pizza", "Apples"];

let robot = {
    name: "Bob",
    ability: "Jump",
    weight: 1
}

// for loop
for (let i = 0; i < foods.length; i++) {
    console.log(foods[i]);
}

// for-in loop - iterates through keys inside an object
for (let i in robot) {
    console.log("Key: " + i + ", value: " + robot[i]);
}

// for-each loop - iterates through values of an iterable , e.g. an array
for (let i of foods) {
    console.log(i);
}

// MORE ABOUT FUNCTIONS ------------------------------------------------------------

// Normal Functions
function add(x, y) {
    return x + y;
}
let addingStuff = add(2, 3);
console.log(addingStuff)

// Anonymous Functions; one without a name and stored in a variable
var anon = function(a, b) {
    return a * b;
    // stored inside a variable
    // no way of calling this func directly
    // to call, use var itself
}
console.log(anon(3, 3));

var ab = anon(7, 7);
console.log(ab);

// Callback Functions; passes a func as a param to another func
    // Guarantees execution order
move("Cyprus", theWeather);

function move(destination, myfunc) {
    console.log(`Moving to ${destination}`);
    myfunc();
}

function theWeather() {
    console.log("It's a tad warm here.");

}

// Constructors; just funcs lol
function Robot(name, ability, weight) {
    this.name = name;
    this.ability = ability;
    this.weight = weight;
}

var r1 = new Robot("Steve", "run, like really fast", 0.8); // creates a new Robot obj
console.log(r1);

var r2 = new Robot("Bob 2.0", move, true)
console.log(r2.ability("Italy", theWeather));

// Self-Invoking Functions; those that call themselves, executes automatically if followed by parentheses
// IIFE - Immediately Invokable Function Expression
let func1 = function() {
    console.log("Inside IIFE");

}(); // note () here

// Fat Arrow Notation, intro'd in ES6
let div = (x, y) => {return x / y};
console.log(div(2, 3));

var sayHello = name => {
    console.log(name + " says hello");
};

// CLOSURE ------------------------------------------------------------
/*
    JS isn't a full "object-oriented" language.
    Uses scopes to achieve "encapsulation" (no explicit access modifiers).
*/
var num = 0;

function add() {
    num += 1;
}

add();
add();
add();
console.log(num); // 3

num = 0; // issue: nothing stopping me from overriding num!!!
console.log(num); // 0, oh noooes!!!

// Nested Functions to the rescue; a func inside a func.
/*
    Scopes available to inner func?
        1. Access to its own scope
        2. Access to the outer func's variables
        3. Access to global variables
*/
let funcy = (function() {
    console.log("Inside outer func.");
    let num = 0; // num declared inside a func; protected, cannot be modified from outside
    return function() { // can be modified from within func
        console.log("Inside inner func.");
        return num += 1; // inner func keeps track of outer func's variable
    }
})() // self-invoking func

console.log(funcy); // invoking outer function
console.log(funcy()); // invoking inner function
console.log(funcy());
console.log(funcy());

// INHERITANCE ------------------------------------------------------------
/*
    "Prototypical Inheritance"
        In JS, when it comes to inheritance, it only has one construct: objects.
        Each obj has a private property which holds a link to another obj, called its prototype.
        The prototype has a prototype of its own, and so on, until an obj has reached a null as its property.
    
    Array obj inherits from Array.prototype
*/
function newStuff() {
    this.a = 1;
    this.b = "2";
};

let o = new newStuff();
console.log(o);

newStuff.prototype.b = 3;
newStuff.prototype.c = 5;
let p = newStuff.prototype;

console.log(p);
