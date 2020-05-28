window.onload = function() {
    let loginForm = this.document.getElementById("loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", this.submitLoginInfo);
    }
}

function submitLoginInfo(event) {
    event.preventDefault();
    fetch('http://localhost:9999/BankOfBen/api/login', {
        method: 'POST',
        body: JSON.stringify({
            "username": document.getElementById('username').value,
            "password": document.getElementById("password").value
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => processResponse(response))
    .catch(error => console.error(error));
}

async function processResponse(response) {
    let responseJSON;
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        responseJSON = await response.json();
        if ("username" in responseJSON) {
            if (document.getElementById("badLoginDiv")){
                document.getElementById("badLoginDiv").innerText = `<strong>Warning!</strong>: Invalid login. Username ${username} is not registered with the Bank of Ben.`;
            } else {
                customWarningElement(
                    "badLoginDiv",
                    document.getElementById("loginForm"),
                    `<strong>Warning!</strong>: Invalid login. Username ${username} is not registered with the Bank of Ben.`);
            }
        } else if ("password" in responseJSON) {
            if (document.getElementById("badLoginDiv")){
                document.getElementById("badLoginDiv").innerText = `<strong>Warning!</strong>: Invalid login. Incorrect password for username ${username}.`;
            } else {
                customWarningElement(
                    "badLoginDiv",
                    document.getElementById("loginForm"),
                    `<strong>Warning!</strong>: Invalid login. Incorrect password for username ${username}.`);
            }
        } else {
            console.error("Couldn't figure out what to do");
        }
    }
}

function customWarningElement(id, attachToElement, text){
    let div = document.createElement("div");
    div.id = id;
    div.class = "warning";
    div.style = "background-color: #ff9800"
    div.innerText = text;
    attachToElement.appendChild(div);
}

/*

    <p id="badLoginDiv" class="warning" style="background-color: #ff9800" hidden>
        <strong>Warning!</strong>: Invalid login. Please try again.
    </p>

*/