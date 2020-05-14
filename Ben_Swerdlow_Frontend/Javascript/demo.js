/*
console.log is usally used to do comments+ in HTML (it won't show up on the page, but it will show up in source).
*/
console.log("We're doing js");

/*
Created in 1995 by Brandan Eich (in 10 days)

JS is meant for client-side focus, but is availabel on server side as well

JS is a loosely-typed (can switch datatypes at runtime), interpreted, object-based, dynamic, scripting language. It was conceived initialy to create additional functionality
to web pages.

Benefits:
* Less server interaction necessary (don't need to send code back and forth from java on server to html client-side as much)
* Increased interactivity
* ASI: Automatic Semi-colon Insertion (best practice to use them, though; can be bad if you don't)

*/

// This is an inline comment

/*
JS Data Types
Number
String
Boolean
Null
Undefined
Array
Object
Function
Symbol (ES6 - unique and immutable primitive values, can be used as the key for an object)
*/

/*
What is ES6?
ECMAScript2015 (v6): A relatively big update in JS
ECMA is an international body that sets the standard for all scripting langauges
*/

/*
Ways to declare a variable

Old way
var: can be global or local (and it's mutable)

ES6 Way
let: global, local, or block (and it's mutable)
const: global, local, or block (and it's immutable)
*/

/*
What are the types of scopes inside JS
Global - accessible everywhere
    Inside js, you have 
Local - function/object scope
Block - only within conditional or control flow (i.e. loop) statements (in ES6) 
*/

//-------------------------------------------------------------------------------------------------


var a;
let b;
const c="This value cannot change, so we have to initialize it";
//console.log(c);
//c = "another string" // invalid! const is trying to be assigned
//console.log(a);// undefined (null is an actual value in JS)
//console.log(b);// undefined

a=5;
b="5";
//console.log(a);
//console.log(b);

//console.log("Is a equal to b? " + (a==b));
//console.log("Is a equal to b AND of the same type? "+ (a===b));

//console.log("But things are loosely typed, so let's change b to exactly match a.");

b = 5;
//console.log("Is a equal to b? " + (a==b));
//console.log("Is a equal to b AND of the same type? "+ (a===b));


//-------------------------------------------------------------------------------------------------

// Creating an object!
var obj = {}; // Just made an object!
let planet = {
    // DON'T EVEN HAVE TO PUT KEYS IN STRINGS!!!!
    name: "Earth",
    distance: 1,//AU
    "humans": true,
};

//console.log(planet.name);
//console.log(planet.distance);
//console.log(planet.humans);//dot-notation
//console.log(planet["name"]+" is the name of the planet");//bracket notation

planet.slogan = "I'M RED!";//Can add a value outside of object
planet.name = "Mars";//Can change value outside of object
delete planet.distance;//Remove a value outside of object

// dynamic aspect of JS, can add/delete new fields into objects at runtime

//console.log(planet);

//-------------------------------------------------------------------------------------------------

let num1 = 10;
// PASS BY VALUE!!
let num2 = num1;
num1 = 25;

//console.log("Number 1: "+num1);
//console.log("Number 2: "+num2);
// Use commas in place of + to ensure printed as strings
//console.log(num1, num2);

let obj1 = {
    value: 10,
};
obj1.value = 15;
let num3 = obj1.value;

//console.log(num3);

//-------------------------------------------------------------------------------------------------

// Creating an Array (multidimensional!!!)

// let arrayOfPlanets = [
//     ["Mercury", 0, "Doesn't have rings"],
//     // Arrays (and arrays of arrays) don't have to be consistent!
//     ["Mars", 2],
//     ["Jupiter", 67, true]
// ];

// console.log(arrayOfPlanets[0]);
// console.log(arrayOfPlanets[1][0]); // have multidimensional arrays

// // This is undefined
// console.log(arrayOfPlanets[1][2]);
// // But let's change that
// arrayOfPlanets[1][2] = false;
// console.log(arrayOfPlanets[1]);

// // INFINITE LOOPING!!!!!!
// arrayOfPlanets[3] = arrayOfPlanets;
// console.log(arrayOfPlanets);

//-------------------------------------------------------------------------------------------------

// Template literals, which use backticks ``, NOT "" OR ''

let tempNum = 42;
let oldWay = "The answer " + tempNum + "\n is the answer to the universe";
// console.log(oldway);

let newWay = ` The answer ${tempNum}
    is 
        the 
            answer to the universe`;
console.log(newWay);

//-------------------------------------------------------------------------------------------------

// Functions

// printStuff();

function printStuff() {
    // hoisted phantom var g;
    console.log(g);
    console.log("stuff");
    // let g = 7;// won't allow hoisting (it will happen, but it will throw an exception)
    var g = 7;// will allow hoisting
}

