console.log("We're doing JS!!!!!")

/*
Created in 1995, by Brandan Eich (in 10 days)

JS is loosely typed, intereted, Object-based, scripting language (that's also dynamic),
conveived to create additionaly functionality to webpages.

Benefits:
    less server interaction
    increased interactivity 

    Features:
        ASI (Automatic semicolon insertion)
*/

//This is an inline comment 

/*
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
    ECMAScript 2015 (version 6) - add more stuff to the language.
    ECMA is an internal body that sets the standard for all scripting languages 
*/ 

/*
What are the ways to declare a variable?

    var: global or local, mutable

ES6:
    let: global, local or block scope, mutable 
    const: global, local or block scope, immutable 
*/

/*
What are the types of scopes inside JS?
        Global  - its accessible everyhere 
        Local - function/object scope 
        Block - within conditional statements (control flow statements)

*/

//-------------------------------------------------------------------

var a;
let b;
const c = "This value cannot change";

// c = "ANother string"; invalid const cannot be reassigned 
// console.log(c);

// console.log(a);

a = 5;
b = "5";

// console.log("Is a equal to b? " + ( a == b));

b = 5; //loosely typed, can switch data types at runtime

// console.log("Is a equal to b, and of the same type? " + ( a === b));


//-------------------------------------------------------------------

//Creating an object!!!

var obj = {}; //Just made an object 

let planet = {
    name: "Earth",
    distance: 1,
    "humans":true
};

// console.log(planet.name);
// console.log(planet.distance);
// console.log(planet.humans); //dot notation 

// console.log(planet["name"] + ": name of planet"); //bracket notation

planet.slogan = "IM RED!!!!";
planet.name = "MARS"; //can change value outside of object
planet["humans"] = "none here mate";
delete planet.distance;

//dynamic aspect of JS, can add/delete new fileds into objects at runtime. 

// console.log(planet);

//-------------------------------------------------------------------

let num1 = 10;
let num2 = num1;
num1 = 25;

// console.log("Number 1:" + num1);
// console.log("Number 2:" +num2);

//pass by value 

let obj1 = {
    value: 10
};

let num3 = obj1.value;
obj1.value = 55;
// console.log(num3);
// console.log(obj1.value);

//-------------------------------------------------------------------

//CREATING AN ARRAY 

let arrayOfPlanets = [
    ["Mercury", 0, "Doesn't have rings"],
    ["Mars",2],
    ["Jupiter", "Has 67 moons", true]
]

// console.log(arrayOfPlanets[0]);
// console.log(arrayOfPlanets[0]["2"]); //have multidimensional arrays
// arrayOfPlanets[1][10] = false; //arrays are very mutable
// console.log(arrayOfPlanets[1][2]);
// console.log(arrayOfPlanets[1]);

arrayOfPlanets[3] = arrayOfPlanets;

// console.log(arrayOfPlanets);

//-------------------------------------------------------------------

//Template literals, which use backticks ``, NOT "" ''

//OLD BORING WYA \

let tempNum = 42;
let oldWay = "The answer " + tempNum +"\n is the answer to the universe";

//NEW COOL WAY
let newWay = ` The answer ${tempNum}
    is 
        the 
            answer to the 
                                        UNIVERSE`

// console.log(oldWay);
// console.log(newWay);

//-------------------------------------------------------------------

//FUNCTIONS

// printStuff();

function printStuff(){
    var g;

    console.log(g);
    console.log("stuff");
    g = 7;
}

//hoisting 
/*
all declarations get moved to the top of their scope 
var delcarations get moved, let and const don't (ES6)
function declarations are hoisted completely, with function body as well  
*/

//-------------------------------------------------------------------

/*
Truthy vs Falsy values 
Everything has a true or boolean value, when used in conditional statements

    falsy values:
        0 is false (every other number is true)
        null
        undefined
        false 
        "" (empty string is false, every other string is truthy)
        NaN
*/

function checkTruthy(input){
    console.log(`input = ${input}, and is typeof ${typeof(input)} and 
    input is ${!!!input}`)
}

// let k;
// checkTruthy(k);
// checkTruthy(42);
// checkTruthy(0);
// checkTruthy("0");
// checkTruthy("");
// checkTruthy({});
// checkTruthy([]);
// checkTruthy(null);
// checkTruthy(NaN);

//-------------------------------------------------------------------

/*
What is NaN?
    Not a keyword or a data type, it is a property of the global object.
    isNan - function returns true if vriable is not a type of number

    1) Division of zero by zero 
    2) Division infinity by infinity 
    3) Multiplication infinity by zero 
    4) Converting a nun-numeric string or undefined into a number
*/

//-------------------------------------------------------------------

let foods = ["cake", "cookies", "pizza", "apples"];

let robot = {
    name: "Bob", 
    ability: "jump",
    weight: 1
}

