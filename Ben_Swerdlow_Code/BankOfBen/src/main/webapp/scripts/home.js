window.onload = function() {
  let regForm = this.document.getElementById("regForm");
  if (regForm) {
    regForm.addEventListener("submit", this.submitUserInfo);
  }
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
        .then(response => processResponse(response))
        .catch(error => console.error(error));
}

async function processResponse(response) {
  // console.log(response);
  let responseJSON;
  // console.log(responseJSON);
  // if (responseJSON.url.includes("newUser")) {
  if (response.url.endsWith(".html") && response.url !== window.location.href) {
    // console.log(response.url);
    // console.log(window.location.href);
    // await new Promise(r => setTimeout(r, 8000));
    // console.log("redirect");
    window.location.href = response.url;
  } else {
    responseJSON = await response.json();
    // console.log(responseJSON);
    // await new Promise(r => setTimeout(r, 8000));
    if ("username" in responseJSON) {
      // console.log("bad username");
      if (document.getElementById("usernameExists")){
        document.getElementById("usernameExists").innerText = `Username \"${responseJSON.username}\" is an existing Bank of Ben username.`;
      } else {
        customCreateElement(
          "usernameExists",
          document.getElementById("regForm"),
          {text: `Username \"${responseJSON.username}\" is an existing Bank of Ben username.`});
      }
    } else if ("email" in responseJSON) {
      // console.log("bad email");
      if (document.getElementById("emailExists")) {
        document.getElementById("emailExists").innerText = `Email \"${responseJSON.email}\" is an existing Bank of Ben user email.`;
      } else {
        customCreateElement(
          "emailExists",
          document.getElementById("regForm"),
          {text: `Email \"${responseJSON.email}\" is an existing Bank of Ben user email.`});
      }
    } else {
      // console.log(responseJSON);
      console.error("Couldn't figure out what to do");
    }
  }
}

function customCreateElement(id, attachToElement, inner){
  let div = document.createElement("div");
  div.id = id;
  if (inner && "text" in inner) {
    div.innerText = inner.text;
  } else if (inner && "html" in inner) {
    div.innerHTML = inner.html;
  } else if (inner) {
    throw "Couldn't figure out how to populate element. Inner object"
          +" must contain either \"text\" or \"html\" property"
  }
  attachToElement.appendChild(div);
}