// hoisting
/*
all declarations get moved to the top of their scope
var declarations get moved, let and const will throw an exeption when they are
function declarations are hoisted completely with thier function bodies
*/

//-------------------------------------------------------------------------------------------------

/*
Truthy vs. Falsey values

Everything has a true/false or boolean value when used in conditional statements

(More truthy than falsey values, basically assume anything not in the list below is truthy)

falsey values
    false
    0 is false (but every other number is true)
    null
    undefined
    "" (empty string is false, every other string is truthy)
    NaN (Not a Number)
*/

function checkTruthy(input){
    console.log(`Input = ${input}, and is typeof ${typeof(input)} and 
    input is ${!!input}`)//!! double negative just shows you what the boolean value is
}

let k;
// checkTruthy(k);
// checkTruthy(42);
// checkTruthy(0);
// checkTruthy("0");
// checkTruthy("");
// checkTruthy({});
// checkTruthy([]);
// checkTruthy(null);
// checkTruthy(NaN);

//-------------------------------------------------------------------------------------------------

/*
What is NaN?
    Not a keyword or a data type
    It is a property of the global object.
        global object is the superobject of the entire script

isNan() - function returns ture if variable is not a number

When is NaN used?
    1) Division of zero by zero
    2) Divison of infinity by infinity
    3) Multiplication infinity by zero
    4) Converting a non-numeric string or undefined to a number
*/

//-------------------------------------------------------------------------------------------------

let foods = ["cake", "cookies", "pizza", "apples"]

let robot = {
    name: "Bob",
    ability: "jump",
    weight: 1
}

// // tranditional for loop
// for (let i=0; i<foods.length; i++){
//     console.log(foods[i]);
// }

// // for-in loop (you could use const instead of let because of funky JS stuff, but please don't!)
// for (let i in robot) {//let i iterate through each key in robot
//     // in will always iterate through the keys of an object
//     console.log("Key: " +i+", Value: "+robot[i]);
// }

// // for-each loop (you could use const instead of let because of funky JS stuff, but please don't!)
// for (let i in foods){//let i iterate through each index of foods
//     // in will always iterate through the keys of an object, which in this case is the indices of the array
//     console.log(foods[i]);
// }
// // for-each loop with of
// for (let j of foods){//let j iterate through each entry in the array
//     // of syntax only allowed on iterable objects (not default key-value objects)
//     console.log(j);
// }

//-------------------------------------------------------------------------------------------------

// Functions P.2

function add(x,y){
    return x+y;
}

// let addingstuff = add(2,3);
// console.log(addingstuff);//this is a number

// Anonymous function! (like lambda funtion)

var anon = function(a,b){ return a*b; };//function stored inside a variable

// console.log(anon(3,3));//this is equivalent to: console.log(3*3);
// console.log(anon);
// console.log(anon(7,8));

/*
callback function
passes a function as a parameter to another function
this guarantees execution order
*/

function move(destination, myfunc) {
    console.log(`Moving to ${destination}`);
    myfunc();
    // need return 0 for when we pass it to the robot constructor (it expects a return value, if none specifed it'll return undefined)
    return 0;
}

function theWeather(){
    console.log("It's a tad warm here");
}

move("Cyprus", theWeather);

// Constructors

function Robot(name, ability, weight){
    this.name = name;
    this.ability = ability;
    this.weight = weight;
}

var r1 = new Robot("Steve", "run, like really fast", 0.8);
// console.log(r1);

var r2 = new Robot("Bob 2.0", move, 0.8);

// console.log(r2);
// console.log(r2.ability("Italy", theWeather));

/*
Self-invoking functions
functions that call themselves
executes automatically if the expression is followed by parentheses
IIFE - Immediately Invokable Function Expression
*/

let func1 = function(){
    console.log("Inside IIFE");
}();//adding the parentheses at the end will automatically call the function

// Fat Arrow Notation (introduced in ES6)

let div = (x,y) => { return x/y; };

let ap = function apples() {return 0}

var sayHello = name => {
    console.log(name + " says hello");
}

console.log(div(-1,0));
sayHello("She");

//-------------------------------------------------------------------------------------------------

// JS is object based, not object oriented (people debate this all the time, apparently)

/* 
Closure
    Using scopes so you can get encapsulation
*/
//Example
var num = 0;

function add(){
    // var num = 0;//could do this to encapsulate, but then it'd be reset every time)
    num+=1;
};

add();
add();
add();
// Not encapsulated :(
num=0;

// console.log(num);

/*
Nested functions, a function inside a function
Scopes availabel to inner function?
    1) Access to its own scope
    2) Access to the outer functions' variables
    3) Access to global variables.
*/

