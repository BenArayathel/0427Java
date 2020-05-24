window.addEventListener("onload", console.log("create bank account running"));

// what do i want to do with this? send off values, and then
// get back another alert saying the account is create and a redirect to
// the account page

let submit4 = document.getElementById("createSubmit");

submit4.addEventListener("click", create);

function create() {
    // setup values from user form
    let account = document.getElementById("newName").value;
    let amount = document.getElementById("newAmount").value;

    console.log(account);
    console.log(amount);
    
    

    // make call to api/servlet
    fetch("http://localhost:9999/Beaver_Trust_Fiduciary_App/createbankaccount", {
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
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {

    // clear forms
    document.getElementById("newName").value = '';
    document.getElementById("newAmount").value = '';

    // create popup to notify them of the result
    alert(responseText);
    window.location.assign("accounts_view.html");
}