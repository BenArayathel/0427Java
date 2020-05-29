window.addEventListener("onload", console.log("create bank account running"));

let submitAcct = document.getElementById("createSubmit");

submitAcct.addEventListener("click", create);

function create() {
    let amount = document.getElementById("amount").value;

    fetch("http://localhost:8090/Project1/CreateAccount", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            amount: amount
        }),
    })
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {

    document.getElementById("amount").value = '';

    alert(responseText);
    window.location.assign("view_accounts.html");
}