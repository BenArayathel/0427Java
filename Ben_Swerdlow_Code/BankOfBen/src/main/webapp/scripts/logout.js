window.onload = function() {
    this.makeLogoutButton();
}

function makeLogoutButton() {
    let body = document.getElementById("body");
    let logoutButton = document.createElement('input');
    logoutButton.id = "logoutButton";
    logoutButton.className = "btn btn-light";
    logoutButton.addEventListener("click", logoutUser);
    logoutButton.style = "float: right";
    body.prepend(logoutButton);
}

function logoutUser() {

}