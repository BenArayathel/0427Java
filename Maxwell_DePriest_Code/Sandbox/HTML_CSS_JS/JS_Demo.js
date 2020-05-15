console.log("We're doing JS")

// Datatypes- number, string, boolean, null, undefined, array, function, object, symbol

//ECMAScripts 2015 (Version6) came out in 2015

// Ways to declare a variable - var, let, const (let and const are ES6)
/*
 * Features: 
 * ASI = auto semicolon insertion
 * 
 * 
/*
 * Scopes:
 * global - its accessible everywhere -
 * local - functino/object scope
 * block within conditional/control flow statments
 * 
 * var - global or local, mutable
 * 
 * let - global, local, or block, mutable
 * const- global, local, or block scope, immutable - has to be initialized when defined
 * 
 * *** Just use semicolons even if not necessary in JS
 * 
 * === is strictly equal 
 * 5 == "5" true 
 * 5 === "5" false
  */

function revealKittens() {
    let kittenC = (document.getElementById("kittensCarousel"));
    let allC = document.getElementsByClassName("carouselContainer");
    for(i = 0; i < allC.length; i++) {
        allC[i].hidden = true;
    }
    kittenC.hidden = false;
    
}

function revealOriginal() {
    let origC = (document.getElementById("mainCarousel"));
    document.getElementById("kittensCarousel").hidden = true;
    origC.hidden = false;
}

function genZombieText() {
    let mainP = document.getElementById("summaryText");
    let zombieP = document.getElementById("zombieText");
    mainP.hidden = true;
    zombieP.hidden = false;
}



let kB = document.getElementById("kittenButton");
let oB = document.getElementById("originalButton");
let zB = document.getElementById("zombieButton");
kB.addEventListener("click", revealKittens);
oB.addEventListener("click", revealOriginal);
zB.addEventListener("click", genZombieText);

function changeFontColor() {
    document.getElementById("summaryText").style.color = "#24529c";
}

function changeFontColorBack() {
    document.getElementById("summaryText").style.color = "#f7f023";
}

let sumText = document.getElementById("summaryText");
sumText.addEventListener("mouseenter", changeFontColor);
sumText.addEventListener("mouseleave", changeFontColorBack);

