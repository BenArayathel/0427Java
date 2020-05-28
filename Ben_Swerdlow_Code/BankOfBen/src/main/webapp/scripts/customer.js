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
    // makePaymentView();
    // makeRequestView();
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
                        <td><span class="dollarSign">$</span><span class="dollars">${account.balance.toFixed(2)}</span></td>
                        <td>${account.pending ? "Pending" : ""}</td>
                        ${account.pending ? "" : `<input type="number" placeholder="0.00" class="amount">
                        <input type="button" class="btn btn-primary" value="Deposit" onclick="makeDeposit(${account.accountNumber})">
                        <input type="button" class="btn btn-secondary" value="Withdraw" onclick="makeWithdrawal(${account.accountNumber})">`}`})
        }
        // let padBefore = padBefore + 1;
    }
}

async function makeDeposit(accountNumber) {
    let amount = document.querySelector(`#${accountNumber}.amount`);
    console.log(amount);
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/makeDeposit', {
            method: 'POST',
            body: JSON.stringify({
                "accountNumber": accountNumber,
                "amount": amount
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let account = await response.json();
        let dollars = document.querySelector(`#${accountNumber}.dollars`);
        dollars.innerText = account.balance;
        dollars.style = "color: green";
    }
}

async function makeWithdrawal(accountNumber) {
    let amount = document.querySelector(`#${accountNumber}.amount`);
    console.log(amount);
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/makeWithdrawal', {
            method: 'POST',
            body: JSON.stringify({
                "accountNumber": accountNumber,
                "amount": amount
            }),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html") && response.url !== window.location.href) {
        window.location.href = response.url;
    } else {
        let account = await response.json();
        let dollars = document.querySelector(`#${accountNumber}.dollars`);
        dollars.innerText = account.balance;
        dollars.style = "color: red";
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