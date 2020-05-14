// console.log("Are we connected?");
/*

    What is the DOM?
        Document Object Model, virtual representation of the HTML page. 

    How do we access it?
*/
// console.log(document);
// console.log(document.all);
// console.log(document.all[10]); //NOT a good way of selecting an element 

// console.log(document.URL);
// console.log(document.title);
// console.log(document.links);
// console.log(document.images);
// console.log(document.forms);

//-------------------------------------------------------------------------

//Let's get an element by Id.
let para1 = document.getElementById("para1");
// console.log(para1.innerHTML);
para1.innerHTML = "We're <strike>finally</strike> doing it!!!!";
para1.innerText = "Using inner text <b>LOOK AT ME!!1</b>";

/*
InnerText: HTML tags will not be regisetered as HTML, but as plain text 
InnerHTML: HTML tags will be registered as HTML
*/


//-------------------------------------------------------------------------

//Let's get elements by class 
// let para2 = document.getElementsByClassName("headers");
// // console.log(para2);
// para2[0].innerHTML = "What's gonna happen?"

//-------------------------------------------------------------------------

// Get elements by tag name 

// let para3 = document.getElementsByTagName("p");

// for(let i = 0; i<para3.length; i++){
//     para3[i].innerText = "Modifying all the p tags " + i;
// }

//-------------------------------------------------------------------------

//Query Selectors!!!!

//prefixes: # for id, . for class, no prefix for a tag

// let selection = document.querySelector("#para2"); 
// selection.innerText = "shtuff";

// let selection2 = document.querySelector(".para");//return the first element of the class type
// selection2.innerText = "more shtuff";

// let selection3 = document.querySelectorAll(".para");
// console.log(selection3);

// let oSelection = document.getElementsByClassName("para");
// console.log(oSelection);

// console.log(oSelection["para1"]);
// console.log(selection3["para1"]);

// for(let i = 0; i<selection3.length; i++){
//     selection3[i].innerText = "No more shtuff";
// }


//-------------------------------------------------------------------------

// let selection4 = document.querySelector(".para:nth-child(4)");
// console.log(selection4);

/*
You can also use stuff like:
    .parentNodes / .parentElements
    .childNodes / .childElements
    .firstChild / .last Child
    firstElementChild / lastElementChild

    also, next and previous siblings... etc.
*/

// console.log(selection2.parentNode.nodeType);
// console.log(selection2.parentElement);

//-------------------------------------------------------------------------

//CREATE ELEMENTS
// let newDiv = document.createElement("div");

//Step 2: populate our new element with attributes
// newDiv.id = "newestDiv";
// newDiv.setAttribute("title","new div, who dis?");

//Step 3: create a text node then append to our new div element.
// let divText = document.createTextNode("new text node riiiiight here!!");
// newDiv.appendChild(divText);

//Step 4: appending our new div elementt into our doc
// let newSelection = document.querySelector("#para1");
// newSelection.appendChild(newDiv);
