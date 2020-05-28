console.log("customer_home.js connected");

var validationServiceScript = document.createElement('script');
validationServiceScript.src = './scripts/validation_service.js';
document.getElementsByTagName("head")[0].appendChild(validationServiceScript);

// window.onload = listAccounts;
window.onload = function() {
    this.listAccounts();
    document.getElementById('ApplyForNewAccountButton').addEventListener('click', applyForAccount);
    this.document.getElementById('accountDepositButton').addEventListener('click', deposit);
    this.document.getElementById('accountWithdrawalButton').addEventListener('click', withdraw);
}

function listAccounts() {
    this.fetch('http://localhost:9090/BhankWebApp/CustomerHome')
    .then(response => {
        return response.json();
    })
    .then(json => {
        let accountRows = document.querySelectorAll('tbody tr, tbody td');
        accountRows.forEach(node => node.remove());
        for(let account in json) {
            let accountTR = document.createElement('tr');
            let accountIdTD = document.createElement('td');
            accountIdTD.innerText = json[account].id;
            let accountBalanceTD = document.createElement('td');
            accountBalanceTD.innerText = json[account].balance;
            let accountPendingTD = document.createElement('td');
            accountPendingTD.innerText = json[account].pending;
            let accountApprovedTD = document.createElement('td');
            accountApprovedTD.innerText = json[account].approved;

            accountTR.appendChild(accountIdTD);
            accountTR.appendChild(accountBalanceTD);
            accountTR.appendChild(accountPendingTD);
            accountTR.appendChild(accountApprovedTD);

            accountTR.addEventListener('click', function() {
                localStorage.setItem('selectedAccount', json[account].id);
                console.log(json[account].id);
            });

            this.document.getElementById('accountsTableBody').appendChild(accountTR);
        }
    })
    .catch(error => {
        this.console.error(error);
    })
}

let customerNameSpan = document.getElementById('customerNameSpan');
customerNameSpan.innerText = localStorage.getItem("username");
console.log(localStorage.getItem("username"));
console.log(localStorage.getItem("customerId"));

let accountsDiv = document.getElementById('accountsDiv');

let startingBalanceInput = document.getElementById('ApplyForNewAccountTextInput');

startingBalanceInput.addEventListener('input', function(event) {
    if(!isValidStartingBalance(startingBalanceInput.value)) {
        startingBalanceInput.setCustomValidity("Invalid starting balance (must contain only 1-10 numbers");
    } else {
        startingBalanceInput.setCustomValidity("");
    }
  });

  transactionAmountInput.addEventListener('input', function(event) {
    if(!isValidTransactionAmount(transactionAmountInput.value)) {
        transactionAmountInput.setCustomValidity("Invalid starting balance (must contain only 1-10 numbers");
    } else {
        transactionAmountInput.setCustomValidity("");
    }
  });

function applyForAccount() {
    if(!isValidStartingBalance(startingBalanceInput.value)) {
        alert("invalid starting balance");
    } else {
        const data = {"customerId": localStorage.getItem("customerId"), "balance": startingBalanceInput.value};
        fetch('http://localhost:9090/BhankWebApp/CustomerHome', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
          })
          .then(response => {
            return response.text();
          })
          .then(response => {
              listAccounts();
              console.log(response);
          })
    }   
}



function deposit() {
    let accountTransactionAmountInput = document.getElementById("accountTransactionAmountInput");
    const data = {'accountId':localStorage.getItem('selectedAccount'),'transactionAmount':accountTransactionAmountInput.value}
    fetch('http://localhost:9090/BhankWebApp/Deposit', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
          })
          .then(response => {
            return response.json;
          })
          .then(json => {
              listAccounts();
          })
}

function withdraw() {
    let accountTransactionAmountInput = document.getElementById("accountTransactionAmountInput");
    const data = {'accountId':localStorage.getItem('selectedAccount'),'transactionAmount':accountTransactionAmountInput.value}
    fetch('http://localhost:9090/BhankWebApp/Withdrawal', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
          })
          .then(response => {
            return response.json;
          })
          .then(json => {
              listAccounts();
          })
}
