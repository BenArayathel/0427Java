window.addEventListener("onload", console.log("transfer function running"));

let submit3 = document.getElementById("transferSubmit");

submit3.addEventListener("click", transfer);

function transfer() {
    let transAccount = document.getElementById("transferAccount").value;
    let transAmount = document.getElementById("transferAmount").value;
    
    fetch("http://localhost:8090/Project1/Transfer", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            targetAccount: transAccount,
            amount: transAmount
        }),
    })
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {
    document.getElementById("transferAccount").value = '';
    document.getElementById("transferAmount").value = '';
    alert(responseText);
    location.reload();
}