window.onload = function() {
    this.setFirstLastName();
    let seeAllBalances = this.document.getElementById("seeAllBalances");
    if (seeAllBalances) {
        seeAllBalances.addEventListener("click", getAllBalances);
    }
    let customerBalancesFrom = this.document.getElementById("customerBalancesFrom");
    if (customerBalancesFrom) {
        customerBalancesFrom.addEventListener("submit", getCustomerBalances);
    }
    let seeAllTransactions = this.document.getElementById("seeAllTransactions");
    if (seeAllTransactions) {
        seeAllTransactions.addEventListener("click", getAllTransactions);
    }
}

async function setFirstLastName() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getFirstLastName', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        console.log(response.url);
        window.location.href = response.url;
    } else {
        console.log("setting first last name")
        let userDetails = await response.json();
        let firstLast = document.getElementById('firstLast');
        firstLast.innerText = `${userDetails.firstName} ${userDetails.lastName}`;
    }
}

let accounts;

async function getAllBalances(event) {
    event.preventDefault();
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getAllBalances', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let badUsername = document.getElementById("badUsername");
        if (badUsername){
            badUsername.parentElement.removeChild(badUsername);
        }
        accounts = await response.json();
        makeBalanceTable(accounts);
    }
}

async function getCustomerBalances(event) {
    event.preventDefault();
    let bodyObj = {username: document.getElementById("username").value}
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getCustomerBalances', {
            method: 'POST',
            body: JSON.stringify(bodyObj),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let customerAccounts = await response.json();
        let badUsername = document.getElementById("badUsername");
        if ("ERROR" in customerAccounts) {
            if (badUsername){
                badUsername.innerHTML = `<strong>Warning!</strong>: Username ${document.getElementById("username").value} does not exist in the database.`;
            } else {
                customWarningElement(
                    "badUsername",
                    document.getElementById("customerBalancesFrom"),
                    `<strong>Warning!</strong>: Username ${document.getElementById("username").value} does not exist in the database.`);
            }
        } else {
            if (badUsername){
                badUsername.parentElement.removeChild(badUsername);
            }
            console.log(customerAccounts);
            makeBalanceTable(customerAccounts);
        }
    }
}

function customWarningElement(id, attachToElement, html){
    let div = document.createElement("div");
    div.id = id;
    div.class = "warning";
    div.style = "background-color: #ff9800"
    div.innerHTML = html;
    attachToElement.appendChild(div);
}

async function getAllTransactions(event) {
    event.preventDefault();
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getAllTransactions', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let transactions = await response.json();
        makeTransactionTable(transactions);
    }

}

function makeBalanceTable(accountsArray) {
    let balances = document.getElementById("balances");
    if (balances) {
        balances.innerHTML = "";
    } else {
        let balancesContainer = document.getElementById("balancesContainer");
        balances = document.createElement("table");
        balances.id = "balances";
        balances.className = "table table-dark";
        balancesContainer.prepend(balances);
    }
    let header = document.createElement("thead");
    header.innerHTML = `
        <tr id="balHeader">
            <th class="scope" id="balAccNum">Account Number</th>
            <th class="scope" id="balBalance">Balance</th>
            <th class="scope" id="balCustId">Customer ID</th>
            <th class="scope" id="balPending">Status</th>
        </tr>
    `;
    document.getElementById("balances").appendChild(header);
    for (const account of accountsArray) {
        customCreateTableRow(
            account.accountNumber,
            document.getElementById("balances"),
            {html: `<td id="${account.accountNumber}accNum">${account.accountNumber}</td>
                    <td id="${account.accountNumber}balance"><span class="dollarSign">$</span><span id="${account.accountNumber}dollars" class="dollars">${account.balance.toFixed(2)}</span></td>
                    <td id="${account.accountNumber}customerId">${account.customerId}</td>
                    <td id="${account.accountNumber}balPending">${account.pending ? `<span style="color: red">Pending<span>` : "Active"}</td>
                    ${account.pending ? 
                        `
                        <input id="${account.accountNumber}appButton" type="button" class="btn btn-success" value="Approve" onclick="approveAccount(${account.accountNumber})">
                        <input id="${account.accountNumber}rejButton" type="button" class="btn btn-danger" value="Reject" onclick="rejectAccount(${account.accountNumber})">` : ""}`})
    }
}

