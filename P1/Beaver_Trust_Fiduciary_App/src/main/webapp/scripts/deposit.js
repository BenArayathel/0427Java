window.addEventListener("onload", console.log("deposit function running"));

let submit = document.getElementById("depositSubmit");

submit.addEventListener("click", deposit);

function deposit() {
    // setup values from user form
    let account = document.getElementById("depositAccount").value;
    let amount = document.getElementById("depositAmount").value;

    // console.log(account);
    // console.log(amount);
    
    

    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/deposit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            account: account,
            amount: amount
        }),
    })
    // try getting back whatever it is sending...
    .then(response => response.text())
    .then(responseText => {
//        console.log(responseText);
        // clear forms
        document.getElementById("depositAccount").value = '';
        document.getElementById("depositAmount").value = '';
        // create popup to notify them of the result
        alert(responseText);
            location.reload();
    })
}

// function handleResponse(responseText) {
// }