console.log("Connection Test");

// Called from HTML
function myFunc(myVar) {
    console.log("Upgraded way!");
    console.log("Still not all the way, though.");
}

// HTML simply defines the ID, this completely independent JS file implements it
let butt = document.getElementById("mainBtn");
butt.addEventListener("click", myFuncTwo);

function myFuncTwo(event) {
    // If an event callback func is given a param, you'll be automatically given an obj that contains details
    console.log("Dynamic Event Fired!");
    console.log(event); // prints what the event was to console
}

// Display Date Example
let newButt = document.getElementById("dateBtn");
newButt.addEventListener("click", myFuncThree);

function myFuncThree(event) {
    console.log("Date Button Clicked!");
    console.log(event);
    console.log(Date());
}

// MouseOver Example
let mouse = document.getElementById("mseOver");
mouse.addEventListener("mouseover", myFuncFour);

function myFuncFour(event) {
    console.log("Moused Over!");
    console.log(event);
}


