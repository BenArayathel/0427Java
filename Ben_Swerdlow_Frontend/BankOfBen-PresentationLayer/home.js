window.onload = function() {
    const regForm = document.getElementById("regForm");
    const username = document.getElementById("username");
    username.oninvalid = function(event) {
        console.log("Username invalid registered")
        event.target.setCustomValidity("");
        if (!e.target.validity.valid) {
            e.target.setCustomValidity("Username is required and must be between 4 and 20 "
            +"alphanumeric characters and must start with a letter")
        }
        username.oninput = function (event) {
            event.target.setCustomValidity("");
        }
    }
    const email = document.getElementById("email");
    const emailError = document.querySelector('#email + span.error');
    email.addEventListener("input", function (event) {
        // Each time the user types something, we check if the
        // form fields are valid.
      
        if (email.validity.valid) {
          // In case there is an error message visible, if the field
          // is valid, we remove the error message.
          emailError.innerHTML = ''; // Reset the content of the message
          emailError.className = 'error'; // Reset the visual state of the message
        } else {
          // If there is still an error, show the correct error
          showError();
        }
    });
    regForm.addEventListener("submit", function (event) {
        // if the email field is valid, we let the form submit
      
        if(!email.validity.valid) {
          // If it isn't, we display an appropriate error message
          showError();
          // Then we prevent the form from being sent by canceling the event
          event.preventDefault();
        }
    });
    this.document.getElementById("submitInfo").addEventListener("click", submitUserInfo);
    this.document.getElementById("submitInfo").addEventListener("keyup", submitUserInfo);
    this.document.getElementById("selectLogin").addEventListener("click", loginUser);
}

function showError() {
    const email = document.getElementById("email");
    const emailError = document.querySelector('#email + span.error');
    if(email.validity.valueMissing) {
      // If the field is empty
      // display the following error message.
      emailError.textContent = 'You need to enter an e-mail address.';
    } else if(email.validity.typeMismatch) {
      // If the field doesn't contain an email address
      // display the following error message.
      emailError.textContent = 'Entered value needs to be an e-mail address.';
    } else if(email.validity.tooShort) {
      // If the data is too short
      // display the following error message.
      emailError.textContent = `Email should be at least ${ email.minLength } characters; you entered ${ email.value.length }.`;
    }
  
    // Set the styling appropriately
    emailError.className = 'error active';
  }

function submitUserInfo() {
    let username = document.getElementById("username");
    let usernameValidation = validateUsername(username);
    let email = document.getElementById("email");
    let emailValidation = validateEmail(email);
    let reasonArray = [];
    if (!usernameValidation.valid && !emailValidation.valid) {
        reasonArray = [usernameValidation.reason, emailValidation.reason];
    } else if (!usernameValidation.valid) {
        reasonArray = [usernameValidation.reason];
    } else if (!emailValidation.valid) {
        reasonArray = [emailValidation.reason];
    }
    return reasonArray;
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

function validateEmail(email) {
    let valid = false;
    let reason = "";
    if (email.match()) {
        valid = true;
    } else {
        reason = "Invalid email address. Please enter a valid email address."
    }
    return {"valid": valid, "reason": reason}
}

function loginUser() {

}