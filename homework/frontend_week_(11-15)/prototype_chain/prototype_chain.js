// /*
// a js prototype chain is an approach to OOP. js doesn't really have classes,
// but you can create constructor functions (and, using es6, even make them look)
// like a class with its Syntax. we want to mimic inheritance by chaining prototypes,
// which looks a lot like a linked list that collections functions from each
// node and eventually always reaches back to Object.

// constructors can be capitalized to differentiate them from other normal functions
// */

//     //1.
// // this is how you'd create just one object
// // let Ninja = {}
// // Ninja.type = "Superhero"
// // Ninja.weapon = "Big Muscle"

//     // 2. 
// function Ninja(name, type, weapon) {
//     this.stat = "10"
//     this.name = name;
//     this.type = type;
//     this.weapon = weapon;

//     // this.catchphrase = function(words) {
//     //     console.log(name + " says: " + words)
//     // }
// }

// Ninja.prototype.catchphrase = function(words) {
//     console.log(name + " says: " + words)
// }

//     // 3.
// //this is the ES6 syntax
// // class Ninja {
// //     constructor(name, type, weapon) {
// //         this.name = name;
// //         this.type = type;
// //         this.weapon = weapon;
// //     }
// // }

// // a couple of instances
// let iceNinja = new Ninja("iceNinja", "Elemental", "Icicle");
// let superNinja = new Ninja("superNinja", "super", "big muscles");

// // iceNinja is accessing the catchphrase method from Ninja.prototype
// iceNinja.catchphrase("i'm first!")
// // and this is what the superNinja object (instance of Ninja class) looks like
// console.log(superNinja);

// let superDuperNinja = Ninja.prototype;
// superDuperNinja.catchphrase("bigger and better!")

// function HeroNinja() {
    
// }

// HeroNinja.prototype = new Ninja();

// HeroNinja.prototype.saveCat = function() {
//     console.log("i saved a cat!")
// }

// let goodGuy = new HeroNinja("goodGuy", "superStrong", "quirky");

// console.log(goodGuy)
// console.log(goodGuy.saveCat)
// console.log(iceNinja.prototype.saveCat)

function BaseClass() {
    this.a = 1;
}

let base = new BaseClass();

base.b = 12;

let prototype = BaseClass.prototype

prototype.name = "i'm the name";
BaseClass.prototype.sit = function (chair) {
    console.log("i'm in a " + chair)
}

console.log(base.name);
base.sit("chair");

let secondBase = new BaseClass();
secondBase.name = "now IIIMMMMM the name"
console.log(secondBase.name);

