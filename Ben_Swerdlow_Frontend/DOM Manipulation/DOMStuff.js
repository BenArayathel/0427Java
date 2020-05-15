console.log("Are we connected?");
/*

What is the DOM:
    Document Object Model, virtual representation of the HTML page.


*/
console.log(document);
console.log(document.all);

/*
The following line is NOT a good way to select an element 
(things could shift and it's not intuitive)
*/
console.log(document.all[7]);

console.log(document.URL);
console.log(document.title);
console.log(document.links);
console.log(document.forms);
console.log(document.images);

//----------------------------------------------------------------

// Get an element by id
let para1 = document.getElementById("para1");
console.log(para1.innerHTML);
// this will be registered as an HTML tag
para1.innerHTML = "We're <strike>finally</strike> doing it!!!!";
// this will not be registered as an HTML tag
para1.innerText = "Using inner text <b>LOOK AT ME!!!!!<\b>"
/*
InnerText: HTML tags will not be registered as HTML, but as plain text
InnerHTML: HTML tags will be registered 
*/

//----------------------------------------------------------------

// Get elements (plural) by class
let para2 = document.getElementsByClassName("headers");
console.log(para2);
para2[0].innerHTML = "What's going to happen?";

//----------------------------------------------------------------

// Get elements by tag name
let para3 = document.getElementsByTagName("p");

for (let i=0; i<para3.length; i++){
    para3[i].innerText = "Modifying all the p tags "+i;
}

//----------------------------------------------------------------

// Query Selectors!!!!!

let selection = document.querySelector("#para2");
selection.innerText = "shtuff";

let selection2 = document.querySelector(".para");// returns the first element of the class type
selection2.innerText = "more shtuff";

let selection3 = document.querySelectorAll(".para");
console.log(selection3);

let oSelection = document.getElementsByClassName("para");
console.log(oSelection["para1"]);
console.log(selection3["para1"]);

for (let i=0; i<selection3.length; i++){
    selection3[i].innerText = "No more shtuff!";
}

/*
Node is a more general form of an element (an element is a )
*/

//----------------------------------------------------------------

let selection4 = document.querySelector(".para:nth-child(3)")
console.log(selection4);

/*
You can also use stuff like:
    .parentNodes
    .parentElements
    .childNodes
    .childElements
    .firstChild
    .lastChild
    .firstElementChild
    .lastElementChild
    // also next and previous siblings, etc.
*/

// Will return 1, which means an element node
// See documentation for more details
// Here's a good site (easily findable on google)
// https://developer.mozilla.org/en-US/docs/Web/API/Node/nodeType
console.log(selection2.parentNode.nodeType);


//----------------------------------------------------------------

// CREATE ELEMENTS!

// 1) Create the element
let newDiv = document.createElement("div");

// 2) Populate our new element with attributes
newDiv.id = "newestDiv";
newDiv.setAttribute("title", "New div, who dis?");//Title is hover-text

// 3) Create a text node then append to our new div element
let divText = document.createTextNode("new text node riiiiight here!!");
newDiv.appendChild(divText);

// 4) Appending our new div element into our document.
let newSelection = document.querySelector("#para1");
newSelection.appendChild(newDiv);

//----------------------------------------------------------------

// EVENT LISTENERS!!!


//----------------------------------------------------------------



//----------------------------------------------------------------



//----------------------------------------------------------------