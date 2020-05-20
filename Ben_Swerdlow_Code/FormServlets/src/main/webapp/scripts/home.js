window.onload = function() {
    this.document.getElementById("subLogin").addEventListener("click", this.loginUser);
    // console.log("loaded window");
    // let readyP = document.createElement("p");
    // readyP.innerText = `Ready to go`;
    // this.document.body.appendChild(readyP);
}

function loginUser(event) {
    event.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    fetch('http://localhost:9999/FormServlets/Login', {
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

function redirectLogin(json) {
    // console.log(json);
    if (json.url.includes("loginSuccessful")){
        window.location.href = json.url;
        // console.log(json.url);
    } else {
        console.log(json);
        invalidCredentials();
    }
}

function invalidCredentials() {
    console.log("Invalid Credentials");
    document.getElementById("badLoginDiv").hidden = false;
    console.log("Warning should be shown");
}

function DOMManipulation(json) {
    let outputDiv = document.createElement("div");
    let outputPara = document.createElement("p");
    outputPara.innerText = `${json.username} and ${json.password}`
    outputDiv.appendChild(outputPara);
    document.body.append(outputDiv);
}
