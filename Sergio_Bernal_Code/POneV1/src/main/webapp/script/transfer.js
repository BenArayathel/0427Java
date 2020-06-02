let user = getSession('user');
let listAccounts = [];
(function () {
    window.addEventListener('load', function () {
    }, false)
})();

let regContent = document.querySelector("#regContent");

let btnFinishRegistration = document.querySelector("#btnRequestTransfer");
btnFinishRegistration.addEventListener("click", function (event) {
    event.preventDefault();
    regContent.innerHTML = "";
    let user = getSession('user');
    if (user) {
        requestTransfer().then(() => {
            getCustomerAccounts();
        }).catch(error => {
            console.log("ERROR RENDER PAGE", error);
        });
    }

})

let requestTransfer = async () => {
    regContent.innerHTML = `
    <form id="transferForm">
        <div class="form-group row">
            <div class="col-sm-5">
                <label for="">From Account:</label>
                <select class="custom-select" id="fromAccount">
                    <option selected>Open this select menu</option>
                </select>
            </div>
            <div class="col-sm-5">
                <label for="staticFromRouting" class="">From Routing</label>
                <input type="text" readonly class="form-control-plaintext" id="staticFromRouting" value="">
            </div>
            <div class="col-sm-2">
                <label for="staticBalance" class="">Balance</label>
                <input type="text" readonly class="form-control-plaintext" id="staticBalance" value="">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-12">
                <div class="custom-control custom-radio">
                    <input type="radio" name="accountRadio" id="myAccounts"
                        class="custom-control-input account-radio" value="1">
                    <label class="custom-control-label" for="myAccounts">My Accounts</label>
                </div>
                <div class="custom-control custom-radio">
                    <input type="radio" name="accountRadio" id="otherAccounts"
                        class="custom-control-input account-radio" value="2">
                    <label class="custom-control-label" for="otherAccounts">Other Accounts</label>
                </div>
            </div>
        </div>
        <div class="form-group row" id="showToAccounts">
            <div class="col-sm-6">
                <label for="">To Account:</label>
                <select class="custom-select" id="toAccount">
                    <option selected>Open this select menu</option>
                </select>
            </div>
            <div class="col-sm-6">
                <label for="staticToRouting" class="">To Routing:</label>
                <input type="text" readonly class="form-control-plaintext" id="staticToRouting" value="">
            </div>
        </div>

        <div class="form-group mb-2">
            <label for="staticAmount1" class="sr-only">Amount</label>
            <input type="text" readonly class="form-control-plaintext"
                id="staticAmount1" value="Amount">
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inpAmount" class="sr-only">Amount</label>
            <input type="number" class="form-control" id="inpAmount"
                placeholder="Enter the amount...">
        </div>

        <button type="submit" class="btn btn-primary" id="btnCTransfer">Accept</button>
    </form>
    `;

    let radioAccount = document.querySelectorAll(".account-radio");
    for (let radio of radioAccount) {
        radio.addEventListener("change", function (event) {
            event.preventDefault();
            showToAccounts(radio.value);
        })
    }

    let btnCTransfer = document.querySelector("#btnCTransfer");
    btnCTransfer.addEventListener('click', function (event) {
        event.preventDefault();
        createTransfer();
    })

    return true;
}

let getCustomerAccounts = async () => {
    let url = `${BASE_URL}customer/id/${user["personId"]}`;
    try {
        let response = await fetch(url);
        let result = await response.json();
        listAccounts = result.data["accounts"];

        if (Array.isArray(listAccounts)) {

            let fromAccount = document.querySelector("#fromAccount");
            fromAccount.addEventListener('change', function (event) {
                event.preventDefault();
                selectFromAccount(listAccounts);
            })
            for (let index in result.data["accounts"]) {
                let option = document.createElement("option");
                option.setAttribute("value", listAccounts[index]["id"]);
                option.innerText = listAccounts[index]["id"];
                fromAccount.appendChild(option);
            }

        } else {
            console.log("NO DATA - Customer doesn't have any account.");
        }
    } catch (error) {
        console.error("SEARCH CUSTOMER ERROR", error);
    }
}