//traditional for loop 
for(let i = 0; i<foods.length; i++){
    // console.log(foods[i]);
}

//for-in loop : iterate through the keys of an object 
for(let i in robot){
    // console.log("Key: " + i +" value:" + robot[i]);
}

//for-each loop: iterates through the values of an iterable, e.g. array
for(const i of foods){
    // console.log(foods[i]);
    // console.log(i);
}

//-------------------------------------------------------------------
//A little bit more about functions 

//funcitonal declaration 

function add(x,y){
    return x+y;
};

let as = add(2,3);
// console.log(as);

//Anonymous function 
var anon = function(a,b){ return a*b;}; //stored inside a variable 

// console.log(anon);

// var ab = anon(7,7);
// console.log(ab);


//callback function 
//passes a function as a parameter to another function 
//guarantee execution order 

// move("Cyprus", theWeather);

function move(destination, myfunc){
    console.log(`Moving to ${destination}`);
    let a = myfunc();
    return a;
}

function theWeather(){
    console.log("It's a tad warm here!!!");
    return "apples";
}

//Constructors

function Robot(name,ability,weight){
    this.name = name;
    this.ability = ability;
    this.weight = weight;
};

var r1 = new Robot("Steve","run, like really fast",0.8);

var r2 = new Robot("Bob 2.0", move,true);

// console.log(r1);
// console.log(r2.ability("Italy",theWeather));


//Self invoking functions
//functions that call themselves
//executes automatically if the expressions is follwed by paranethesis 
//IIFE - Immediately Invokable Function Expression 

let func1 = function(){
    console.log("Inside IIFE");
}();

//Fat Arrow Notation , introduced in ES6

let div = (x,y) => {return x/y};

var sayHello = name => {
    console.log(name + ' says hello');
}; //Ethan's contribution


// console.log(div("7","0"));

//-------------------------------------------------------------------


//Closure 
//Using scopes to achieve encapsulation 

//Example

var num = 0;

function add(){
    var num = 0;
    num += 1;
    return num;
}

add();
add();
add();


num = 0;

// console.log(add());

//nested functions, a function inside a function 
//scopes available to inner function?
//1) Access to its own scope
//2)Access to the outer functions variables 
//3) Access to global variables. 

let funcy = (function(){

    console.log("Inside Outer function!!!!")
    let num = 0;

    return function(apples) {

        console.log("Inside inner function!!!")

        if(apples == "just want the number"){
            console.log("Just return the number!!!")
            return num;
        }

        return num += apples;
    }

})(); //Self invoking functions

function rando(){
    console.log("doing stuff outside");
    let num = 0;

    // return function(){
    //     return num +=1;
    // }

    return [function(){return num+= 1;}, function(){return num-= 1}];
}

let ofuncy = rando();

ofuncy[0]();

// console.log(ofuncy[1]());
// console.log(ofuncy[1]());
// console.log(ofuncy[0]());
// console.log(ofuncy[0]());

let o2funcy = rando();

// let moreExplicit = function(){ return num +=1 };

// num = 7; 

// console.log(funcy(5));
// console.log(funcy(3));
// console.log(funcy(2));
// console.log(funcy("just want the number"));

//-------------------------------------------------------------------

//Inheritance
//prototypical inheirtance 
/*
In JS, when it comes to inheritance, it only has one constuct: objects. 
Each object has a private property which holds a link to another object, 
    called its prototype

The prototype has a prototype of its own and so on, until an object has reached 
    a null as its property. 
*/

function newStuff(){
    this.a = 1;
    this.b = "2";
} ;

let o = new newStuff();
// o.c = 3;

newStuff.prototype.b = 3;
newStuff.prototype.c = 5;

let p = newStuff.prototype;

o.proto

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



<<<<<<< HEAD
// console.log(o);

function animal() {
    this.move = "moving";
};

let animal1 = new animal();

console.log(`animal toString = ${animal.toString()} | animal valueOf = ${animal.valueOf()}`);
console.log(`animal.__proto__ toString = ${animal.__proto__.toString()} | animal.__proto__ valueOf = ${animal.__proto__.valueOf()}`);
console.log(`animal.__proto__.__proto__ toString = ${animal.__proto__.__proto__.toString()} | animal.__proto__.__proto__ valueOf = ${animal.__proto__.__proto__.valueOf()}`);
console.log(`animal.__proto__.__proto__.__proto__ toString = ${animal.__proto__.__proto__.__proto__.toString()} | animal.__proto__.__proto__.__proto__ valueOf = ${animal.__proto__.__proto__.__proto__.valueOf()}`);
console.log(animal.__proto__);
console.log(animal.__proto__.__proto__);
console.log(animal.__proto__.__proto__.__proto__);

=======
// console.log(o);
>>>>>>> ae219a6741e2eefa628b52d71a86bd09461d71cb
