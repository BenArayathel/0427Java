window.onload = function() {
  this.document.getElementById("regForm")
    .addEventListener("submit", this.submitUserInfo);
  // this.document.getElementById("selectLogin")
  //   .addEventListener("click", loginUser);
}

function submitUserInfo(event) {
  event.preventDefault();
  let username = document.getElementById("username").value;
  let email = document.getElementById("email").value;
  console.log(username);
  console.log(email);
  console.log("About to fetch")
  fetch('http://localhost:9999/BankOfBen/api/InitialRegistration', {
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "email": email
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(response => response.json())
        .then(responseJSON => processResponse(responseJSON))
        .catch(error => console.error(error));
}

function processResponse(responseJSON) {
  console.log(responseJSON);
  // if (responseJSON.url.includes("newUser")) {
  if ("url" in responseJSON) {
    console.log("redirect");
    window.location.href = responseJSON.url;
  } else if ("username" in responseJSON.body) {
    console.log("bad username");
    if (document.getElementById("usernameExists")){
      document.getElementById("usernameExists").innerText = `Username ${username} already exists. Please login below.`;
    } else {
      let div = document.createElement("div");
      div.id = "usernameExists";
      let regForm = document.getElementById("regForm");
      div.innerText = `Username ${username} already exists. Please login below.`;
      regForm.appendChild(div);
    }
  } else if ("email" in responseJSON.body) {
    console.log("bad email");
    if (document.getElementById("emailExists")) {
      document.getElementById("emailExists").innerText = `Email ${email} already exists. Please login below.`;
    } else {
      let div = document.createElement("div");
      div.id = "emailExists";
      let regForm = document.getElementById("regForm");
      div.innerText = `Email ${email} already exists. Please login below.`;
      regForm.appendChild(div);
    }
  } else {
    console.log("Couldn't figure out what to do");
    console.log(responseJSON.json());
    // const reader = responseJSON.body.getReader();

    // reader.read().then(({done, value}) => {
    //   if (done) {
    //     AbortController.close()
    //   }
    // })
    console.log(responseJSON.body);
  }
}