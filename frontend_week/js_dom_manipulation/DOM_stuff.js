// just document shows us the DOM, what we are working with
// console.log(document);

// in array structure instead of document, html hierarchy
// console.log(document.all);

// select elements, edit them
let para1 = document.getElementById("para2");
// console.log(para1.innerHTML);
para1.innerHTML = "change it up";

para1.innerHTML = "change it a different way with html <b>tags</b>"
// para1.innerText = "change it a different way with inner <b>text</b>"

// elements by class
let para2 = document.getElementsByClassName("headers");
//access that part of the array, if you don't want to iterate
// console.log(para2[0].innerHTML = "huh");

// IT ISN'T ACTUALLY AN array, it is an HTMLCollection or NodeList so spread it to use .forEach
[...para2].forEach(element => {
    element.style.color = "red";
});

