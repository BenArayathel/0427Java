let btnNewUser = document.querySelector("#btnTransfer");
btnNewUser.addEventListener("click", function (event) {
    regContent.innerHTML = "";
    event.preventDefault();
    let user = getSession('user');
    if (user)
        getTransferList();
})

let getTransferList = async () => {
    let user = getSession('user');
    let url = `${BASE_URL}transfer/${user["personId"]}`;

    try {
        let response = await fetch(url);
        let result = await response.json();
        if (Array.isArray(result.data))
            tableTransfer(result.data);
        else {
            console.log('There is not pending trnasfers');
        }
    } catch (error) {
        console.log("USER ERROR", error);
    }
}

function tableTransfer(data) {

    console.log('hey data', data);
    let nRow = document.querySelector("#panel2");
    if (!nRow) {
        nRow = document.createElement("div");
        nRow.id = "panel2";
        nRow.setAttribute("class", "row mt-4");
    }

    nRow.innerHTML = `
    <table class="table table-hover table-responsive-sm">
        <caption>List of Transfer</caption>
        <thead class="thead-dark">
            <tr>
                <th scope="col">From</th>
                <th scope="col">To</th>
                <th scope="col">Created At</th>
                <th scope="col">Status</th>
                <th scope="col">Amount</th>
                <th scope="col">#</th>
                <th scope="col">Edit</th>
            </tr>
        </thead>
        <tbody id="tableTransfer"></tbody>
    </table>
    `;

    regContent.appendChild(nRow);

    let tableTransfer = document.getElementById("tableTransfer");

    for (let v of data) {
        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        let con = 0;
        for (let record in v) {
            con++;
            if (record === "endRoutingNumber" || record === "iniRoutingNumber") {
                //nothing
            } else if (record === "id") {
                th.innerText = v[record];
                tr.appendChild(th);
            } else if (record === "createdAt") {
                let td = document.createElement("td");
                let auxDate = new Date(v[record]);
                td.innerText = `${auxDate.getMonth()}-${auxDate.getDate()}-${auxDate.getFullYear()}`;
                tr.appendChild(td);
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

        tableTransfer.appendChild(tr);
    }
}

function createBtnGroup(data) {

    let divGroupBtn = document.createElement("div");
    divGroupBtn.setAttribute("class", "btn-group");
    divGroupBtn.setAttribute("role", "group");
    divGroupBtn.setAttribute("aria-label", "Basic example");

    let btnDelete = document.createElement("button");
    btnDelete.setAttribute("class", "btn btn-danger");
    btnDelete.innerHTML = `Reject`;
    btnDelete.addEventListener("click", () => {
        updateTransfer(data, 'Rejected');
    }, false);

    let btnEdit = document.createElement("button");
    btnEdit.setAttribute("class", "btn btn-success");
    btnEdit.innerHTML = `Approve`;
    btnEdit.addEventListener("click", () => {
        updateTransfer(data, 'Approve');
    }, false);

    divGroupBtn.appendChild(btnDelete);
    divGroupBtn.appendChild(btnEdit);

    return divGroupBtn;
}

let updateTransfer = async (data, status) => {

    console.log(data);
    let url = `${BASE_URL}transfer`;
    let options = {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'id': data['id'],
            'status': status,
            'amount': data['amount'],
            'accountId': data['endAccountNumber']
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        if (result.data) {
            // createToast(`Your new account is ready to use, save this number: ${result.data["id"]}`);
            location.reload()
        } else {
            location.reload();
        }
    } catch (error) {
        console.error("ACCOUNT ERROR", error);
    }
}
