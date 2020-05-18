window.onload = function() {
    this.document.getElementById("submitInfo").addEventListener("click", submitUserInfo);
    this.document.getElementById("submitInfo").addEventListener("keyup", submitUserInfo);
    this.document.getElementById("selectLogin").addEventListener("click", loginUser);
}

function submitUserInfo() {
    let username = document.getElementById("username");
    let usernameValidation = validateUsername(username);
    let email = document.getElementById("email");
    let emailValidation = validateEmail(email);
    let password = document.getElementById("password");
    validatePassword(password);
    
}

function validateUsername(username) {
    let valid = false;
    let reason = "";
    if (username.length < 4) {
        reason = "Username cannot be less than 4 characters long.";
    } else if (username.length > 20) {
        reason = "Username cannot be more than 20 characters long."
    } else if (!username.match("^[A-Za-z]{1}[A-Za-z0-9]{3,}$")) {
        reason = "Username must be an alphanumeric entry between 4 and 20 characters long "
            +"starting with a alphabetical letter";
    } else {
        valid = true;
        reason = null;
    }
    return {"valid": valid, "reason": reason}
}

function loginUser() {

}