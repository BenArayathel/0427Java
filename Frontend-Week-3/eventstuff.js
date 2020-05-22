/**
 * 
 */

function revealFunc(eve) {
	let selection = document.querySelector("#favorite");
	selection.innerHTML = "<font color=red>Orange Cassidy</font>";
}

function titleFunc(eve) {
	let selection = document.querySelector("#maintitle");
	selection.innerHTML = "<font color=red>IT'S WRESTLING STUFF MOSTLY</font>";
}

function defaultTitleFunc(eve) {
	let selection = document.querySelector("#maintitle");
	selection.innerHTML = "WELCOME TO BEN'S PAGE OF BEN-RELATED NONSENSE!";
}

let but = document.getElementById("MysteryButton");
but.addEventListener("click", revealFunc);
let title = document.getElementById("maintitle");
title.addEventListener('mouseenter', titleFunc);
title.addEventListener('mouseleave', defaultTitleFunc);