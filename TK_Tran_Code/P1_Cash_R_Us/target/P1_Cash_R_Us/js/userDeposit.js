window.onload = function () {
    this.document.getElementById("depositBtn").addEventListener("click", this.deposit)
}

function deposit() {
    let depositAmount = document.getElementById("depositInput").value;

    console.log("-- Fetching to LoginServlet --")

    fetch("http://localhost:9999/DepositServlet", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            "balance": depositAmount
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