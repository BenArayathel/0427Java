window.onload = function () {
    this.document.getElementById("viewAccountBtn").addEventListener("click", this.displayAccount)
}

let singleAccount = document.getElementById("singleAccount");

function displayAccount() {
    fetch('http://localhost:9999/GetAccountServlet')
        .then(response => response.json())
        .then(JSON => {
            console.log(JSON)
            let x = window.document.getElementById("accountTable");
            x.hidden = false; // table is initially hidden, this reveals it

            // For each account, create a new row and cell to append to said row
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            let td3 = document.createElement("td");
            let td4 = document.createElement("td");
            let td5 = document.createElement("td");

            // Extracts JSON to create text nodes
            let username = document.createTextNode(`${JSON.username}`)
            let password = document.createTextNode(`${JSON.password}`)
            let name = document.createTextNode(`${JSON.name}`)
            let balance = document.createTextNode(`${JSON.balance}`)
            let type = document.createTextNode(`${JSON.type}`)

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
            singleAccount.appendChild(tr);
        })
}