let funcy = (function(){
    let num = 0;
    return function(addition){
        if (addition=="get"){
            return num;
        }
        return num+=addition;
    }
})();
// when self-invoked, funcy returns the inner function while also tracking the value of num the entire time since it's scope is a level above the inner function
// BUT, it's also out of scope to things outside of the function, so you can't change funcy

console.log(funcy(1));
console.log(funcy(3));
console.log(funcy(4));
console.log(funcy(0));
console.log(funcy("get"));
console.log(funcy("thingy"));

let g = function rando(){
    console.log("doing stuff outside");
    let num = -7;

    return function(){
        return num += 1;
    };
}();

console.log(g);
console.log(g());


// an array of multiple functions

let superFunky = function funky(){
    console.log("doing stuff outside");
    let num = -7;

    return [function(){return num+=1}, function(){return num-=1}];
}();

console.log(superFunky[0]())
console.log(superFunky[0]())
console.log(superFunky[1]())
console.log(superFunky[1]())

//-------------------------------------------------------------------------------------------------
/*
INHERITANCE WITHOUT CLASSES?!?

Prototypical inheritance
In JS, when it comes to inheritance, it only  has one construct: objects.
Each object has a private property which holds a link to another object, called its prototype.

The prototype has a protoype of its own and so on, until an obje ct has reached a null as its property
*/
// There is a better syntax for this introduced in ES6, but you still need to know this
//----The new syntax-----
// class newStuff {
//     constructor() {
//         this.a = 1;
//         this.b = "2";
//     }
// };
//----The original syntax-----
function newStuff(){
    this.a = 1;
    this.b = "2";
};
// Using this in a function makes it a "class"

let o = new newStuff();
// We can do this right now, but
o.c=3;
// we cannot do this with a constructor

// Prototype like this is like super
newStuff.prototype.b = 3;
newStuff.prototype.c = 5;

let p = newStuff.prototype;

/*
Array object inherits from prototype
p object inhertis from newStuff.prototype
Object.prototype is at the top of the chain (for most standard objects in JS)
*/

// let k = Object.prototype;

// let z= k.prototype;

let asArray = [];


console.log(o);
console.log(p);

// Assignment: Make a prototypical chain

function baseClass(){
    this.a = 1;
    // in this function/class there is a hidden property called prototype (this.prototype)
    // prototype points to the Prototype object, which points back to this constructor.
}

// When this constructor is called, the instance is created with the constructor above
// Also, the __proto__ property is created
let base = new baseClass();
// This __proto__ property points to the prototype object for baseClass, which points to the constructor

console.log(base);

base.b = "This is b. I was just added to the base class instance.";

console.log(base);

// thing.prototype points to the constructor for the baseClass (rather than for its own class)
let prototype = baseClass.prototype;
console.log(baseClass.prototype === base.__proto__);//true

let anotherBase = new baseClass();
console.log(anotherBase.__proto__===anotherBase.__proto__)

// thimg.prototype will always point to the constructor, but we can add our own properties to it as well!
prototype.name = "Prototype";
prototype.greeting = "Hello. I am the prototype for the baseClass. It's nice to meet you";
prototype.sit = function sit(chair) {console.log("I am sigging on "+chair);};

// now if I print out the classes that point to prototype, they've updated as well since they point to the same thing
console.log(base);
console.log(anotherBase);

// Therefore, base and anotherBase have de-facto inherited name and greeting from the baseClass prototype! Moreover, any property from the baseClass prototype
// not overwritten by the instance has the prototype's values and can be accessed accordingly
console.log(base.name);
console.log(base.greeting);
console.log(anotherBase.greeting);
console.log("Whoa, I didn't set the names and greetings above, my prototype did!")
base.name = "Newer Model";
console.log(`Calling yourself prototype is so no longer fashionable. This base instance calls itself \"${base.name}\"`)
console.log(anotherBase.name);

function baseBaby(){
    this.b=2;
}

// Now lets have the instance of baseBaby named baby inherit from base
// If we want baby to inherit from base, we have to assign baseBaby's prototype property to point to base, which also points to baseClass.prototype

baseBaby.prototype = base;
let baby = new baseBaby();

console.log(baby);

console.log("From the baseBaby prototype: "+baby.name);
console.log("From the baseBaby prototype: "+baby.a);
console.log("From the baseBaby constructor: "+baby.b);
console.log("From the baseBaby prototype: "+baby.greeting);

baby.name = "N3w35t M0d3l";
console.log(`PSSSSHHHHHH, that old name is so lame. This baseBaby calls itself ${baby.name} now!`);
baby.greeting = "\'Sup losers! That\'s my new greeting now! *baby is going through a phase*";

console.log(baby.greeting);

console.log(baby.a, baby.b);