window.onload = function () {
    this.document.getElementById("transferBtn").addEventListener("click", this.transfer)
}

function transfer() {
    let payee = document.getElementById("payeeInput").value;
    let transferAmount = document.getElementById("transferInput").value;

    console.log("-- Fetching to LoginServlet --")

    fetch("http://localhost:9999/TransferServlet", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            "username": payee,
            "balance": transferAmount
        }) // JSON.stringify() turns JS values to a JSON string; BODY TYPE MUST MATCH HEADER'S "Content-Type"!!!
    })
        .then(response => response.text())
        .then(string => {
            console.log("-- Back from Servlet --")
            console.log("LoginServlet Response: " + string);
            console.log("Redirecting to: " + string);
            window.location.assign(string)
        })
        .catch(error => {
            console.log(error);
        });
}