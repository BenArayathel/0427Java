let regButton = document.getElementById("registerButton");
regButton.addEventListener("click", comparePasswords);

function comparePasswords() {
    let firstPassword = document.getElementById("newPassword");
    let secondPassword = document.getElementById("confirmPassword");

    if(firstPassword.value == secondPassword.value && firstPassword != "") {
        alert("Passwords match");
        registerNewCustomer("name", "email", "phone", firstPassword.value);
    }
    else {
        alert("Passwords don't match or fields are empty. Please try again.");
    }
}

function registerNewCustomer(name, email, phoneNum, password) {
    // CREATENEWUSER(id, name, email, phonenumber, password, status)

}