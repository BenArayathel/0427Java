window.addEventListener("onload", console.log("withdraw function running"));

let submit2 = document.getElementById("wdSubmit");

submit2.addEventListener("click", withdraw);

function withdraw() {
    let wdAmount = document.getElementById("withdrawalAmount").value;
    
    // make call to api/servlet
    fetch("http://localhost:8090/Project1/Withdrawal", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            'Accept': 'application/json',
        },
        body: JSON.stringify({
            amount: wdAmount
        }),
    })
    .then(response => response.text())
    .then(responseText => handleResponse(responseText))
}

function handleResponse(responseText) {
    document.getElementById("withdrawalAmount").value = '';
    alert(responseText);
    location.reload();
}