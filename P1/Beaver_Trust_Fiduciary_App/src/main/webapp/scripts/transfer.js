window.addEventListener("onload", console.log("transfer function running"));

let submit3 = document.getElementById("transferSubmit");

submit3.addEventListener("click", transfer);

function transfer() {
    // setup values from user form
    let accountFrom = document.getElementById("transferFromAccount").value;
    let accountTo = document.getElementById("transferToAccount").value;
    let amount3 = document.getElementById("transferAmount").value;

    console.log(accountFrom);
    console.log(accountTo);
    console.log(amount3);
    
    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/transfer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            accountFrom: accountFrom,
            accountTo: accountTo,
            amount: amount3
        }),
    })
    // try getting back whatever it is sending...
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {
    // clear forms
    document.getElementById("transferFromAccount").value = '';
    document.getElementById("transferToAccount").value = '';
    document.getElementById("transferAmount").value = '';
    // create popup to notify them of the result
    alert(responseText);
    location.reload();
}