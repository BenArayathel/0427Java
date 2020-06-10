console.log("We're connected");

function myFunc(myVar){
    console.log("This is an upgrade!")
    console.log("Still not all the way!")
}

function myFuncTwo(eve){

    /*
    if you give a evenet callback function a parameter then you'll 
    AUTOMATICALLY be given a object that contains the event's details

    */

   console.log("dynamic event has fired");
   console.log(eve.clientY);
   console.log("offset-y: "+eve.offsetY);
	console.log("client-x: "+eve.clientX);
    console.log("offset-x: "+eve.offsetX);

}

let but = document.getElementById("TheMainButton");
but.addEventListener("click", myFuncTwo);

let body = document.querySelector("body");
// body.addEventListener("mousemove",myFuncTwo);