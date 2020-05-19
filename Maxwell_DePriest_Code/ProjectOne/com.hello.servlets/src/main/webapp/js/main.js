
window.onload = function() {
    
    console.log("Index.html loaded");
    
}
var uEmail = localStorage.getItem("email");
console.log(uEmail);
var greet = document.getElementById("customerGreeting")
greet.innerHTML = `Howdy, ${uEmail}`;