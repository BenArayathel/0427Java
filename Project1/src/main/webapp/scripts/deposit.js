window.addEventListener("onload", console.log("deposit function running"));

let submit = document.getElementById("depSubmit");

submit.addEventListener("click", deposit);

function deposit() {
    let amount = document.getElementById("depositAmount").value;

    fetch("http://localhost:8090/Project1/Deposit", {
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
    .then(responseText => {
        document.getElementById("depositAmount").value = '';
        alert(responseText);
            location.reload();
    })
}
