window.onload = function () {
    this.document.getElementById("registerBtn").addEventListener("click", this.createAccount)
}

function createAccount() {
    // Assign values from form input to JS vars
    let username = document.getElementById("usernameInput").value;
    let password = document.getElementById("passwordInput").value;
    let name = document.getElementById("nameInput").value;
    let balance = document.getElementById("depositInput").value;
    let type = document.getElementById("typeInput").value;

    // SANITY CHECK
    console.log(username);
    console.log(password);
    console.log(name);
    console.log(balance);
    console.log(type);

    console.log("-- Starting Fetch to RegisterServlet --")

    // Send values to Servlet
    fetch("http://localhost:9999/RegisterServlet", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            "username": username,
            "password": password,
            "name": name,
            "balance": balance,
            "type": type
        })
    })
        .then(response => response.text())
        .then(string => {
            console.log("-- Back from Servlet --")
            console.log("RegisterServlet Response: " + string);
            console.log("Redirecting to: " + string);
            window.location.assign(string)
        })
        .catch(error => {
            console.log(error);
        });
}