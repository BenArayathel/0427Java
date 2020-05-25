window.onload = function () {
    this.document.getElementById("viewAccountsBtn").addEventListener("click", this.displayAllAccounts)
    this.document.getElementById("viewTransactionsBtn").addEventListener("click", this.displayAllTransactions)
}

let allAccounts = document.getElementById("allAccounts");

function displayAllAccounts() {
    fetch('http://localhost:9999/GetAllAccountsServlet')
        .then(response => response.json())
        .then(JSON => {
            console.log(JSON)
            for (let i in JSON) {
                let x = window.document.getElementById("accountsTable");
                x.hidden = false; // table is initially hidden, this reveals it

                // For each account, create a new row and cell to append to said row
                let tr = document.createElement("tr");
                let td1 = document.createElement("td");
                let td2 = document.createElement("td");
                let td3 = document.createElement("td");
                let td4 = document.createElement("td");
                let td5 = document.createElement("td");

                // Extracts JSON to create text nodes
                let username = document.createTextNode(`${JSON[i].username}`)
                let password = document.createTextNode(`${JSON[i].password}`)
                let name = document.createTextNode(`${JSON[i].name}`)
                let balance = document.createTextNode(`${JSON[i].balance}`)
                let type = document.createTextNode(`${JSON[i].type}`)

                // Append text nodes to each cell
                td1.appendChild(username);
                td2.appendChild(password);
                td3.appendChild(name);
                td4.appendChild(balance);
                td5.appendChild(type);

                // Append each cell to a row
                tr.className = "text-center"; // text of new cells will all be centered
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);

                // Append the row to the table
                allAccounts.appendChild(tr);
            }
        })
}

function displayAllTransactions() {
    let x = window.document.getElementById("allTransactions");
    x.hidden = false;
}