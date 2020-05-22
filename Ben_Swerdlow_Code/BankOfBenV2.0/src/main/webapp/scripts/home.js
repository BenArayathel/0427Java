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
  fetch('http://localhost:9999/BankOfBenV2/Login', {
            method: 'POST',
            body: JSON.stringify({
                "username": username,
                "password": password
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        })
        .then(responseJSON => redirectLogin(responseJSON))
        .catch(error => console.error(error));
}