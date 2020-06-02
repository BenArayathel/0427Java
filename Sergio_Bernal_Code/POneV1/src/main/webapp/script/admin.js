(function () {
    window.addEventListener("load", function () {
        setCookie("ctrlApproval", "false", 1);
        // TODO fin usability to user
        let user = getSession('user');
    }, false)
})();

let regContent = document.querySelector("#regContent");

let btnNewUser = document.querySelector("#btnCustomer");
btnNewUser.addEventListener("click", function (event) {
    regContent.innerHTML = "";
    event.preventDefault();
    let user = getSession('user');
    if (user)
        searchPanel();
})

let btnFinishRegistration = document.querySelector("#btnApproval");
btnFinishRegistration.addEventListener("click", function (event) {
    event.preventDefault();
    regContent.innerHTML = "";
    let user = getSession('user');
    if (user)
        listApproval();
})

function searchPanel() {
    let nRow = document.createElement("div");
    nRow.id = "panel1";
    nRow.setAttribute("class", "row");
    setCookie("ctrlApproval", "false", 1);

    nRow.innerHTML = `
    <form class="col-12" id="searchCustomer">
        <div class="form-group row">
            <label for="inpSsn" class="col-sm-2 col-form-label"><strong>Customer</strong></label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="inpSsn" placeholder="Enter the customer SSN...">
            </div>
            <button type="submit" class="btn btn-primary col-sm-2" id="btnSearch">Search</button>
        </div>
    </form>
    `;

    regContent.appendChild(nRow);

    let btnSearch = regContent.querySelector("#btnSearch");
    btnSearch.addEventListener("click", function (event) {
        event.preventDefault();

        let ssn = document.querySelector("#inpSsn").value;
        if ((ssn + "").length === 9) {
            getCustomerAccountsById(`customer/${ssn}`)
                .then(() => {
                    let nForm = document.querySelector("#searchCustomer");
                    nForm.reset();
                });
        } else {
            console.error("The ssn is invalid!");
            createToast('Invalid SSN, try again.', 'danger');
        }
    })
}

function tableApproval(data) {

    let nRow = document.querySelector("#panel2");
    if (!nRow) {
        nRow = document.createElement("div");
        nRow.id = "panel2";
        nRow.setAttribute("class", "row");
    }

    nRow.innerHTML = `
    <table class="table table-hover table-responsive-sm">
        <caption>List of Pending Accounts</caption>
        <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Customer</th>
                <th scope="col">Account Type</th>
                <th scope="col">Start Balance</th>
                <th scope="col">Status</th>
                <th scope="col">Created At</th>
                <th scope="col">Edit</th>
            </tr>
        </thead>
        <tbody id="tableApproval"></tbody>
    </table>
    `;

    regContent.appendChild(nRow);

    let tableApproval = document.getElementById("tableApproval");

    for (let v of data) {
        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        let con = 0;
        for (let record in v) {
            con++;
            if (record === "id") {
                th.innerText = v[record];
                tr.appendChild(th);
            } else if (record === "customerId" || record === 'accountTypeId') {
                // Nothing
            } else {
                let td = document.createElement("td");
                td.innerText = v[record];
                tr.appendChild(td);
            }

            if (Object.keys(v).length === con) {
                let tdBtn = document.createElement("td");
                tdBtn.appendChild(createBtnGroup(v));
                tr.appendChild(tdBtn);
            }
        }

        tableApproval.appendChild(tr);
    }
}

function createBtnGroup(id) {
    let divGroupBtn = document.createElement("div");
    divGroupBtn.setAttribute("class", "btn-group");
    divGroupBtn.setAttribute("role", "group");
    divGroupBtn.setAttribute("aria-label", "Basic example");

    let btnDelete = document.createElement("button");
    btnDelete.setAttribute("class", "btn btn-danger");
    btnDelete.innerHTML = `Reject`;
    btnDelete.addEventListener("click", () => {
        updateAccount(id, 'Rejected');
    }, false);

    let btnEdit = document.createElement("button");
    btnEdit.setAttribute("class", "btn btn-success");
    btnEdit.innerHTML = `Approve`;
    btnEdit.addEventListener("click", () => {
        updateAccount(id, 'Approve');
    }, false);

    divGroupBtn.appendChild(btnDelete);
    divGroupBtn.appendChild(btnEdit);

    return divGroupBtn;
}

let listApproval = async () => {

    let flag = getCookie("ctrlApproval");
    if (flag === "false") {
        let url = BASE_URL + "approval/";
        try {
            let response = await fetch(url);
            let result = await response.json();
            console.log(result);
            if (Array.isArray(result.data)) {
                tableApproval(result.data);
                deleteCookie("ctrlApproval");
            } else {
                console.log("NO DATA");
            }
        } catch (error) {
            console.log(error);
        }
        setCookie("ctrlApproval", true, 1);
    }

}

let updateAccount = async (approval, status) => {

    let url = `${BASE_URL}approval`;
    let options = {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'id': approval['id'],
            'accountTypeId': approval['accountTypeId'],
            'accountTypeName': approval['accountType'],
            'status': status,
            'balance': approval['balance'],
            'personId': approval['customerId']
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        console.log('update account', result);
        if (result.data) {
            createToast(`Your new account is ready to use, save this number: ${result.data["id"]}`, 'success');
        } else {
            location.reload();
        }
    } catch (error) {
        console.error("ACCOUNT ERROR", error);
    }

}
