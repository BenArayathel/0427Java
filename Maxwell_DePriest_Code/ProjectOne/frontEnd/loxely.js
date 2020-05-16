let loginButton = document.getElementById("loginSubmitButton");
loginButton.addEventListener("click", checkCredentials(email, password));




function checkCredentials() {
    let email = document.getElementById("loginEmail").value;

    let password = document.getElementById("loginPassword").value;
    var checkEmail = "max@email.com"
    ;
    
    alert(email);// select user by email
    // if email is in db, check entered password against stored password
    // if match, send to index.html
    // store user email in cookies/session storage/ local storage/ etc

    // if password doesn't match, log error, inform customer to retry
    // if no email match, log error, inform customer to try again or sign up

}

function registerNewUser() {

}