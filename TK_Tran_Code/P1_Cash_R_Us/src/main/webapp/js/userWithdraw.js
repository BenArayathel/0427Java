window.onload = function () {
    this.document.getElementById("withdrawBtn").addEventListener("click", this.withdraw)
}

function withdraw() {
    let withdrawAmount = document.getElementById("withdrawInput").value;

    console.log("-- Fetching to LoginServlet --")

    fetch("http://localhost:9999/WithdrawServlet", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=UTF-8"},
        body: JSON.stringify({
            "balance": withdrawAmount
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