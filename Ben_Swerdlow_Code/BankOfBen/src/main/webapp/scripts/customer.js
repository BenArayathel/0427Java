window.onload = function() {
    this.setFirstLastName();
    let paymentPoster = this.document.getElementById("paymentPoster");
    if (paymentPoster) {
        paymentPoster.addEventListener("submit", postPayment);
    }
    let requestPoster = this.document.getElementById("requestPoster");
    if (requestPoster) {
        requestPoster.addEventListener("submit", postRequest);
    }
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

async function postPayment(event) {
    event.preventDefault();
    let payingAccountNumber = document.getElementById("payAccNum").value;
    let recievingAccountNumber = document.getElementById("recAccNum").value;
    let paymentAmount = document.getElementById("paymentAmount").value;
    let post = false;
    let accBal = undefined;
    for (const account of accounts) {
        if (account.accountNumber == payingAccountNumber) {
            accBal = account.balance;
            if (paymentAmount > account.balance){
                post=false;
            } else {
                post=true;
            }
        }
    }
    if (post) {
        let bodyObj = {
            "payingAccountNumber": `${payingAccountNumber}`,
            "recievingAccountNumber": `${recievingAccountNumber}`,
            "paymentAmount": `${paymentAmount}`
        }
        console.log(bodyObj);
        let response = await fetch(
            'http://localhost:9999/BankOfBen/api/postPayment', {
                method: 'POST',
                body: JSON.stringify(bodyObj),
                headers: {"Content-type": "application/json; charset=UTF-8"} 
            }
        );
        if (response.url.endsWith(".html")) {
            window.location.href = response.url;
        } else {
            console.log("Could not make payment!")
        }
    } else {
        console.log(`Could not make payment. Amount ${paymentAmount} cannot exceed balance ${accBal ? accBal : ""}`)
    }
}

async function postRequest(event) {
    event.preventDefault();
    let requestorAccountNumber = document.getElementById("reqAccNum").value;
    let soughtAccountNumber = document.getElementById("sotAccNum").value;
    let requestAmount = document.getElementById("requestAmount").value;
    let post = false;
    let accBal = undefined;
    // console.log(accounts);
    for (const account of accounts) {
        // console.log(account.accountNumber);
        if (account.accountNumber == requestorAccountNumber) {
            accBal = account.balance;
            if (requestAmount > account.balance){
                // console.log(requestAmount);
                // console.log(account.balance);
                post=false;
            } else {
                post=true;
            }
        }
    }
    console.log(post)
    if (post) {
        let response = await fetch(
            'http://localhost:9999/BankOfBen/api/postRequest', {
                method: 'POST',
                body: JSON.stringify({
                    "requestorAccountNumber": `${requestorAccountNumber}`,
                    "soughtAccountNumber": `${soughtAccountNumber}`,
                    "requestAmount": `${requestAmount}`
                }),
                headers: {"Content-type": "application/json; charset=UTF-8"} 
            }
        );
        if (response.url.endsWith(".html")) {
            window.location.href = response.url;
        } else {
            console.log("Could not make request!")
        }
    } else {
        console.log(`Could not make request. Amount ${requestAmount} cannot exceed balance ${accBal ? accBal : ""}`)
    }
}

async function makeCustomerView() {
    await makeBalanceView();
    await makePaymentView();
    await makeRequestView();
}

let accounts;

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
        accounts = await response.json();
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
                        ${account.pending ? "" : `<input type="number" step="0.01" placeholder="0.00" min=0.01 max=1000000000 id="${account.accountNumber}amount">
                        <input type="button" class="btn btn-primary" value="Deposit" onclick="makeDeposit(${account.accountNumber})">
                        <input type="button" class="btn btn-secondary" value="Withdraw" onclick="makeWithdrawal(${account.accountNumber})">`}`})
        }
        let paymentPoster = document.getElementById("paymentPoster");
        let requestPoster = document.getElementById("requestPoster");
        let payAccDropDown = document.createElement("select");
        let reqAccDropDown = document.createElement("select");
        payAccDropDown.id = "payAccNum";
        reqAccDropDown.id = "reqAccNum";
        payAccDropDown.required = true;
        reqAccDropDown.required = true;
        let payOptions = '<option value="" selected>Paying Account Number</option>\n';
        let reqOptions = '<option value="" selected>Requestor Account Number</option>\n';
        let options = '';
        for (const account of accounts) {
            if (!account.pending) {
                options = options.concat(`<option value=${account.accountNumber}>${account.accountNumber}</option>\n`);
            }
        }
        payAccDropDown.innerHTML = payOptions.concat(options);
        reqAccDropDown.innerHTML = reqOptions.concat(options);
        //<input id="payAccNum" type=number min=1000000000 max=9999999999 required placeholder="Paying Account Number">
        paymentPoster.prepend(payAccDropDown);
        requestPoster.prepend(reqAccDropDown);
    }
}

// async function makePaymentView() {
//     let response = await fetch(
//         'http://localhost:9999/BankOfBen/api/getPaymentsPendingInvolvingCustomer', {
//             method: 'POST',
//             headers: {"Content-type": "application/json; charset=UTF-8"}
//         }
//     );
//     if (response.url.endsWith(".html") && response.url !== window.location.href) {
//         window.location.href = response.url;
//     } else {
//         let payments = await response.json();
//         // let padBefore = 0;
//         for (const payment of payments) {
//             customCreateTableRow(
//                 payment.id,
//                 document.getElementById("payments"),
//                 {html: `<td>${payment.id}</td>
//                         <td>${payment.initUserId}</td>
//                         <td>${payment.receivingAccountNumber}</td>
//                         <td>${payment.amount.toFixed(2)}</td>
//                         <td>${payment.pending ? "Pending" : ""}</td>
//                         ${payment.pending ? `
//                         <input type="button" class="btn btn-success" value="Accept" onclick="acceptPayment('${payment.id}')">
//                         <input type="button" class="btn btn-danger" value="Halt" onclick="halt('${payment.id}')">` : "" }`})
//         }
//     }
// }

async function makePaymentView() {
    // Get the payments to the customer first. These will have the option to accept/halt
    let responseToCustomer = await fetch(
        'http://localhost:9999/BankOfBen/api/getPaymentsPendingToCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (responseToCustomer.url.endsWith(".html") && responseToCustomer.url !== window.location.href) {
        window.location.href = responseToCustomer.url;
    } else {
        let payments = await responseToCustomer.json();
        // let padBefore = 0;
        console.log(payments);
        console.log(JSON.stringify(payments));
        if (payments!==undefined && payments.length!=0) {
            for (const payment of payments) {
                customCreateTableRow(
                    payment.id,
                    document.getElementById("payments"),
                    {html: `<td>${payment.id}</td>
                            <td>${payment.initUserId}</td>
                            <td>${payment.payingAccountNumber}</td>
                            <td>${payment.receivingAccountNumber}</td>
                            <td>${payment.amount.toFixed(2)}</td>
                            <td>${payment.pending ? "Pending" : ""}</td>
                            ${payment.pending ? `
                            <input type="button" class="btn btn-success" value="Accept" onclick="acceptPayment('${payment.id}')">
                            <input type="button" class="btn btn-danger" value="Halt" onclick="haltPayment('${payment.id}')">` : "" }`})
            }
        } else {
            customCreateTableRow(
                "noToPayments",
                document.getElementById("payments"),
                {html: `<td>--------------------</td>
                        <td>--------------------</td>
                        <td>--------------</td>
                        <td>--------------</td>
                        <td>--------</td>
                        <td>------</td>`
                }
            );
        }
        customCreateTableRow(
            "paymentSeparator",
            document.getElementById("payments"),
            {html: ``}
        )
    }
    // Get the payments to the customer first. These will only have the option to halt
    let responseFromCustomer = await fetch(
        'http://localhost:9999/BankOfBen/api/getPaymentsPendingFromCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (responseFromCustomer.url.endsWith(".html") && responseFromCustomer.url !== window.location.href) {
        window.location.href = responseFromCustomer.url;
    } else {
        let payments = await responseFromCustomer.json();
        // let padBefore = 0;
        if (payments) {
            for (const payment of payments) {
                customCreateTableRow(
                    payment.id,
                    document.getElementById("payments"),
                    {html: `<td>${payment.id}</td>
                            <td>${payment.initUserId}</td>
                            <td>${payment.payingAccountNumber}</td>
                            <td>${payment.receivingAccountNumber}</td>
                            <td>${payment.amount.toFixed(2)}</td>
                            <td>${payment.pending ? "Pending" : ""}</td>
                            ${payment.pending ? `
                            <input type="button" class="btn btn-danger" value="Halt" onclick="haltPayment('${payment.id}')">` : "" }`})
            }
        } else {
            customCreateTableRow(
                "noFromPayments",
                document.getElementById("payments"),
                {html: `<td>--------------------</td>
                        <td>--------------------</td>
                        <td>--------------</td>
                        <td>--------------</td>
                        <td>--------</td>
                        <td>------</td>`
                }
            );
        }
    }
}

// async function makeRequestView() {
//     let response = await fetch(
//         'http://localhost:9999/BankOfBen/api/getRequestsPendingInvolvingCustomer', {
//             method: 'POST',
//             headers: {"Content-type": "application/json; charset=UTF-8"}
//         }
//     );
//     if (response.url.endsWith(".html") && response.url !== window.location.href) {
//         window.location.href = response.url;
//     } else {
//         let requests = await response.json();
//         // let padBefore = 0;
//         for (const request of requests) {
//             customCreateTableRow(
//                 request.id,
//                 document.getElementById("requests"),
//                 {html: `<td>${request.id}</td>
//                         <td>${request.initUserId}</td>
//                         <td>${request.requestorAccountNumber}</td>
//                         <td>${request.soughtAccountNumber}</td>
//                         <td>${request.amount.toFixed(2)}</td>
//                         <td>${request.pending ? "Pending" : ""}</td>
//                         ${request.pending ? `
//                         <input type="button" class="btn btn-success" value="Accept" onclick="acceptRequest('${request.id}')">
//                         <input type="button" class="btn btn-danger" value="Halt" onclick="haltRequest('${request.id}')">` : "" }`})
//         }
//     }
// }



async function makeRequestView() {
    // Get the payments to the customer first. These will have the option to accept/halt
    let responseToCustomer = await fetch(
        'http://localhost:9999/BankOfBen/api/getRequestsPendingToCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (responseToCustomer.url.endsWith(".html") && responseToCustomer.url !== window.location.href) {
        window.location.href = responseToCustomer.url;
    } else {
        let requests = await responseToCustomer.json();
        // let padBefore = 0;
        if (requests) {
            for (const request of requests) {
                customCreateTableRow(
                    request.id,
                    document.getElementById("requests"),
                    {html: `<td>${request.id}</td>
                            <td>${request.initUserId}</td>
                            <td>${request.requestorAccountNumber}</td>
                            <td>${request.soughtAccountNumber}</td>
                            <td>${request.amount.toFixed(2)}</td>
                            <td>${request.pending ? "Pending" : ""}</td>
                            ${request.pending ? `
                            <input type="button" class="btn btn-success" value="Accept" onclick="acceptRequest('${request.id}')">
                            <input type="button" class="btn btn-danger" value="Halt" onclick="haltRequest('${request.id}')">` : "" }`})
            }
        } else {
            customCreateTableRow(
                "noToRequests",
                document.getElementById("requests"),
                {html: `<td>--------------------</td>
                        <td>--------------------</td>
                        <td>--------------</td>
                        <td>--------------</td>
                        <td>--------</td>
                        <td>------</td>`
                }
            );
        }
        customCreateTableRow(
            "requestSeparator",
            document.getElementById("requests"),
            {html: ``}
        )
    }
    // Get the payments to the customer first. These will only have the option to halt
    let responseFromCustomer = await fetch(
        'http://localhost:9999/BankOfBen/api/getRequestsPendingFromCustomer', {
            method: 'POST',
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (responseFromCustomer.url.endsWith(".html") && responseFromCustomer.url !== window.location.href) {
        window.location.href = responseFromCustomer.url;
    } else {
        let requests = await responseFromCustomer.json();
        // let padBefore = 0;
        if (requests) {
            for (const request of requests) {
                customCreateTableRow(
                    request.id,
                    document.getElementById("requests"),
                    {html: `<td>${request.id}</td>
                            <td>${request.initUserId}</td>
                            <td>${request.requestorAccountNumber}</td>
                            <td>${request.soughtAccountNumber}</td>
                            <td>${request.amount.toFixed(2)}</td>
                            <td>${request.pending ? "Pending" : ""}</td>
                            ${request.pending ? `
                            <input type="button" class="btn btn-danger" value="Halt" onclick="haltRequest('${request.id}')">` : "" }`})
            }
        } else {
            customCreateTableRow(
                "noFromRequests",
                document.getElementById("requests"),
                {html: `<td>--------------------</td>
                        <td>--------------------</td>
                        <td>--------------</td>
                        <td>--------------</td>
                        <td>--------</td>
                        <td>------</td>`
                }
            );
        }
    }
}

async function acceptPayment(paymentId) {
    console.log(paymentId);
    console.log(JSON.stringify({id: paymentId}));
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/acceptPayment', {
            method: 'POST',
            body: JSON.stringify({id: paymentId}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html")) {
        // makeBalanceView();
        // makePaymentView();
        window.location.href = response.url;
    } else {
        console.log("Couldn't figure out what to do with response: "+response.url);
    }
}

async function haltPayment(paymentId) {
    console.log(paymentId);
    console.log(JSON.stringify({id: paymentId}));
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/haltPayment', {
            method: 'POST',
            body: JSON.stringify({id: paymentId}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html")) {
        // makeBalanceView();
        // makePaymentView();
        window.location.href = response.url;
    } else {
        console.log("Couldn't figure out what to do with response: "+response.url);
    }
}

async function acceptRequest(requestId) {
    console.log(requestId);
    console.log(JSON.stringify({id: requestId}));
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/acceptRequest', {
            method: 'POST',
            body: JSON.stringify({id: requestId}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html")) {
        // makeBalanceView();
        // makePaymentView();
        window.location.href = response.url;
    } else {
        console.log("Couldn't figure out what to do with response: "+response.url);
    }
}

async function haltRequest(requestId) {
    console.log(requestId);
    console.log(JSON.stringify({id: requestId}));
    let response = await fetch(
        'http://localhost:9999/BankOfBen/api/haltRequest', {
            method: 'POST',
            body: JSON.stringify({id: requestId}),
            headers: {"Content-type": "application/json; charset=UTF-8"}
        }
    );
    if (response.url.endsWith(".html")) {
        // makeBalanceView();
        // makePaymentView();
        window.location.href = response.url;
    } else {
        console.log("Couldn't figure out what to do with response: "+response.url);
    }
}

async function makeDeposit(accountNumber) {
    if (document.getElementById(`${accountNumber}amount`).checkValidity()) {
        let amount = document.getElementById(`${accountNumber}amount`).value;
        let bodyObj = {
            "accountNumber": `${accountNumber}`,
            "amount": `${amount}`
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
    } else {
        console.log(document.getElementById(`${accountNumber}amount`).checkValidity());
        console.log("Amount must be a number at least $0.01 to no more than 2 decimal places for deposit or withdrawal.");
    }
    // let amount = document.getElementById(`${accountNumber}amount`).value;
    // let splitAmountStringArray = `${amount}`.split('.');
    // if (isNaN(amount)) {
    //     console.log("Improper number");
    // } else if (amount < 0.01) {
    //     console.log("Amount must be at least $0.01 for deposit or withdrawal");
    // } else if (splitAmountStringArray.length > 2) {
    //     console.log("Improper number");
    // } else if (splitAmountStringArray.length==2 && splitAmountStringArray[1].length > 2) {
    //     console.log("Amount cannot be specified to more than two decimal places.");
    // } else {
    //     let bodyObj = {
    //         "accountNumber": `${accountNumber}`,
    //         "amount": `${amount}`
    //     };
    //     console.log(bodyObj);
    //     let response = await fetch(
    //         'http://localhost:9999/BankOfBen/api/makeDeposit', {
    //             method: 'POST',
    //             body: JSON.stringify(bodyObj),
    //             headers: {"Content-type": "application/json; charset=UTF-8"}
    //         }
    //     );
    //     console.log(response);
    //     if (response.url.endsWith(".html") && response.url !== window.location.href) {
    //         window.location.href = response.url;
    //     } else {
    //         console.log("Making reponse json.")
    //         let account = await response.json();
    //         let dollars = document.getElementById(`${accountNumber}dollars`);
    //         dollars.innerText = account.balance.toFixed(2);
    //         let balance = document.getElementById(`${accountNumber}balance`);
    //         balance.style = "color: green";
    //     }
    // }
}

async function makeWithdrawal(accountNumber) {
    if (document.getElementById(`${accountNumber}amount`).checkValidity()) {
        let amount = document.getElementById(`${accountNumber}amount`).value;
        let myAccount = undefined;
        for (const account of accounts) {
            if (account.accountNumber==accountNumber) {
                myAccount = account;
            }
        }
        if (amount > myAccount.balance) {
            console.log("Tried to withdraw too much money.")
        } else {
            let bodyObj = {
                "accountNumber": `${accountNumber}`,
                "amount": `${amount}`
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
    } else {
        console.log(document.getElementById(`${accountNumber}amount`).checkValidity());
        console.log("Amount must be a number at least $0.01 to no more than 2 decimal places for deposit or withdrawal.");
    }
    // let amount = document.getElementById(`${accountNumber}amount`).value;
    // let splitAmountStringArray = `${amount}`.split('.');
    // if (isNaN(amount)) {
    //     console.log("Improper number");
    // } else if (amount < 0.01) {
    //     console.log("Amount must be at least $0.01 for deposit or withdrawal");
    // } else if (splitAmountStringArray.length > 2) {
    //     console.log("Improper number");
    // } else if (splitAmountStringArray.length==2 && splitAmountStringArray[1].length > 2) {
    //     console.log("Amount cannot be specified to more than two decimal places.");
    // } else {
    //     let bodyObj = {
    //         "accountNumber": `${accountNumber}`,
    //         "amount": `${amount}`
    //     };
    //     console.log(bodyObj);
    //     let response = await fetch(
    //         'http://localhost:9999/BankOfBen/api/makeWithdrawal', {
    //             method: 'POST',
    //             body: JSON.stringify(bodyObj),
    //             headers: {"Content-type": "application/json; charset=UTF-8"}
    //         }
    //     );
    //     console.log(response);
    //     if (response.url.endsWith(".html") && response.url !== window.location.href) {
    //         window.location.href = response.url;
    //     } else {
    //         console.log("Making reponse json.")
    //         let account = await response.json();
    //         let dollars = document.getElementById(`${accountNumber}dollars`);
    //         dollars.innerText = account.balance.toFixed(2);
    //         let balance = document.getElementById(`${accountNumber}balance`);
    //         balance.style = "color: red";
    //     }
    // }
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