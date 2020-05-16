
let loginButton = document.getElementById("loginButton");
loginButton.addEventListener("click", checkCredentials);

function checkCredentials() {
    let email = document.getElementById("loginEmail");
    let password = document.getElementById("loginPassword");
    var checkEmail = "max@email.com";

    if (checkEmail == email.value) {
        alert("Email confirmed");
    }
    // select user by email
    // if email is in db, check entered password against stored password
    // if match, send to index.html
    // store user email in cookies/session storage/ local storage/ etc

    // if password doesn't match, log error, inform customer to retry
    // if no email match, log error, inform customer to try again or sign up

}