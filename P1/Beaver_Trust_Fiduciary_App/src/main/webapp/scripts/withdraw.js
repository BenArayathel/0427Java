window.addEventListener("onload", console.log("withdraw function running"));

let submit2 = document.getElementById("withdrawSubmit");

submit2.addEventListener("click", withdraw);

function withdraw() {
    // setup values from user form
    let account2 = document.getElementById("withdrawAccount").value;
    let amount2 = document.getElementById("withdrawAmount").value;

    console.log(account2);
    console.log(amount2);
    
    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/withdraw", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            account: account2,
            amount: amount2
        }),
    })
    // try getting back whatever it is sending...
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {
    // clear forms
    document.getElementById("withdrawAccount").value = '';
    document.getElementById("withdrawAmount").value = '';
    // create popup to notify them of the result
    alert(responseText);
    location.reload();
}