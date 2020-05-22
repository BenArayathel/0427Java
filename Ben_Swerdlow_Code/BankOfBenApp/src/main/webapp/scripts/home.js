window.onload = function() {
  this.document.getElementById("regForm")
    .addEventListener("submit", this.submitUserInfo);
  // this.document.getElementById("selectLogin")
  //   .addEventListener("click", loginUser);
}

function submitUserInfo(event) {
  event.preventDefault();
  let username = document.getElementById("username");
  let email = document.getElementById("email");
  fetch('http://localhost:9999/BankOfBenApp/api/InitialRegistration', {
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "email": email
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(responseJSON => processResponse(responseJSON))
        .catch(error => console.error(error));
}

function processResponse(responseJSON) {
  if ("url" in  responseJSON) {
    window.location.href = responseJSON.url;
  } else if ("username" in responseJSON) {
    if (document.getElementById("usernameExists")){
      document.getElementById("usernameExists").innerText = `Username ${username} already exists. Please login below.`;
    } else {
      let div = document.createElement("div");
      div.id = "usernameExists";
      let regForm = document.getElementById("regForm");
      div.innerText = `Username ${username} already exists. Please login below.`;
      regForm.appendChild(div);
    }
  } else if ("email" in responseJSON) {
    if (document.getElementById("emailExists")) {
      document.getElementById("emailExists").innerText = `Email ${email} already exists. Please login below.`;
    } else {
      let div = document.createElement("div");
      div.id = "emailExists";
      let regForm = document.getElementById("regForm");
      div.innerText = `Email ${email} already exists. Please login below.`;
      regForm.appendChild(div);
    }
  }
}