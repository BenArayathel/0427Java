
(function () {
    window.addEventListener('load', function (event) {
        event.preventDefault();
        let user = getSession('user');
        if (user)
            getAccountDetail();
    })
})();

let getAccountDetail = async () => {
    let accountId = getSession('account');
    let url = `${BASE_URL}account/${accountId["id"]}`;
    try {
        let response = await fetch(url);
        let result = await response.json();
        if (result.data) {
            accountDetail(result.data);
        } else {
            console.log("NO DATA - Account doesn't exist.");
        }
    } catch (error) {
        console.error("ACCOUNT ERROR", error);
    }
}

function accountDetail(data) {

    let accType = document.querySelector("#accType");
    accType.innerHTML = `${data["accountType"]["name"]}`;
    let accNumber = document.querySelector("#accNumber");
    accNumber.innerHTML = `<strong>Account Number: </strong>${data["id"]}`;
    let rouNumber = document.querySelector("#rouNumber");
    rouNumber.innerHTML = `<strong>Routing Number: </strong>${data["routingNumber"]}`;
    let balance = document.querySelector("#balance");
    balance.innerHTML = `<strong>Balance: </strong>$${data["balance"]}`;

}

let depositAccount = async () => {

    let dAmount = document.querySelector("#inpDAmount").value;
    let auxAmount = parseFloat(dAmount);
    let account = getSession('account');

    let url = `${BASE_URL}account/deposit`;
    let options = {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'accountId': account["id"],
            'accountBalance': account["balance"],
            'balance': auxAmount,
        })
    }

    if (auxAmount > 0) {
        try {
            let response = await fetch(url, options);
            let result = await response.json();
            console.log(result);
            if (result.data) {
                document.querySelector("#inpDAmount").value = '';
                setSession('account', result.data);
                accountDetail(result.data);
            } else {
                console.log("NO DATA - Account doesn't exist.");
            }
        } catch (error) {
            console.error("ACCOUNT ERROR", error);
        }
    } else {
        console.log("invalid amount")
    }
}

let withdrawAccount = async () => {

    let dAmount = document.querySelector("#inpWAmount").value;
    let auxAmount = parseFloat(dAmount);
    let account = getSession('account');

    let url = `${BASE_URL}account/withdraw`;
    let options = {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'accountId': account["id"],
            'accountBalance': account["balance"],
            'balance': auxAmount,
        })
    }

    if (auxAmount > 0 && (account["balance"] - auxAmount) >= 0) {
        try {
            let response = await fetch(url, options);
            let result = await response.json();
            if (result.data) {
                document.querySelector("#inpWAmount").value = '';
                setSession('account', result.data);
                accountDetail(result.data);
            } else {
                console.log("NO DATA - Account doesn't exist.");
            }
        } catch (error) {
            console.error("ACCOUNT ERROR", error);
        }
    } else {
        console.log("invalid amount")
    }

}