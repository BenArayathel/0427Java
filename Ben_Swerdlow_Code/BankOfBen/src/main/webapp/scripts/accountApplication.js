window.onload = function() {
    this.console.log("setting up web page")
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
            "startingBalance": document.getElementById("startBal").value
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}})
    .then(response => processResponse(response))
    .catch(error => console.error(error));
}

async function processResponse(response) {
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
    console.log("getting frist last name")
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getFirstLastName', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        });

    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        console.log("redirecting")
        window.location.href = response.url;
    } else {
        console.log("setting first last name")
        let userDetails = await response.json();
        let firstLast = document.getElementById('firstLast');
        firstLast.innerText = `${userDetails.firstName} ${userDetails.lastName}`;
    }
}