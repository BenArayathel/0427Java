
window.onload = function() {
    let currentUser = localStorage.getItem();
    console.log("Index.html loaded");
    document.getElementsByClassName("customerGreeting").innerText = Howdy, partner;
}