let initAccount = null;
let endAccount = null;

function selectFromAccount(data) {
    let fromAccount = document.querySelector("#fromAccount").value;
    let accountSelected = data.filter((account) => account["id"] === fromAccount);
    let staticFromRouting = document.querySelector("#staticFromRouting");
    let staticBalance = document.querySelector("#staticBalance");
    if (accountSelected.length) {
        initAccount = accountSelected[0];
        staticBalance.value = accountSelected[0]["balance"]
        staticFromRouting.value = accountSelected[0]["routingNumber"];
    } else {
        staticFromRouting.value = ""
    }
}

function showToAccounts(flag) {

    if (flag === "1") {

        let toAccount = createToAccounts(listAccounts);
        for (let index in listAccounts) {
            let option = document.createElement("option");
            option.setAttribute("value", listAccounts[index]["id"]);
            option.innerText = listAccounts[index]["id"];
            toAccount.appendChild(option);
        }

    } else {
        getListBankAccountEnrollment().then(result => {
            if (Array.isArray(result["data"])) {

                let toAccount = createToAccounts(result["data"]);
                for (let index in result["data"]) {
                    let option = document.createElement("option");
                    option.setAttribute("value", result["data"][index]["accountNumber"]);
                    option.innerText = `${result["data"][index]["toName"]} ${result["data"][index]["toLastName"]}`;
                    toAccount.appendChild(option);
                }

            } else {
                console.log("There is not other accounts.");
            }

        });
    }
}

function createToAccounts(data) {

    let toAccount = document.querySelector("#toAccount");
    toAccount.innerHTML = `<option selected value="0">Open this select menu</option>`;
    toAccount.addEventListener('change', function (event) {
        event.preventDefault();
        selectToAccount(data);
    })
    return toAccount;

}

function selectToAccount(data) {

    let toAccount = document.querySelector("#toAccount").value;
    let accountSelected = data.filter((account) => {
        if ("toName" in account) return account["accountNumber"] === toAccount;
        else return account["id"] === toAccount;
    });
    let staticToRouting = document.querySelector("#staticToRouting");
    if (accountSelected.length) {
        endAccount = accountSelected[0];
        staticToRouting.value = accountSelected[0]["routingNumber"];
    } else {
        staticToRouting.value = ""
    }
}

let getListBankAccountEnrollment = async (data) => {
    let user = getSession('user');
    let url = `${BASE_URL}enrollment/${user["personId"]}`;

    try {
        let response = await fetch(url);
        let result = await response.json();
        return result;
    } catch (error) {
        console.log("USER ERROR", error);
    }
}

let createBankAccountEnrollment = async (data) => {
    let user = getSession('user');
    let url = `${BASE_URL}enrollment/${user["personId"]}`;
    let options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'toName': toName,
            'toLastName': toLastName,
            'accountNumber': accountNumber,
            'routingNumber': routingNumber,
            'personId': user["personId"],
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        console.log(result)
    } catch (error) {
        console.log("USER ERROR", error);
    }
}

let createTransfer = async () => {

    let inpAmount = document.querySelector("#inpAmount").value;
    let auxAmount = parseFloat(inpAmount);

    let url = `${BASE_URL}transfer/`;
    let options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'initAccount': initAccount['id'],
            'endAccount': endAccount['id'],
            'initRouting': initAccount['routingNumber'],
            'endRouting': endAccount['routingNumber'],
            'status': 'Pending',
            'amount': auxAmount,
        })
    }
    if (auxAmount > 0 && initAccount !== null && endAccount !== null) {
        if ((initAccount['balance'] - auxAmount) >= 0) {
            try {
                let response = await fetch(url, options);
                let result = await response.json();
                if (result.data) {
                    location.reload();
                }
            } catch (error) {
                console.log("TRANSFER ERROR", error);
            }
        }
        else console.log('not posible');

    }
}