async function approveAccount(accountNumber) {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/approveAccount', {
            method: 'POST',
            body: JSON.stringify({"accountNumber": `${accountNumber}`}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    let responseJSON = await response.json();
    if (responseJSON.result=="approved") {
        // update the view
        let balPending = document.getElementById(`${accountNumber}balPending`);
        let appButton = document.getElementById(`${accountNumber}appButton`);
        let rejButton = document.getElementById(`${accountNumber}rejButton`);
        balPending.innerHTML = "Active";
        appButton.parentNode.removeChild(appButton);
        rejButton.parentNode.removeChild(rejButton);
    } else {
        console.log("Error! Could not reject account "+accountNumber);
    }
}

async function rejectAccount(accountNumber) {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/rejectAccount', {
            method: 'POST',
            body: JSON.stringify({"accountNumber": `${accountNumber}`}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    let responseJSON = await response.json();
    if (responseJSON.result=="rejected") {
        // update the view
        let balEntry = document.getElementById(`${accountNumber}`);
        balEntry.parentNode.removeChild(balEntry);
    } else {
        console.log("Error! Could not approve account "+accountNumber);
    }
}

function makeTransactionTable(transactionsArray) {
    let logs = document.getElementById("logs");
    if (logs) {
        logs.innerHTML = "";
    } else {
        let logContainer = document.getElementById("logContainer");
        logs = document.createElement("table");
        logs.id = "logs";
        logs.className = "table table-dark";
        logContainer.prepend(logs);
    }
    let header = document.createElement("thead");
    header.innerHTML = `
        <tr id="balHeader">
            <th class="scope" id="logTransId">Transaction ID</th>
            <th class="scope" id="logTransTime">Timestamp</th>
            <th class="scope" id="logTransSrcAccNum">Source Account Number</th>
            <th class="scope" id="logTransInitBal">Initial Balance</th>
            <th class="scope" id="logTransAmount">Amount</th>
            <th class="scope" id="logTransFinalBal">Final Balance</th>
            <th class="scope" id="logTransDestAccNum">Destination Account Number</th>
        </tr>
    `;
    document.getElementById("logs").appendChild(header);
    for (const transaction of transactionsArray) {
        customCreateTableRow(
            transaction.transactionId,
            document.getElementById("logs"),
            {html: `<td id="${transaction.transactionId}">${transaction.transactionId}</td>
                    <td id="${transaction.transactionId}time">${transaction.timestamp}</td>
                    <td id="${transaction.transactionId}srcAccNum">${transaction.accountNumber}</td>
                    <td id="${transaction.transactionId}initBal"><span class="dollarSign">$</span><span id="${transaction.transactionId}dollars" class="dollars">${transaction.initialBalance.toFixed(2)}</span></td>
                    <td id="${transaction.transactionId}amount"><span class="dollarSign">$</span><span id="${transaction.transactionId}dollars" class="dollars">${transaction.amount.toFixed(2)}</span></td>
                    <td id="${transaction.transactionId}finalBal"><span class="dollarSign">$</span><span id="${transaction.transactionId}dollars" class="dollars">${transaction.finalBalance.toFixed(2)}</span></td>
                    <td id="${transaction.transactionId}destAccNum">${transaction.otherAccountNumber}</td>`})
    }
}

function customCreateTableRow(id, attachToElement, inner){
    let tr = document.createElement("tr");
    tr.id = id;
    if (inner && "text" in inner) {
      tr.innerText = inner.text;
    } else if (inner && "html" in inner) {
      tr.innerHTML = inner.html;
    } else if (inner) {
      throw "Couldn't figure out how to populate element. Inner object"
            +" must contain either \"text\" or \"html\" property"
    }
    attachToElement.appendChild(tr);
  }