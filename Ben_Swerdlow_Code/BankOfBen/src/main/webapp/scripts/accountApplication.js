window.onload = function() {
    this.setFirstLastName();

    let accAppForm = this.document.getElementById("accAppForm");
    if (accAppForm) {
        accAppForm.addEventListener("submit", submitAccountApplication);
    }
}

function submitAccountApplication(event) {
    event.preventDefault();
    fetch('http://localhost:9999/BankOfBen/api/accountApplication', {
        method: 'POST',
        body: JSON.stringify({
            "startingBalance": document.getElementById("startBal")
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    }).then(response => processResponse(response));
}

function processResponse(response) {
    let responseJSON;
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        responseJSON = await response.json();
        if ("ssn" in responseJSON) {
            if (document.getElementById("ssnExists")){
                document.getElementById("ssnExists").innerText = `Social security number \"${responseJSON.ssn}\" is an existing Bank of Ben user social security number.`;
            } else {
                customCreateElement(
                    "ssnExists",
                    document.getElementById("regInfoForm"),
                    {text: `Social security number \"${responseJSON.ssn}\" is an existing Bank of Ben user social security number.`});
                }
        } else {
            console.error("Couldn't figure out what to do");
        }
    }
}

async function setFirstLastName() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/GetFirstLastName', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        });

    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let userDetails = await response.json();
        let email = document.getElementById('userEmail');
        email.innerText = userDetails.email;
        let username = document.getElementById('userUsername');
        username.innerText = userDetails.username;
    }
}