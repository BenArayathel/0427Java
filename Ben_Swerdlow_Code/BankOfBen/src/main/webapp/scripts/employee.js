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

/* <div id="viewBalances">
<label for="balances">Account Information</label>
<table class="table table-dark" id="balances">
    <thead>
        <tr id="balHeader">
            <th class="scope" id="balAccNum">Account Number</th>
            <th class="scope" id="balBalance">Balance</th>
            <th class="scope" id="balCustId">Customer ID</th>
            <th class="scope" id="balPending">Pending</th>
            <!--<th>Approve</th>
                <th>Reject</th>-->
        </tr>
    </thead>
</table>
</div> */

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
        accounts = await response.json();
        makeBalanceTable(accounts);
        // console.log(accounts);
        // let balances = document.getElementById("balances");
        // if (balances) {
        //     balances.innerHTML = "";
        //     // balances.class = "table table-dark";
        // } else {
        //     let balancesContainer = document.getElementById("balancesContainer");
        //     balances = document.createElement("table");
        //     balances.id = "balances";
        //     balances.className = "table table-dark";
        //     balancesContainer.prepend(balances);
        // }
        // let header = document.createElement("thead");
        // header.innerHTML = `
        //     <tr id="balHeader">
        //         <th class="scope" id="balAccNum">Account Number</th>
        //         <th class="scope" id="balBalance">Balance</th>
        //         <th class="scope" id="balCustId">Customer ID</th>
        //         <th class="scope" id="balPending">Status</th>
        //     </tr>
        // `;
        // document.getElementById("balances").appendChild(header);
        // for (const account of accounts) {
        //     customCreateTableRow(
        //         account.accountNumber,
        //         document.getElementById("balances"),
        //         {html: `<td>${account.accountNumber}</td>
        //                 <td id="${account.accountNumber}balance"><span class="dollarSign">$</span><span id="${account.accountNumber}dollars" class="dollars">${account.balance.toFixed(2)}</span></td>
        //                 <td id="${account.accountNumber}customerId">${account.customerId}</td>
        //                 <td>${account.pending ? `<span style="color: red">Pending<span>` : "Active"}</td>
        //                 ${account.pending ? 
        //                     `
        //                     <input type="button" class="btn btn-success" value="Approve" onclick="approveAccount(${account.accountNumber})">
        //                     <input type="button" class="btn btn-danger" value="Reject" onclick="rejectAccount(${account.accountNumber})">` : ""}`})
        // }
    }
}

async function getCustomerBalances(event) {
    event.preventDefault();
    let bodyObj = {username: document.getElementById("username").value}
    console.log(bodyObj);
    console.log(JSON.stringify(bodyObj));
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
        console.log(customerAccounts);
        makeBalanceTable(customerAccounts);
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
            {html: `<td>${account.accountNumber}</td>
                    <td id="${account.accountNumber}balance"><span class="dollarSign">$</span><span id="${account.accountNumber}dollars" class="dollars">${account.balance.toFixed(2)}</span></td>
                    <td id="${account.accountNumber}customerId">${account.customerId}</td>
                    <td>${account.pending ? `<span style="color: red">Pending<span>` : "Active"}</td>
                    ${account.pending ? 
                        `
                        <input type="button" class="btn btn-success" value="Approve" onclick="approveAccount(${account.accountNumber})">
                        <input type="button" class="btn btn-danger" value="Reject" onclick="rejectAccount(${account.accountNumber})">` : ""}`})
    }
}

function getAllTransactions(event) {
    event.preventDefault();
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