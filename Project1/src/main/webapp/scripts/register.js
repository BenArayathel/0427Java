window.addEventListener("onload", console.log("register new user running"));

let submitReg = document.getElementById("subRegister");

submitReg.addEventListener("click", create);

function create() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    fetch("http://localhost:8090/Project1/Register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            'username': username,
            'password': password
        }),
    })
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {

    document.getElementById("username").value = '';
    document.getElementById("password").value = '';

    alert(responseText);
    window.location.assign("customer_page.html");
}