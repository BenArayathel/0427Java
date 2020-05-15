console.log("We're connected");

function myFunc(myVar){
    console.log("This is an upgrade");
    console.log("still not all the way!");
}

function myFuncTwo(eve){
    /*
    If you give an event callback function a parameter, you'll
    automatically be given an object that contains the event details
    */
    console.log("dynamic event has fired.")
    console.log(eve);
}

function myFuncThree(eve){
    console.log("This is the super special awesome modern way!");
}


// This is the better way!
let but = document.getElementById("TheMainButton");
console.log(but);
but.addEventListener("click", myFuncThree);

// let body = document.querySelector("body");
// body.addEventListener("mousemove", myFuncTwo);


/*
3 buttons
Remodel html page to just change the elemtns, not reload a page (i.e. two buttons)
Make another thing that does something different on event
*/