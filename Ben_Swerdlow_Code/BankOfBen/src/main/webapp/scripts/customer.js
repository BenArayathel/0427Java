window.onload = function() {
    this.setFirstLastName();
    this.makeCustomerView();
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

function makeCustomerView() {
    makeBalanceView();
    makePaymentView();
    makeRequestView();
}

async function makeBalanceView() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getAccountsForCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let accounts = await response.json();
        console.log(accounts);
        // let padBefore = 0;
        for (const account of accounts) {
            // let wholeNumString = account.balance.toString().split(".")[0];
            // padBefore = Math.max(padBefore, wholeNumString.length);
            customCreateTableRow(
                account.accountNumber,
                document.getElementById("balances"),
                {html: `<td>${account.accountNumber}</td>
                        <td id="${account.accountNumber}balance"><span class="dollarSign">$</span><span id="${account.accountNumber}dollars" class="dollars">${account.balance.toFixed(2)}</span></td>
                        <td>${account.pending ? "Pending" : ""}</td>
                        ${account.pending ? "" : `<input type="number" step="0.01" placeholder="0.00" id="${account.accountNumber}amount">
                        <input type="button" class="btn btn-primary" value="Deposit" onclick="makeDeposit(${account.accountNumber})">
                        <input type="button" class="btn btn-secondary" value="Withdraw" onclick="makeWithdrawal(${account.accountNumber})">`}`})
        }
        // let padBefore = padBefore + 1;
    }
}

async function makePaymentView() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getPaymentsPendingInvolvingCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let payments = await response.json();
        // let padBefore = 0;
        for (const payment of payments) {
            customCreateTableRow(
                payment.id,
                document.getElementById("payments"),
                {html: `<td>${payment.id}</td>
                        <td>${payment.initUserId}</td>
                        <td>${payment.receivingAccountNumber}</td>
                        <td>${payment.amount.toFixed(2)}</td>
                        <td>${payment.pending ? "Pending" : ""}</td>
                        ${payment.pending ? `
                        <input type="button" class="btn btn-success" value="Accept" onclick="acceptPayment(${payment.id})">
                        <input type="button" class="btn btn-danger" value="Halt" onclick="rejectPayment(${payment.id})">` : "" }`})
        }
    }
}

async function makeRequestView() {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/getRequestsPendingInvolvingCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let requests = await response.json();
        // let padBefore = 0;
        for (const request of requests) {
            customCreateTableRow(
                request.id,
                document.getElementById("requests"),
                {html: `<td>${request.id}</td>
                        <td>${request.initUserId}</td>
                        <td>${request.receivingAccountNumber}</td>
                        <td>${request.amount.toFixed(2)}</td>
                        <td>${request.pending ? "Pending" : ""}</td>
                        ${request.pending ? `
                        <input type="button" class="btn btn-success" value="Accept" onclick="acceptRequest(${request.id})">
                        <input type="button" class="btn btn-danger" value="Halt" onclick="rejectRequest(${request.id})">` : "" }`})
        }
    }
}

async function acceptPayment(paymentId) {
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/acceptPayment', {
            method: 'POST',
            body: JSON.stringify({id: paymentId}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        //
    }
}

async function makeDeposit(accountNumber) {
    let bodyObj = {
        "accountNumber": `${accountNumber}`,
        "amount": document.getElementById(`${accountNumber}amount`).value
    };
    console.log(bodyObj);
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/makeDeposit', {
            method: 'POST',
            body: JSON.stringify(bodyObj),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    console.log(response);
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        console.log("Making reponse json.")
        let account = await response.json();
        let dollars = document.getElementById(`${accountNumber}dollars`);
        dollars.innerText = account.balance.toFixed(2);
        let balance = document.getElementById(`${accountNumber}balance`);
        balance.style = "color: green";
    }
}

async function makeWithdrawal(accountNumber) {
    let bodyObj = {
        "accountNumber": `${accountNumber}`,
        "amount": document.getElementById(`${accountNumber}amount`).value
    };
    console.log(bodyObj);
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/makeWithdrawal', {
            method: 'POST',
            body: JSON.stringify(bodyObj),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    console.log(response);
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        console.log("Making reponse json.")
        let account = await response.json();
        let dollars = document.getElementById(`${accountNumber}dollars`);
        dollars.innerText = account.balance.toFixed(2);
        let balance = document.getElementById(`${accountNumber}balance`);
        balance.style = "color: red";
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