console.log(document); // prints html layout to console
console.log(document.all);
console.log(document.all[4]); // returns individual element; not a good way of selecting elements
console.log(document.URL); // returns URL of webpage
console.log(document.title);
console.log(document.links);
console.log(document.forms);
console.log(document.images);

// getElementByID
let para1 = document.getElementById("para2");
console.log(para1.innerHTML);
para1.innerHTML = "We're <strike>finally</strike> doing it!"; // innerHTML: HTML tags will be registered as HTML
para1.innerText = "Using inner text <b>LOOK AT ME</b>"; // innerText: HTML tags will be registered as plain text

// getElementsByClassName
let para2 = document.getElementsByClassName("headers");
console.log(para2);
para2[0].innerHTML = "Changing all header classes"

// getElementByTagName
let para3 = document.getElementsByTagName("p");
for (let i = 0; i < para3.length; i++) {
    para3[i].innerText = "Modifying all p tags" + i;
}

// Query Selectors; returns first element of the class/id type
let selection = document.querySelector("#para2");
selection.innerText = "Shtuff";

let selection2 = document.querySelector(".para");
selection2.innerText = "More shtuff";

let selection3 = document.querySelectorAll(".para");
console.log(selection3);
let oSelection = document.getElementsByClassName("para");
console.log(oSelection);
for (let i = 0; i < selection3.length; i++) {
    selection3[i].innerText = "No more shtuff";
}

// Child Nodes
let selection4 = document.querySelector(".para:nth-child(1)");
console.log(selection4);
/*
    .parentNodes / .parentElements
    .childNodes / .childElements
    .firstChild / .lastChild
    .firstElementChi;d / .lastElementChild
*/
console.log(selection2.parentNode.nodeType); // 1 is a DIV type
console.log(selection2.parentNode.nodeName); // selection2's parent node name is DIV
console.log(selection2.parentNode.nodeValue); // DIV contains no value
console.log(selection2.parentElement); // returns selection2's parent element (a DIV)

// Create Elements
let newDiv = document.createElement("div"); // creates a new element

newDiv.id = "newestDiv"; // populates the element
newDiv.setAttribute("title", "new div, who dis");

let divText = document.createTextNode("new text node right here"); // creates a text node
newDiv.appendChild(divText); // appends text node to div element

let newSelection = document.querySelector("#para1"); // appending new div element into document
newSelection.appendChild(newDiv);










